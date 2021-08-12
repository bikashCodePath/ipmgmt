package com.firstmeridian.ipmgmt.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstmeridian.ipmgmt.IpManagementConstants;
import com.firstmeridian.ipmgmt.common.exception.BusinessException;
import com.firstmeridian.ipmgmt.dao.IpPoolDao;
import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.service.IpPoolService;

@Service
public class IpPoolServiceImpl implements IpPoolService{
	
	private static final Logger LOGGER =LoggerFactory.getLogger(IpPoolServiceImpl.class);

	@Autowired
	private IpPoolDao IpPoolDao;
	@Override
	public Optional<List<IpPoolEntity>> ipPools() {
		LOGGER.info("####Retriving all the ip pools availble####");
		List<IpPoolEntity> findAll = IpPoolDao.findAll();
		return findAll.size()>0?Optional.of(findAll):Optional.empty();
	}
	@Override
	public Optional<IpPoolEntity> getPoolById(Long poolId) {
		LOGGER.info("####Retriving ip pools based on ip poolId####");
		return IpPoolDao.getPoolById(poolId);
	}
	@Override
	@Transactional
	public Optional<List<IPAddress>> generateIpPool(Long poolId,int totalNumber) {
		LOGGER.info("Generating ip addresses in the requested pool {}",poolId);
		List<IPAddress> ipAddressList = new ArrayList<IPAddress>();
		Optional<IpPoolEntity> findById = IpPoolDao.getPoolById(poolId);
		List<IPAddress> addressGenerated = new ArrayList<IPAddress>();
		if(findById.isPresent()) {
			LOGGER.info("PoolId found with requested poolId {}",poolId);
			IpPoolEntity ipPoolEntity = findById.get();
			int upperBound = IpPoolDao.findUpperBound(poolId);
			int lowerBound = IpPoolDao.findMaxLowerBound(poolId);// will change each time so kept in the loop, only name is findMaxLowerBound but fetching only the lowerbound of pool
			List<IPAddress> ipAddresses = ipPoolEntity.getIpAddresses();
			int currentCapacity = ipAddresses !=null?upperBound-ipAddresses.size():upperBound;
			int updateLowerBound = 0;
			LOGGER.info("Creating {} of ip address into ip pool with poolId {}",totalNumber,poolId);
			while(totalNumber !=0) {
				if(totalNumber<=currentCapacity) {
					updateLowerBound = lowerBound+1;
					lowerBound = updateLowerBound;
					IPAddress address = IpPoolDao.generateIpInPool(new IPAddress(IpManagementConstants.boundConst+updateLowerBound, ipPoolEntity));
					ipPoolEntity.setLowerBound(updateLowerBound);
					IpPoolDao.updateIpPoll(updateLowerBound, poolId);// lowerbound updated in the pool 
					addressGenerated.add(address);// storing all address to the list to return back
					ipAddressList.add(address);
					currentCapacity--;
					LOGGER.info("####Generated ip address successfully####");
				}else {
					LOGGER.warn("Could not create ip address as the requested pool does not have enough space to serve this request");
					throw new BusinessException("OutOfSpace", "The requested ip pool can generate less than or equal to "+currentCapacity+" number of ip ! please request for lesser number of ip address",HttpStatus.INSUFFICIENT_STORAGE);
				}
				totalNumber--;
			}
		}else {
			LOGGER.warn("####The requested ip pool is not available####");
			throw new BusinessException("PoolNotFound", "No IpPool found to create ip address for the input poolId : "+poolId,HttpStatus.NOT_FOUND);
		}
		LOGGER.info(ipAddressList.size()+" number of ip address generated in the ip pool with pool id "+poolId);
		return ipAddressList.size() !=0?Optional.of(ipAddressList):Optional.empty();
	}
	@Override
	@Transactional
	public Optional<IpPoolEntity> createIpPool(IpPoolEntity ipPool) {
		// TODO Auto-generated method stub
		LOGGER.info("Creating the requested ip pool");
		List<IPAddress> ipAddresses = ipPool.getIpAddresses();
		List<IPAddress> collect = null;
		if(ipAddresses !=null) {
			LOGGER.info("Validating ip address with ip value greater than upper bound of the ip pool");
			collect = ipAddresses.stream()
								 .filter(address->Integer.parseInt(address.getIpValue().substring(address.getIpValue().lastIndexOf('.')+1, address.getIpValue().length()))>ipPool.getUppperBound())
								 .collect(Collectors.toList());
		}
		if(collect !=null && collect.size() !=0) {
			LOGGER.warn("Aborting generation of pool as address in the pool is violating upperbound");
			throw new BusinessException("upperBoundViolated", "Please provide correct ip value based on pool upperbound for "+collect.get(0).getIpValue(),HttpStatus.NOT_ACCEPTABLE);
		}
		if(ipAddresses !=null) {
			ipAddresses.forEach(address->address.setIpPool(ipPool));
		}
		return IpPoolDao.createIpPool(ipPool);
	}
	
	

}
