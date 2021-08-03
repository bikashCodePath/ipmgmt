package com.firstmeridian.ipmgmt.dao;

import java.util.List;
import java.util.Optional;

import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;

public interface IpPoolDao {

	public List<IpPoolEntity> findAll();
	public Optional<IpPoolEntity> getPoolById(Long poolId);
	public List<IpPoolEntity> generateIpPool(int number);
}
