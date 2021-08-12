package com.firstmeridian.ipmgmt.serviceImpl;

import com.firstmeridian.ipmgmt.common.exception.BusinessException;
import com.firstmeridian.ipmgmt.dao.IpPoolDao;
import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IpPoolServiceImplTest {

	@Mock
	IpPoolDao ipPoolDao;

	@InjectMocks
	IpPoolServiceImpl ipPoolService;

	@Test
	void ipPoolsSuccessEmpty() {
		when(ipPoolDao.findAll()).thenReturn(new ArrayList<>());
		Optional<List<IpPoolEntity>> ipPools = ipPoolService.ipPools();
		assertEquals(ipPools, Optional.empty());
	}

	@Test
	void ipPoolsSuccessNotEmpty() {
		IpPoolEntity entity = new IpPoolEntity();
		List<IpPoolEntity> ipPoolEntities = new ArrayList<>();
		ipPoolEntities.add(entity);
		when(ipPoolDao.findAll()).thenReturn(ipPoolEntities);
		Optional<List<IpPoolEntity>> ipPools = ipPoolService.ipPools();
		assertEquals(1, ipPools.get().size());
	}

	@Test
	void getPoolByIdSuccessNotEmpty() {
		IpPoolEntity entity = new IpPoolEntity();
		when(ipPoolDao.getPoolById(anyLong())).thenReturn(Optional.of(entity));
		assertEquals(ipPoolService.getPoolById(1L), Optional.of(entity));
	}

	@Test
	void generateIpPoolSuccess() {
//		IpPoolEntity ipPoolEntity = new IpPoolEntity();
//		ipPoolEntity.setDescription("desc1");
//		ipPoolEntity.setLowerBound(0);
//		ipPoolEntity.setUppperBound(4);
//		ipPoolEntity.setTotalCapacity(5);
//		ipPoolEntity.setUsedCapacity(1);
		//IPAddress address = new IPAddress(IpManagementConstants.boundConst+1, ipPoolEntity);
		IPAddress address = new IPAddress();
		//address.setIpPool(ipPoolEntity);
		//address.setIpValue("10.20.0");
		List<IPAddress> addressList = new ArrayList<IPAddress>();
		addressList.add(address);
		//IPAddress ipAddress = new IPAddress();
		//addressList.add(address);
		//ipPoolEntity.setIpAddresses(addressList);
		when(ipPoolDao.getPoolById(anyLong())).thenReturn(Optional.of(new IpPoolEntity()));
		when(ipPoolDao.findMaxLowerBound(anyLong())).thenReturn(1);
		when(ipPoolDao.findUpperBound(anyLong())).thenReturn(4);
		//when(ipPoolDao.generateIpPool(anyLong(), anyInt())).thenReturn(addressList);
		//when(ipPoolDao.generateIpInPool(new IPAddress(IpManagementConstants.boundConst+anyInt(), ipPoolEntity))).thenReturn(ipAddress);
		when(ipPoolDao.generateIpInPool(any(IPAddress.class))).thenReturn(address);
		Optional<List<IPAddress>> generateIpPool = ipPoolService.generateIpPool(1L, 2);
		List<IPAddress> list = generateIpPool.get();
		assertEquals(list.size(), 2);
	}
	
	/*@Test
	void generateIpPoolSuccessEmpty() {
		IPAddress address = new IPAddress();
		List<IPAddress> addressList = new ArrayList<IPAddress>();
		addressList.add(address);
		when(ipPoolDao.getPoolById(anyLong())).thenReturn(Optional.of(new IpPoolEntity()));
		when(ipPoolDao.findMaxLowerBound(anyLong())).thenReturn(1);
		when(ipPoolDao.generateIpInPool(any(IPAddress.class))).thenReturn(address);
		Optional<List<IPAddress>> generateIpPool = ipPoolService.generateIpPool(20L, 2);
		assertEquals(generateIpPool, Optional.empty());
	}*/
	
	@Test
    void generateIpPoolOutOfSpace() {
        when(ipPoolDao.findMaxLowerBound(anyLong())).thenReturn(7);
        when(ipPoolDao.getPoolById(any(Long.class))).thenReturn(Optional.of(new IpPoolEntity()));
        when(ipPoolDao.findUpperBound(anyLong())).thenReturn(4);
        BusinessException exception =
                assertThrows(BusinessException.class, () ->
                ipPoolService.generateIpPool(1L,10));
        assertEquals(exception.getLocalizedMessage(), "OutOfSpace");
    }
	
	@Test
	void generateIpPoolNotFound() {
		when(ipPoolDao.getPoolById(any(Long.class))).thenReturn(Optional.empty());
		BusinessException exception = assertThrows(BusinessException.class, () -> ipPoolService.generateIpPool(1L, 10));
		assertEquals(exception.getLocalizedMessage(), "PoolNotFound");
	}

	@Test
	void createIpPoolSuccess() {
		IpPoolEntity ipPoolEntity = new IpPoolEntity();
//		ipPoolEntity.setLowerBound(0);
//		ipPoolEntity.setUppperBound(4);
//		ipPoolEntity.setTotalCapacity(5);
//		ipPoolEntity.setUsedCapacity(1);
		when(ipPoolDao.createIpPool(any(IpPoolEntity.class))).thenReturn(Optional.of(ipPoolEntity));
		Optional<IpPoolEntity> ipPool = ipPoolService.createIpPool(ipPoolEntity);
		assertEquals(ipPool, Optional.of(ipPoolEntity));
	}
	
	@Test
	void createIpPoolUpperBoundViolated() {
		IpPoolEntity ipPoolEntity = new IpPoolEntity();
		List<IPAddress> ipAddresses = new ArrayList<IPAddress>();
		IPAddress address = new IPAddress();
		address.setIpValue("10.20.9");
		ipAddresses.add(address);
		ipPoolEntity.setIpAddresses(ipAddresses);
		BusinessException exception =
                assertThrows(BusinessException.class, () ->
                ipPoolService.createIpPool(ipPoolEntity));
		assertEquals(exception.getLocalizedMessage(), "upperBoundViolated");
	}
}