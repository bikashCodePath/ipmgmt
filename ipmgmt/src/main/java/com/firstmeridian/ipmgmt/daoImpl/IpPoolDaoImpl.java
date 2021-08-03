package com.firstmeridian.ipmgmt.daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.firstmeridian.ipmgmt.IpManagementConstants;
import com.firstmeridian.ipmgmt.common.exception.BusinessException;
import com.firstmeridian.ipmgmt.dao.IpPoolDao;
import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.repository.IPAddressRepository;
import com.firstmeridian.ipmgmt.repository.IpPoolRepository;

@Repository
public class IpPoolDaoImpl implements IpPoolDao{

	@Autowired
	private IpPoolRepository IpPoolRepository;
	@Autowired
	private IPAddressRepository iPAddressRepository;
	@Override
	public List<IpPoolEntity> findAll() {
		// TODO Auto-generated method stub
		int findMaxLowerBound = IpPoolRepository.findMaxLowerBound();
		return IpPoolRepository.findAll();
	}
	
	@Override
	public Optional<IpPoolEntity> getPoolById(Long poolId) {
		// TODO Auto-generated method stub
		return IpPoolRepository.findById(poolId);
	}

	@Override
	@Transactional
	public List<IpPoolEntity> generateIpPool(int number) {
		// TODO Auto-generated method stub
		List<IpPoolEntity> ipPloosList = new ArrayList<IpPoolEntity>();
		while(number !=0) {
			number--;
			int maxLowerBound = IpPoolRepository.findMaxLowerBound();
			if(maxLowerBound < IpManagementConstants.upperBound) {
				IpPoolEntity ipPoolEntity = new IpPoolEntity("desc2", 10, 2, maxLowerBound+1, IpManagementConstants.upperBound);
				IpPoolRepository.save(ipPoolEntity);
				iPAddressRepository.save(new IPAddress(IpManagementConstants.boundConst+ipPoolEntity.getLowerBound(), ipPoolEntity));
				ipPloosList.add(ipPoolEntity);
			}else {
				throw new BusinessException("OutOfSpace", "IpPool is full please try to create less number of Ip address");
			}
		}
		return ipPloosList;
	}

}
