package com.tang.domain;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Workordermanager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "workordermanager", catalog = "bos_ssh")

public class Workordermanager implements java.io.Serializable {

	// Fields

	private String id;
	private String arrivecity;
	private String product;
	private Integer num;
	private float weight;
	private String floadrepr;
	private String prodtimelimit;
	private String prodtype;
	private String sendername;
	private String senderphone;
	private String senderaddr;
	private String receviename;
	private String receviephon;
	private String recevieaddr;
	private double actlweit;
	private String managercheck;
	private Date updatetime;

	// Constructors

	/** default constructor */
	public Workordermanager() {
	}

	/** minimal constructor */
	public Workordermanager(String arrivecity, String product, Integer num, float weight, String floadrepr,
			String prodtype, String sendername, String senderphone, String senderaddr, String receviename,
			String receviephon, String recevieaddr) {
		this.arrivecity = arrivecity;
		this.product = product;
		this.num = num;
		this.weight = weight;
		this.floadrepr = floadrepr;
		this.prodtype = prodtype;
		this.sendername = sendername;
		this.senderphone = senderphone;
		this.senderaddr = senderaddr;
		this.receviename = receviename;
		this.receviephon = receviephon;
		this.recevieaddr = recevieaddr;
	}

	/** full constructor */
	public Workordermanager(String arrivecity, String product, Integer num, float weight, String floadrepr,
			String prodtimelimit, String prodtype, String sendername, String senderphone, String senderaddr,
			String receviename, String receviephon, String recevieaddr, double actlweit, String managercheck,
			Date updatetime) {
		this.arrivecity = arrivecity;
		this.product = product;
		this.num = num;
		this.weight = weight;
		this.floadrepr = floadrepr;
		this.prodtimelimit = prodtimelimit;
		this.prodtype = prodtype;
		this.sendername = sendername;
		this.senderphone = senderphone;
		this.senderaddr = senderaddr;
		this.receviename = receviename;
		this.receviephon = receviephon;
		this.recevieaddr = recevieaddr;
		this.actlweit = actlweit;
		this.managercheck = managercheck;
		this.updatetime = updatetime;
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

	@Column(name = "arrivecity", nullable = false, length = 16)

	public String getArrivecity() {
		return this.arrivecity;
	}

	public void setArrivecity(String arrivecity) {
		this.arrivecity = arrivecity;
	}

	@Column(name = "product", nullable = false, length = 28)

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
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

	@Column(name = "floadrepr", nullable = false, length = 32)

	public String getFloadrepr() {
		return this.floadrepr;
	}

	public void setFloadrepr(String floadrepr) {
		this.floadrepr = floadrepr;
	}

	@Column(name = "prodtimelimit", length = 26)

	public String getProdtimelimit() {
		return this.prodtimelimit;
	}

	public void setProdtimelimit(String prodtimelimit) {
		this.prodtimelimit = prodtimelimit;
	}

	@Column(name = "prodtype", nullable = false, length = 32)

	public String getProdtype() {
		return this.prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	@Column(name = "sendername", nullable = false, length = 16)

	public String getSendername() {
		return this.sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	@Column(name = "senderphone", nullable = false, length = 26)

	public String getSenderphone() {
		return this.senderphone;
	}

	public void setSenderphone(String senderphone) {
		this.senderphone = senderphone;
	}

	@Column(name = "senderaddr", nullable = false, length = 38)

	public String getSenderaddr() {
		return this.senderaddr;
	}

	public void setSenderaddr(String senderaddr) {
		this.senderaddr = senderaddr;
	}

	@Column(name = "receviename", nullable = false, length = 18)

	public String getReceviename() {
		return this.receviename;
	}

	public void setReceviename(String receviename) {
		this.receviename = receviename;
	}

	@Column(name = "receviephon", nullable = false, length = 22)

	public String getReceviephon() {
		return this.receviephon;
	}

	public void setReceviephon(String receviephon) {
		this.receviephon = receviephon;
	}

	@Column(name = "recevieaddr", nullable = false, length = 32)

	public String getRecevieaddr() {
		return this.recevieaddr;
	}

	public void setRecevieaddr(String recevieaddr) {
		this.recevieaddr = recevieaddr;
	}

	@Column(name = "actlweit", precision = 22, scale = 0)

	public double getActlweit() {
		return this.actlweit;
	}

	public void setActlweit(double actlweit) {
		this.actlweit = actlweit;
	}

	@Column(name = "managercheck", length = 12)

	public String getManagercheck() {
		return this.managercheck;
	}

	public void setManagercheck(String managercheck) {
		this.managercheck = managercheck;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updatetime", length = 10)

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}