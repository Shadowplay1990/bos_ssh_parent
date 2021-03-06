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
 * Staff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "staff", catalog = "bos_ssh")
@JsonAutoDetect
public class Staff implements java.io.Serializable {

	// Fields

	private String staffid;
	private String name;
	private String cellphone;
	private String haspad;
	private String deltag;
	private String station;
	private String standard;
	@JsonIgnore
	private Set<Decidedzone> decidedzones = new HashSet<Decidedzone>(0);

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff(String name, String cellphone, String haspad, String deltag, String station, String standard) {
		this.name = name;
		this.cellphone = cellphone;
		this.haspad = haspad;
		this.deltag = deltag;
		this.station = station;
		this.standard = standard;
	}

	/** full constructor */
	public Staff(String name, String cellphone, String haspad, String deltag, String station, String standard,
			Set<Decidedzone> decidedzones) {
		this.name = name;
		this.cellphone = cellphone;
		this.haspad = haspad;
		this.deltag = deltag;
		this.station = station;
		this.standard = standard;
		this.decidedzones = decidedzones;
	}

	// Property accessors
	@GenericGenerator(name="Suuid",strategy = "uuid")
	@Id
	@GeneratedValue(generator = "Suuid")

	@Column(name = "staffid", unique = true, nullable = false, length = 32)

	public String getStaffid() {
		return this.staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	@Column(name = "name", nullable = false, length = 22)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "cellphone", nullable = false, length = 22)

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "haspad", nullable = false, length = 1)

	public String getHaspad() {
		return this.haspad;
	}

	public void setHaspad(String haspad) {
		this.haspad = haspad;
	}

	@Column(name = "deltag", nullable = false, length = 1)

	public String getDeltag() {
		return this.deltag;
	}

	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}

	@Column(name = "station", nullable = false, length = 38)

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	@Column(name = "standard", nullable = false, length = 100)

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staff")

	public Set<Decidedzone> getDecidedzones() {
		return this.decidedzones;
	}

	public void setDecidedzones(Set<Decidedzone> decidedzones) {
		this.decidedzones = decidedzones;
	}

}