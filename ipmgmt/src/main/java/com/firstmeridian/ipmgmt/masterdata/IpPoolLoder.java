package com.firstmeridian.ipmgmt.masterdata;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.repository.IPAddressRepository;
import com.firstmeridian.ipmgmt.repository.IpPoolRepository;

@Component
public class IpPoolLoder {

	@Autowired
	private IpPoolRepository IpPoolRepository;
	@Autowired
	private IPAddressRepository iPAddressRepository;
	@PostConstruct
	@Transactional
	public void loadIpPool() {
		System.out.println("####post construction####");
		//IpPoolEntity ipPollEntity = new IpPoolEntity("desc1", 20, 10, "0", "255");
		//address.setIpPool(ipPollEntity);
		//address.setIpValue("10.2.27.0");
		//List<IPAddress> addressList = new ArrayList<IPAddress>();
		//addressList.add(address);
		//ipPollEntity.setIpAddresses(addressList);
		//address.setIpPool(ipPollEntity);
		/*IpPoolRepository.save(ipPollEntity);
		IPAddress address = new IPAddress("10.2.27.0", ipPollEntity);
		iPAddressRepository.save(address);*/
		System.out.println("####post construction completed####");
	}
}
