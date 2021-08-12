package com.firstmeridian.ipmgmt.resource;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.firstmeridian.ipmgmt.common.exception.BusinessException;
import com.firstmeridian.ipmgmt.persistence.IPAddress;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.service.IpPoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author MOHAPAB
 *
 */
/**
 * @author MOHAPAB
 *
 */
/**
 * @author MOHAPAB
 *
 */
@RestController
@RequestMapping("ipmgmt")
@Api(value = "IpResourceManagement", description = "Manages IpAddress in IP pool")
public class IpManagementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IpManagementController.class);

	@Autowired
	private IpPoolService IpPoolService;
	//get method for ippools
	/**
	 * @return all the ip pool present in the system
	 */
	@GetMapping("/ippools")
	@ApiOperation(value = "Returns All IpAddress present in the Ip pool")
	public List<IpPoolEntity> findAll(){
		LOGGER.info("####Retriving all pools####");
		return IpPoolService.ipPools().orElseThrow(() -> new BusinessException("NOT_FOUND","No ip pool found !",HttpStatus.NOT_FOUND));
	}
	//get operation on ip pool based on poolId
	/**
	 * @param poolId
	 * @return the ip pool against the input poolId
	 */
	@GetMapping("/ippools/{poolId}")
	@ApiOperation(value = "Returns the pool and ip address present based on poolid",consumes = "poolId")
	public IpPoolEntity findIpPool(@PathVariable Long poolId){
		LOGGER.info("Retriving pool based on poolId {}",poolId);
		return IpPoolService.getPoolById(poolId).orElseThrow(() -> new BusinessException("NOT_FOUND","IpPool not found with id " + poolId,HttpStatus.NOT_FOUND));
	}
	//post operation for ip address into a specific ip pool based on poolId
	/**
	 * @param poolId
	 * @param totalNumber
	 * @return list of ip address in the pool
	 */
	@PostMapping("/ippools/ipaddress/generate/{poolId}/{totalNumber}")
	public List<IPAddress> produceIpAddress(@PathVariable Long poolId,@PathVariable int totalNumber){
		LOGGER.info("Generating ip address into pool with poolId {}",poolId);
		return IpPoolService.generateIpPool(poolId,totalNumber).orElseThrow(() -> new BusinessException("NotCreated","Could not create ip address",HttpStatus.OK));
	}
	//post method for ippool 
	/**
	 * @param ipPool
	 * @return the pool got created in the system
	 */
	@PostMapping("/ippools/generate/ipPool")
	public IpPoolEntity createIpPool(IpPoolEntity ipPool) {
		LOGGER.info("Handler to create an ip pool");
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Handler to create an ip pool");
		}
		return IpPoolService.createIpPool(ipPool).orElseThrow(() -> new BusinessException("NotCreated","IpPool could not be created, Plz try later !",HttpStatus.OK));
	}
	
	//Other api can be created 
	//get the current capacity
	//delete the ip pool
	//delete specific ip address in the pool
	//clear the ip pool by deleting all ip address in the pool
	//update upperbound of ip pool
}
