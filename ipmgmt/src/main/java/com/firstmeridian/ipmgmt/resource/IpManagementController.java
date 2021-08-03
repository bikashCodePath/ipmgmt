package com.firstmeridian.ipmgmt.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.firstmeridian.ipmgmt.common.exception.BusinessException;
import com.firstmeridian.ipmgmt.persistence.IpPoolEntity;
import com.firstmeridian.ipmgmt.service.IpPoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("ipmgmt")
@Api(value = "IpResourceManagement", description = "Manages IpAddress in IP pool")
public class IpManagementController {

	@Autowired
	private IpPoolService IpPoolService;
	
	@GetMapping("/ippools")
	@ApiOperation(value = "Returns All IpAddress present in the Ip pool")
	public List<IpPoolEntity> findAll(){
		return IpPoolService.ipPools().orElseThrow(() -> new BusinessException("NOT_FOUND","IpPool is empty !"));
	}
	@GetMapping("/ippools/{poolId}")
	@ApiOperation(value = "Returns IpAddress present in the Ip pool based on poolid",consumes = "poolId")
	public IpPoolEntity findIpPool(@PathVariable Long poolId){
		return IpPoolService.getPoolById(poolId).orElseThrow(() -> new BusinessException("NOT_FOUND","IpPool not found with id " + poolId));
	}
	@PostMapping("/ippools/generate/{totalNumber}")
	public List<IpPoolEntity> produceIpAddress(@PathVariable int totalNumber){
		return IpPoolService.generateIpPool(totalNumber).orElseThrow(() -> new BusinessException("OutOfSpace","IpPool is full plz try with"));
	}
}
