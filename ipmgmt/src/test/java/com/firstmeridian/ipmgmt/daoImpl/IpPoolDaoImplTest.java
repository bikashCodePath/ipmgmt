package com.firstmeridian.ipmgmt.daoImpl;

import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.repository.IPAddressRepository;
import com.firstmeridian.ipmgmt.repository.IpPoolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IpPoolDaoImplTest {

    @InjectMocks
    IpPoolDaoImpl ipPoolDao;
    @Mock
    private IpPoolRepository ipPoolRepository;
    @Mock
    private IPAddressRepository iPAddressRepository;

   /* @Test
    void generateIpPoolSuccess() {
        when(ipPoolRepository.findMaxLowerBound()).thenReturn(2);
        when(iPAddressRepository.save(any(IPAddress.class))).thenReturn(new IPAddress());
        when(ipPoolRepository.findById(any(Long.class))).thenReturn(Optional.of(new IpPoolEntity()));
        when(ipPoolRepository.updateIpPoll(any(Integer.class),any(Long.class))).thenReturn(1);
        List<IPAddress> ipAddresses = ipPoolDao.generateIpPool(1L,5);
        assertEquals(ipAddresses.size(), 5);
    }

    @Test
    void generateIpPoolOutOfSpace() {
        when(ipPoolRepository.findMaxLowerBound()).thenReturn(7);
        when(ipPoolRepository.findById(any(Long.class))).thenReturn(Optional.of(new IpPoolEntity()));
        BusinessException exception =
                assertThrows(BusinessException.class, () ->
                        ipPoolDao.generateIpPool(1L,10));
        assertEquals(exception.getLocalizedMessage(), "OutOfSpace");
    }
    
    @Test
    void generateIpPoolNotFound() {
        when(ipPoolRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        BusinessException exception =
                assertThrows(BusinessException.class, () ->
                        ipPoolDao.generateIpPool(1L,10));
        assertEquals(exception.getLocalizedMessage(), "PoolNotFound");
    }*/
    @Test
    void getPoolIdTest() {
    	Optional<IpPoolEntity> ipPoolEntity = Optional.of(new IpPoolEntity());
    	when(ipPoolDao.getPoolById(any(Long.class))).thenReturn(Optional.of(new IpPoolEntity()));
    	assertEquals(ipPoolDao.getPoolById(1L),ipPoolEntity);
    }
    
    @Test
    void testfindAll() {
    	List<IpPoolEntity> poolList = new ArrayList<IpPoolEntity>();
    	when(ipPoolDao.findAll()).thenReturn(new ArrayList<IpPoolEntity>());
    	assertEquals(ipPoolDao.findAll(),poolList);
    }
    
    @Test
    void createIpPoolSuccess() {
    	when(ipPoolRepository.save(any(IpPoolEntity.class))).thenReturn(new IpPoolEntity());
    	IpPoolEntity ipPoolEntity = new IpPoolEntity();
//		ipPoolEntity.setLowerBound(0);
//		ipPoolEntity.setUppperBound(4);
//		ipPoolEntity.setTotalCapacity(5);
//		ipPoolEntity.setUsedCapacity(1);
		//when(ipPoolDao.createIpPool(any(IpPoolEntity.class))).thenReturn(Optional.of(ipPoolEntity));
		Optional<IpPoolEntity> ipPool = ipPoolDao.createIpPool(ipPoolEntity);
		assertEquals(ipPool, Optional.of(ipPoolEntity));
    }
    
    @Test
    void createIpPoolEmpty() {
    	when(ipPoolRepository.save(any(IpPoolEntity.class))).thenReturn(null);
    	IpPoolEntity ipPoolEntity = new IpPoolEntity();
//		ipPoolEntity.setLowerBound(0);
//		ipPoolEntity.setUppperBound(4);
//		ipPoolEntity.setTotalCapacity(5);
//		ipPoolEntity.setUsedCapacity(1);
		//when(ipPoolDao.createIpPool(any(IpPoolEntity.class))).thenReturn(Optional.of(ipPoolEntity));
		Optional<IpPoolEntity> ipPool = ipPoolDao.createIpPool(ipPoolEntity);
		assertEquals(ipPool, Optional.empty());
    }
    @Test
    void findMaxLowerBoundTest() {
    	when(ipPoolRepository.findMaxLowerBound(any(Long.class))).thenReturn(0);
    	assertEquals(ipPoolDao.findMaxLowerBound(1l), 0);
    }
    
    @Test
    void getPoolByIdTest() {
    	Optional<IpPoolEntity> ipPoolEntity = Optional.of(new IpPoolEntity());
    	when(ipPoolRepository.findById(any(Long.class))).thenReturn(Optional.of(new IpPoolEntity()));
    	assertEquals(ipPoolDao.getPoolById(1l), ipPoolEntity);
    }
    
    @Test
    void generateIpInPoolTest() {
    	IPAddress ipAddress = new IPAddress();
    	when(iPAddressRepository.save(any(IPAddress.class))).thenReturn(new IPAddress());
    	assertEquals(ipPoolDao.generateIpInPool(ipAddress), new IPAddress());
    }
    
    @Test
    void updateIpPollTest() {
    	//ipPoolRepository.updateIpPoll(updateLowerBound, poolId);
    	when(ipPoolRepository.updateIpPoll(any(Integer.class), any(Long.class))).thenReturn(1);
    	assertEquals(ipPoolDao.updateIpPoll(2,1l), 1);
    	
    }
}