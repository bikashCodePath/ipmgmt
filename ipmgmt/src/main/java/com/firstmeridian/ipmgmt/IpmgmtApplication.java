package com.firstmeridian.ipmgmt;

import java.util.ArrayList;
import java.util.List;

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
			IPAddress address = new IPAddress();
			address.setIpValue("10.10.0");
			address.setIpPool(ipPollEntity);
			List<IPAddress> addresses = new ArrayList<IPAddress>();
			addresses.add(address);
			ipPollEntity.setIpAddresses(addresses);
			ipreIpPoolRepository.save(ipPollEntity);
		};
	}
}
