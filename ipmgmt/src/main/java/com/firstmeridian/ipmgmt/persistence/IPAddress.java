package com.firstmeridian.ipmgmt.persistence;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "IPAddress")
public class IPAddress implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8532301131399205669L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden = true)
	private Long addId;
	private String ipValue;
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
	//@JoinColumn(name = "poolId"/* ,nullable = false */)
	//@JsonBackReference
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poolId")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonIgnore
	//@ApiModelProperty(hidden = true)
	private IpPoolEntity ipPool;
	
	public IPAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IPAddress(String ipValue, IpPoolEntity ipPool) {
		super();
		this.ipValue = ipValue;
		this.ipPool = ipPool;
	}
	/*
	 * public Long getAddId() { return addId; } public void setAddId(Long addId) {
	 * this.addId = addId; } public String getIpValue() { return ipValue; } public
	 * void setIpValue(String ipValue) { this.ipValue = ipValue; } public
	 * IpPoolEntity getIpPool() { return ipPool; } public void
	 * setIpPool(IpPoolEntity ipPool) { this.ipPool = ipPool; }
	 */
		
	
}
