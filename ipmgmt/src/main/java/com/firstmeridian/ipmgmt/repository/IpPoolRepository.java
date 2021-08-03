package com.firstmeridian.ipmgmt.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;

public interface IpPoolRepository extends JpaRepository<IpPoolEntity, Long>,CrudRepository<IpPoolEntity, Long>{

	public static final String MAX_OF_LOWERBOUND = "SELECT max(lowerBound) FROM IpPoolEntity";
	
	@Query(MAX_OF_LOWERBOUND)
	public int findMaxLowerBound();
}
