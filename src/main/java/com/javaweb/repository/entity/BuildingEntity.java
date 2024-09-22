package com.javaweb.repository.entity;

public class BuildingEntity {
	private String name;
	private Integer numbeOfBasement;
	private String ward;
	private String street;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumbeOfBasement() {
		return numbeOfBasement;
	}
	public void setNumbeOfBasement(Integer numbeOfBasement) {
		this.numbeOfBasement = numbeOfBasement;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
}
