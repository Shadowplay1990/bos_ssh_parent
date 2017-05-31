package com.tang.domain;
// default package

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Noticebill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "noticebill", catalog = "bos_ssh")

public class Noticebill implements java.io.Serializable {

	// Fields

	private String id;
	private Staff staff;
	private User user;
	private String userId;
	private String customerId;
	private String delegater;
	private String telephone;
	private String pickaddress;
	private String arrivecity;
	private String product;
	private Date pickdate;
	private Integer num;
	private float weight;
	private String volume;
	private String orderType;
	private Set<Workbill> workbills = new HashSet<Workbill>(0);

	public static final String ORDERTYPE_AUTO="自动分单";
	public static final String ORDERTYPE_MAN="人工分单";

	// Constructors

	/** default constructor */
	public Noticebill() {
	}

	/** minimal constructor */
	public Noticebill(String telephone, String pickaddress, String arrivecity, String product, Date pickdate,
			Integer num, float weight, String orderType) {
		this.telephone = telephone;
		this.pickaddress = pickaddress;
		this.arrivecity = arrivecity;
		this.product = product;
		this.pickdate = pickdate;
		this.num = num;
		this.weight = weight;
		this.orderType = orderType;
	}

	/** full constructor */
	public Noticebill(Staff staff, User user, String userId, String customerId, String delegater, String telephone,
			String pickaddress, String arrivecity, String product, Date pickdate, Integer num, float weight,
			String volume, String orderType, Set<Workbill> workbills) {
		this.staff = staff;
		this.user = user;
		this.userId = userId;
		this.customerId = customerId;
		this.delegater = delegater;
		this.telephone = telephone;
		this.pickaddress = pickaddress;
		this.arrivecity = arrivecity;
		this.product = product;
		this.pickdate = pickdate;
		this.num = num;
		this.weight = weight;
		this.volume = volume;
		this.orderType = orderType;
		this.workbills = workbills;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "id", unique = true, nullable = false, length = 38)

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id")

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "user_id", length = 38)

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "customer_id", length = 38)

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "delegater", length = 42)

	public String getDelegater() {
		return this.delegater;
	}

	public void setDelegater(String delegater) {
		this.delegater = delegater;
	}

	@Column(name = "telephone", nullable = false, length = 22)

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "pickaddress", nullable = false, length = 48)

	public String getPickaddress() {
		return this.pickaddress;
	}

	public void setPickaddress(String pickaddress) {
		this.pickaddress = pickaddress;
	}

	@Column(name = "arrivecity", nullable = false, length = 12)

	public String getArrivecity() {
		return this.arrivecity;
	}

	public void setArrivecity(String arrivecity) {
		this.arrivecity = arrivecity;
	}

	@Column(name = "product", nullable = false, length = 26)

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pickdate", nullable = false, length = 10)

	public Date getPickdate() {
		return this.pickdate;
	}

	public void setPickdate(Date pickdate) {
		this.pickdate = pickdate;
	}

	@Column(name = "num", nullable = false)

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "weight", nullable = false, precision = 12, scale = 0)

	public float getWeight() {
		return this.weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Column(name = "volume", length = 32)

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Column(name = "orderType",length = 22)

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "noticebill")

	public Set<Workbill> getWorkbills() {
		return this.workbills;
	}

	public void setWorkbills(Set<Workbill> workbills) {
		this.workbills = workbills;
	}

}