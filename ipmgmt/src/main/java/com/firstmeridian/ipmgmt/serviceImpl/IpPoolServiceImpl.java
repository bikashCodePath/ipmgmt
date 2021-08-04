package com.firstmeridian.ipmgmt.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.firstmeridian.ipmgmt.dao.IpPoolDao;
import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.service.IpPoolService;

@Service
public class IpPoolServiceImpl implements IpPoolService{
	
	private static final Logger LOGGER =LoggerFactory.getLogger(IpPoolServiceImpl.class);

	@Autowired
	private IpPoolDao IpPoolDao;
	@Override
	public Optional<List<IpPoolEntity>> ipPools() {
		LOGGER.info("####Retriving all the ip pools availble####");
		List<IpPoolEntity> findAll = IpPoolDao.findAll();
		return findAll.size()>0?Optional.of(findAll):Optional.empty();
	}
	@Override
	public Optional<IpPoolEntity> getPoolById(Long poolId) {
		LOGGER.info("####Retriving ip pools based on ip poolId####");
		return IpPoolDao.getPoolById(poolId);
	}
	@Override
	public Optional<List<IPAddress>> generateIpPool(Long poolId,int number) {
		LOGGER.info("####Generating ip addresses in the requested pool####");
		List<IPAddress> generatedIpAddress = IpPoolDao.generateIpPool(poolId,number);
		return generatedIpAddress.size() !=0?Optional.of(generatedIpAddress):Optional.empty();
	}
	
	

}
