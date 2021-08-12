package com.firstmeridian.ipmgmt.daoImpl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
		LOGGER.info("Retriving ip pool for the poolId {}",poolId);
		return ipPoolRepository.findById(poolId);
	}

	@Override
	public Optional<IpPoolEntity> createIpPool(IpPoolEntity ipPool) {
		// TODO Auto-generated method stub
		IpPoolEntity addressPool = ipPoolRepository.save(ipPool);
		return addressPool !=null?Optional.of(addressPool):Optional.empty();
	}

	@Override
	public int findMaxLowerBound(Long poolId) {
		// TODO Auto-generated method stub
		return ipPoolRepository.findMaxLowerBound(poolId);
	}

	@Override
	public IPAddress generateIpInPool(IPAddress ipAddress) {
		// TODO Auto-generated method stub
		return iPAddressRepository.save(ipAddress);
	}

	@Override
	public int updateIpPoll(int updateLowerBound, Long poolId) {
		// TODO Auto-generated method stub
		return ipPoolRepository.updateIpPoll(updateLowerBound, poolId);
		
	}

	@Override
	public int findUpperBound(Long poolId) {
		// TODO Auto-generated method stub
		return ipPoolRepository.findUpperBound(poolId);
	}

}
