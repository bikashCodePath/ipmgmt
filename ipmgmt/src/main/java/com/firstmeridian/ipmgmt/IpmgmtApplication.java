package com.firstmeridian.ipmgmt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.repository.IPAddressRepository;
import com.firstmeridian.ipmgmt.repository.IpPoolRepository;

@SpringBootApplication
public class IpmgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpmgmtApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(IpPoolRepository ipreIpPoolRepository,IPAddressRepository ipAddressRepository) {
		return args->{
			IpPoolEntity ipPollEntity = new IpPoolEntity("desc1", 20, 10, IpManagementConstants.lowerBound, IpManagementConstants.upperBound);
			ipreIpPoolRepository.save(ipPollEntity);
			ipAddressRepository.save(new IPAddress("10.70.26."+IpManagementConstants.lowerBound, ipPollEntity));
		};
	}
}
