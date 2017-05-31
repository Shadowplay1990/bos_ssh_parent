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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Decidedzone entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "decidedzone", catalog = "bos_ssh")
@JsonAutoDetect
public class Decidedzone implements java.io.Serializable {

	// Fields

	private String decidedzoneId;
	private Staff staff;
	private String decidedzoneName;
	@JsonIgnore
	private Set<Subarea> subareas = new HashSet<Subarea>(0);

	// Constructors

	/** default constructor */
	public Decidedzone() {
	}

	/** minimal constructor */
	public Decidedzone(Staff staff, String decidedzoneName) {
		this.staff = staff;
		this.decidedzoneName = decidedzoneName;
	}

	/** full constructor */
	public Decidedzone(Staff staff, String decidedzoneName, Set<Subarea> subareas) {
		this.staff = staff;
		this.decidedzoneName = decidedzoneName;
		this.subareas = subareas;
	}

	// Property accessors
	@GenericGenerator(name="Suuid",strategy = "uuid")
	@Id
	@GeneratedValue(generator = "Suuid")

	@Column(name = "decidedzoneId", unique = true,nullable=false,length = 32)

	public String getDecidedzoneId() {
		return this.decidedzoneId;
	}

	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "staffId")

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Column(name = "decidedzoneName", nullable = false, length = 32)

	public String getDecidedzoneName() {
		return this.decidedzoneName;
	}

	public void setDecidedzoneName(String decidedzoneName) {
		this.decidedzoneName = decidedzoneName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "decidedzone")

	public Set<Subarea> getSubareas() {
		return this.subareas;
	}

	public void setSubareas(Set<Subarea> subareas) {
		this.subareas = subareas;
	}

}