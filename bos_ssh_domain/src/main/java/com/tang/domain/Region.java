package com.tang.domain;
// default package

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Region entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "region", catalog = "bos_ssh")
@JsonAutoDetect
public class Region implements java.io.Serializable {

	// Fields

	private String id;
	private String provice;
	private String city;
	private String area;
	private String postcode;
	private String briefcode;
	private String citycode;
	@JsonIgnore
	private Set<Subarea> subareas = new HashSet<Subarea>(0);

	// Constructors

	/** default constructor */
	public Region() {
	}

	/** minimal constructor */
	public Region(String provice, String city, String area, String postcode, String briefcode, String citycode) {
		this.provice = provice;
		this.city = city;
		this.area = area;
		this.postcode = postcode;
		this.briefcode = briefcode;
		this.citycode = citycode;
	}

	/** full constructor */
	public Region(String provice, String city, String area, String postcode, String briefcode, String citycode,
			Set<Subarea> subareas) {
		this.provice = provice;
		this.city = city;
		this.area = area;
		this.postcode = postcode;
		this.briefcode = briefcode;
		this.citycode = citycode;
		this.subareas = subareas;
	}

	// Property accessors
	@GenericGenerator(name="Suuid",strategy = "uuid")
	@Id
	@GeneratedValue(generator = "Suuid")

	@Column(name = "id", unique = true, nullable = false, length = 32)

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "provice", nullable = false, length = 32)

	public String getProvice() {
		return this.provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	@Column(name = "city", nullable = false, length = 32)

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "area", nullable = false, length = 32)

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "postcode", nullable = false, length = 10)

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "briefcode", nullable = true ,length = 10)

	public String getBriefcode() {
		return this.briefcode;
	}

	public void setBriefcode(String briefcode) {
		this.briefcode = briefcode;
	}

	@Column(name = "citycode", length = 16)

	public String getCitycode() {
		return this.citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "region")

	public Set<Subarea> getSubareas() {
		return this.subareas;
	}

	public void setSubareas(Set<Subarea> subareas) {
		this.subareas = subareas;
	}

}