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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IPPOOL")
@Getter
@Setter
@NoArgsConstructor
@Data
public class IpPoolEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4238725722002364033L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long poolId;
	private String description;
	private int totalCapacity;
	private int usedCapacity;
	private int lowerBound;
	private int uppperBound;
	@OneToMany(mappedBy = "ipPool",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonManagedReference
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
	/*public IpPoolEntity() {
		super();
		// TODO Auto-generated constructor stub
	}*/
	public Long getPoolId() {
		return poolId;
	}
	public void setPoolId(Long poolId) {
		this.poolId = poolId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTotalCapacity() {
		return totalCapacity;
	}
	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
	public int getUsedCapacity() {
		return usedCapacity;
	}
	public void setUsedCapacity(int usedCapacity) {
		this.usedCapacity = usedCapacity;
	}
	public int getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}
	public int getUppperBound() {
		return uppperBound;
	}
	public void setUppperBound(int uppperBound) {
		this.uppperBound = uppperBound;
	}
	
	
}
