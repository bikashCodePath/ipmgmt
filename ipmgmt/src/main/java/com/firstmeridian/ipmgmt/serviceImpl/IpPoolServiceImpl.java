package com.firstmeridian.ipmgmt.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstmeridian.ipmgmt.common.exception.BusinessException;
import com.firstmeridian.ipmgmt.dao.IpPoolDao;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.service.IpPoolService;

@Service
public class IpPoolServiceImpl implements IpPoolService{

	@Autowired
	private IpPoolDao IpPoolDao;
	@Override
	public Optional<List<IpPoolEntity>> ipPools() {
		List<IpPoolEntity> findAll = IpPoolDao.findAll();
		return findAll.size()>0?Optional.of(findAll):Optional.empty();
	}
	@Override
	public Optional<IpPoolEntity> getPoolById(Long poolId) {
		// TODO Auto-generated method stub
		Optional<IpPoolEntity> poolById = IpPoolDao.getPoolById(poolId);
		return IpPoolDao.getPoolById(poolId);
	}
	@Override
	public Optional<List<IpPoolEntity>> generateIpPool(int number) {
		// TODO Auto-generated method stub
		List<IpPoolEntity> generateIpPool = IpPoolDao.generateIpPool(number);
		return generateIpPool.size()>0?Optional.of(generateIpPool):Optional.empty();
	}
	
	

}
