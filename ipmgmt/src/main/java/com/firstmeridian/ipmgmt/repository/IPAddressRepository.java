package com.firstmeridian.ipmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.firstmeridian.ipmgmt.persistence.IPAddress;

public interface IPAddressRepository extends JpaRepository<IPAddress, Long>,CrudRepository<IPAddress, Long>{

}
