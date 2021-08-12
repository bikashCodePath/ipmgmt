package com.firstmeridian.ipmgmt.dao;

import java.util.List;
import java.util.Optional;

import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;

public interface IpPoolDao {

	public List<IpPoolEntity> findAll();
	public Optional<IpPoolEntity> getPoolById(Long poolId);
	public Optional<IpPoolEntity> createIpPool(IpPoolEntity ipPool);
	public int findMaxLowerBound(Long poolId);
	public IPAddress generateIpInPool(IPAddress ipAddress);
	public int updateIpPoll(int updateLowerBound, Long poolId);
	public int findUpperBound(Long poolId);
}
