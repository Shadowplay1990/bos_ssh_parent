package com.tang.domain;
// default package

import org.hibernate.Criteria;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Subarea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "subarea", catalog = "bos_ssh")

public class Subarea implements java.io.Serializable {

	// Fields

	private String subareaId;
	private Region region;
	private Decidedzone decidedzone;
	private String addresskey;
	private String startcode;
	private String endcode;
	private String singlecode;
	private String position;

	// Constructors

	/** default constructor */
	public Subarea() {
	}

	/** full constructor */
	public Subarea(Region region, Decidedzone decidedzone, String addresskey, String startcode, String endcode,
			String singlecode, String position) {
		this.region = region;
		this.decidedzone = decidedzone;
		this.addresskey = addresskey;
		this.startcode = startcode;
		this.endcode = endcode;
		this.singlecode = singlecode;
		this.position = position;
	}

	// Property accessors
	@GenericGenerator(name="Suuid",strategy = "uuid")
	@Id
	@GeneratedValue(generator = "Suuid")

	@Column(name = "subareaId", unique = true, nullable = false, length = 32)

	public String getSubareaId() {
		return this.subareaId;
	}

	public void setSubareaId(String subareaId) {
		this.subareaId = subareaId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "areaId")

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "decidedzoneId")

	public Decidedzone getDecidedzone() {
		return this.decidedzone;
	}

	public void setDecidedzone(Decidedzone decidedzone) {
		this.decidedzone = decidedzone;
	}

	@Column(name = "addresskey", nullable = false, length = 88)

	public String getAddresskey() {
		return this.addresskey;
	}

	public void setAddresskey(String addresskey) {
		this.addresskey = addresskey;
	}

	@Column(name = "startcode", nullable = false, length = 28)

	public String getStartcode() {
		return this.startcode;
	}

	public void setStartcode(String startcode) {
		this.startcode = startcode;
	}

	@Column(name = "endcode", nullable = false, length = 28)

	public String getEndcode() {
		return this.endcode;
	}

	public void setEndcode(String endcode) {
		this.endcode = endcode;
	}

	@Column(name = "singlecode", nullable = false, length = 1)

	public String getSinglecode() {
		return this.singlecode;
	}

	public void setSinglecode(String singlecode) {
		this.singlecode = singlecode;
	}

	@Column(name = "position", nullable = false, length = 216)

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}