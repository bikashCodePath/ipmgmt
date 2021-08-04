package com.firstmeridian.ipmgmt.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;

public interface IpPoolRepository extends JpaRepository<IpPoolEntity, Long>,CrudRepository<IpPoolEntity, Long>{

	public static final String MAX_OF_LOWERBOUND = "SELECT max(lowerBound) FROM IpPoolEntity";
	public static final String UPDATE_LOWERBOUND = "UPDATE IpPoolEntity SET lowerBound =:lowerBound where poolId=:poolId";
	
	@Query(MAX_OF_LOWERBOUND)
	public int findMaxLowerBound();
	
	@Modifying
	@Query(UPDATE_LOWERBOUND)
	public int updateIpPoll(int lowerBound,Long poolId);
}
