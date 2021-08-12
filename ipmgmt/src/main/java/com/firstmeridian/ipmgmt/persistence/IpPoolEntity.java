package com.firstmeridian.ipmgmt.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IPPOOL")
@Data
public class IpPoolEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4238725722002364033L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden = true)
	private Long poolId;
	private String description;
	private int totalCapacity;// has to be provided in API call
	private int usedCapacity;// count(row)  available = totalcapacity - usedcapacity
	private int lowerBound;
	private int uppperBound;
	//@OneToMany(mappedBy = "ipPool", /* fetch = FetchType.LAZY, */cascade = CascadeType.ALL)
	//@JsonManagedReference
	//@OneToMany(mappedBy = "ipPool",fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@OneToMany(mappedBy = "ipPool",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	//@ApiModelProperty(hidden = true)
	@JsonIgnore
	private List<IPAddress> ipAddresses;
	
	public List<IPAddress> getIpAddresses() {
		return ipAddresses;
	}
	public void setIpAddresses(List<IPAddress> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}
	public IpPoolEntity(String description, int totalCapacity, int usedCapacity, int lowerBound,
			int uppperBound) {
		super();
		this.description = description;
		this.totalCapacity = totalCapacity;
		this.usedCapacity = usedCapacity;
		this.lowerBound = lowerBound;
		this.uppperBound = uppperBound;
	}
	public IpPoolEntity() {
		super();
	}
	
}
