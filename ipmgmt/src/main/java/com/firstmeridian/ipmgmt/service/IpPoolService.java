package com.firstmeridian.ipmgmt.service;

import java.util.List;
import java.util.Optional;

import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;

public interface IpPoolService {

	public Optional<List<IpPoolEntity>> ipPools();
	public Optional<IpPoolEntity> getPoolById(Long poolId);
	public Optional<List<IPAddress>> generateIpPool(Long poolId,int number);
	public Optional<IpPoolEntity> createIpPool(IpPoolEntity ipPool);
	
}
