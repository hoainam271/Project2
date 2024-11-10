package com.javaweb.repository.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity // đánh dấu nó là 1 entity
@Table(name="building") // đánh dấu nó là table có tên là building
public class BuildingEntity {
	
	@Id // anotation này để đánh dấu nó là primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // tự động tăng dần
	private Long id;
	
	@Column(name="name")// anotation này để nó tạo 1 cột column
	private String name;
	
	@Column(name="street")
	private String street;
	
	@Column(name="ward")
	private String ward;
	
	
	
	@Column(name="structure")
	private String struture;
	
	@Column(name="numberofbasement")
	private Integer numberOfBasement;
	
	@Column(name="floorarea")
	private Integer floorarea;
	
	@Column(name="direction")
	private String direction;
	
	@Column(name="level")
	private Integer level;
	
	@Column(name="rentprice")
	private Integer rentprice;
	
	@Column(name="rentpricedescription")
	private String rentpricedescription;
	
	@Column(name="servicefee")
	private Integer servicefee;
	
	@Column(name="carfee")
	private Integer carfee;
	
	@Column(name="motorbikefee")
	private Integer motorbikefee;
	
	@Column(name="overtimefee")
	private Integer overtimefee;
	
	@Column(name="waterfee")
	private Integer waterfee;
	
	@Column(name="electricityfee")
	private Integer electricityfee;
	
	@Column(name="deposit")
	private Integer deposit;
	
	@Column(name="payment")
	private Integer payment;
	
	@Column(name="renttime")
	private Integer renttime;
	
	@Column(name="decorationtime")
	private Integer decorationtime;
	
	@Column(name="brokeragefee")
	private Integer brokeragefee;
	
	@Column(name="note")
	private String note;
	
	@Column(name="linkofbuilding")
	private String linkofbuilding;
	
	@Column(name="map")
	private String map;
	
	@Column(name="image")
	private String image;
	
	@Column(name="createddate")
	private Date createddate;
	
	@Column(name="modifieddate" )
	private Date modifieddate;
	
	@Column(name="createdby")
	private String createdby;
	
	@Column(name="managername")
	private String managername;
	
	@Column(name="managerphonenumber")
	private Long managerphonenumber;
	
	// khi join column nó sẽ tự tạo luôn cái column 
	@ManyToOne
	@JoinColumn(name="districtid")
	private DistrictEntity district; // bên kia mapped như nào thì bên này phải như thế
	
	public List<RentTypeEntity> getRenttypes() {
		return renttypes;
	}
	public void setRenttypes(List<RentTypeEntity> renttypes) {
		this.renttypes = renttypes;
	}
	@OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
	private List<RentAreaEntity> items = new ArrayList<>();
	
	@ManyToMany(mappedBy="buildings",fetch=FetchType.LAZY)
	private List<RentTypeEntity> renttypes= new ArrayList<>();
	
	public List<RentTypeEntity> getRentTypes() {
		return renttypes;
	}
	public void setRentTypes(List<RentTypeEntity> renttypes) {
		this.renttypes = renttypes;
	}
	public List<RentAreaEntity> getItems() {
		return items;
	}
	public void setItems(List<RentAreaEntity> items) {
		this.items = items;
	}
	public Long getManagerphonenumber() {
		return managerphonenumber;
	}
	public void setManagerphonenumber(Long managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
	}
	public DistrictEntity getDistrict() {
		return district;
	}
	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	
	public String getStruture() {
		return struture;
	}
	public void setStruture(String struture) {
		this.struture = struture;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public Integer getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(Integer floorarea) {
		this.floorarea = floorarea;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getRentprice() {
		return rentprice;
	}
	public void setRentprice(Integer rentprice) {
		this.rentprice = rentprice;
	}
	public String getRentpricedescription() {
		return rentpricedescription;
	}
	public void setRentpricedescription(String rentpricedescription) {
		this.rentpricedescription = rentpricedescription;
	}
	public Integer getServicefee() {
		return servicefee;
	}
	public void setServicefee(Integer servicefee) {
		this.servicefee = servicefee;
	}
	public Integer getCarfee() {
		return carfee;
	}
	public void setCarfee(Integer carfee) {
		this.carfee = carfee;
	}
	public Integer getMotorbikefee() {
		return motorbikefee;
	}
	public void setMotorbikefee(Integer motorbikefee) {
		this.motorbikefee = motorbikefee;
	}
	public Integer getOvertimefee() {
		return overtimefee;
	}
	public void setOvertimefee(Integer overtimefee) {
		this.overtimefee = overtimefee;
	}
	public Integer getWaterfee() {
		return waterfee;
	}
	public void setWaterfee(Integer waterfee) {
		this.waterfee = waterfee;
	}
	public Integer getElectricityfee() {
		return electricityfee;
	}
	public void setElectricityfee(Integer electricityfee) {
		this.electricityfee = electricityfee;
	}
	public Integer getDeposit() {
		return deposit;
	}
	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public Integer getRenttime() {
		return renttime;
	}
	public void setRenttime(Integer renttime) {
		this.renttime = renttime;
	}
	public Integer getDecorationtime() {
		return decorationtime;
	}
	public void setDecorationtime(Integer decorationtime) {
		this.decorationtime = decorationtime;
	}
	public Integer getBrokeragefee() {
		return brokeragefee;
	}
	public void setBrokeragefee(Integer brokeragefee) {
		this.brokeragefee = brokeragefee;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getLinkofbuilding() {
		return linkofbuilding;
	}
	public void setLinkofbuilding(String linkofbuilding) {
		this.linkofbuilding = linkofbuilding;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public Date getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public Long getManagerPhoneNumber() {
		return managerphonenumber;
	}
	public void setManagerPhoneNumber(Long managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
	}

	
	
	
}
