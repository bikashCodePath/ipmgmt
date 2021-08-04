package com.firstmeridian.ipmgmt.daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.firstmeridian.ipmgmt.IpManagementConstants;
import com.firstmeridian.ipmgmt.common.exception.BusinessException;
import com.firstmeridian.ipmgmt.dao.IpPoolDao;
import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.repository.IPAddressRepository;
import com.firstmeridian.ipmgmt.repository.IpPoolRepository;

@Repository
public class IpPoolDaoImpl implements IpPoolDao{

	private static final Logger LOGGER =LoggerFactory.getLogger(IpPoolDaoImpl.class);
	@Autowired
	private IpPoolRepository ipPoolRepository;
	@Autowired
	private IPAddressRepository iPAddressRepository;
	@Override
	public List<IpPoolEntity> findAll() {
		LOGGER.info("####Retriving all pools####");
		return ipPoolRepository.findAll();
	}
	
	@Override
	public Optional<IpPoolEntity> getPoolById(Long poolId) {
		LOGGER.info("####Retriving ip pool for the poolId####");
		return ipPoolRepository.findById(poolId);
	}

	@Override
	@Transactional
	public List<IPAddress> generateIpPool(Long poolId,int number) {
		List<IPAddress> ipAddressList = new ArrayList<IPAddress>();
		Optional<IpPoolEntity> findById = ipPoolRepository.findById(poolId);
		List<IPAddress> addressGenerated = new ArrayList<IPAddress>();
		LOGGER.info("####Checking if Ip pool available with the requested poolId####");
		if(findById.isPresent()) {
			LOGGER.info("####PoolId found with requested poolId####");
			IpPoolEntity ipPoolEntity = ipPoolRepository.findById(poolId).get();
			while(number !=0) {
				number--;
				int maxLowerBound = ipPoolRepository.findMaxLowerBound();
				int currentCapacity = IpManagementConstants.upperBound - maxLowerBound;
				if(maxLowerBound < IpManagementConstants.upperBound) {
					int updateLowerBound = ipPoolEntity.getLowerBound()+1;
					IPAddress address = iPAddressRepository.save(new IPAddress(IpManagementConstants.boundConst+updateLowerBound, ipPoolEntity));
					ipPoolEntity.setLowerBound(updateLowerBound);
					ipPoolRepository.updateIpPoll(updateLowerBound, poolId);
					addressGenerated.add(address);
					ipAddressList.add(address);
					LOGGER.info("####Generated ip address successfully####");
				}else {
					LOGGER.info("####Could not create ip address as the requested ip pool is full now####");
					throw new BusinessException("OutOfSpace", "The requested ip pool can generate less than or equal to "+currentCapacity+" number of ip ! please request for lesser number of ip address");
				}
			}
		}else {
			LOGGER.info("####The requested ip pool is not available####");
			throw new BusinessException("PoolNotFound", "No IpPool found to create ip address for the input poolId : "+poolId);
		}
		LOGGER.info(ipAddressList.size()+" number of ip address generated in the ip pool with pool id "+poolId);
		return ipAddressList;
	}

}
