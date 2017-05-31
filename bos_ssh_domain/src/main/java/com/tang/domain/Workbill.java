package com.tang.domain;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Workbill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "workbill", catalog = "bos_ssh")

public class Workbill implements java.io.Serializable {

	// Fields

	private String id;
	private Noticebill noticebill;
	private Staff staff;
	private String type;
	private String pickstate;
	private Date buildtime;
	private Date attachtime;
	private String remark;

	public static final String TYPE_NEW="新单";
	public static final String TYPE_APPEND="追单";
	public static final String TYPE_UPDATE="改单";
	public static final String TYPE_DELETE="销单";

	public static final String PICKSTATE_PRE="未取件";
	public static final String PICKSTATE_ING="取件中";
	public static final String PICKSTATE_HAD="已取件";

	// Constructors

	/** default constructor */
	public Workbill() {
	}

	/** minimal constructor */
	public Workbill(String type, String pickstate, Date buildtime) {
		this.type = type;
		this.pickstate = pickstate;
		this.buildtime = buildtime;
	}

	/** full constructor */
	public Workbill(Noticebill noticebill, Staff staff, String type, String pickstate, Date buildtime, Date attachtime,
			String remark) {
		this.noticebill = noticebill;
		this.staff = staff;
		this.type = type;
		this.pickstate = pickstate;
		this.buildtime = buildtime;
		this.attachtime = attachtime;
		this.remark = remark;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "id", unique = true, nullable = false, length = 52)

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "noticebill_id")

	public Noticebill getNoticebill() {
		return this.noticebill;
	}

	public void setNoticebill(Noticebill noticebill) {
		this.noticebill = noticebill;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id")

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Column(name = "type", nullable = false, length = 8)

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "pickstate", nullable = false, length = 12)

	public String getPickstate() {
		return this.pickstate;
	}

	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}

	@Column(name = "buildtime", nullable = false, length = 8)

	public Date getBuildtime() {
		return this.buildtime;
	}

	public void setBuildtime(Date buildtime) {
		this.buildtime = buildtime;
	}

	@Column(name = "attachtime", length = 8)

	public Date getAttachtime() {
		return this.attachtime;
	}

	public void setAttachtime(Date attachtime) {
		this.attachtime = attachtime;
	}

	@Column(name = "remark", length = 16)

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}