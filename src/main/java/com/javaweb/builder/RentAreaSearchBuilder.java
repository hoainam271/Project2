package com.javaweb.builder;

public class RentAreaSearchBuilder {
	private String value;
	private Integer buildingId;
	public RentAreaSearchBuilder(Builder builder) {
		this.value=builder.value;
		this.buildingId=builder.buildingId;
	}
	public String getValue() {
		return value;
	}
	public Integer getBuildingId() {
		return buildingId;
	}
	public static class Builder {
		private String value;
		private Integer buildingId;
		public Builder setValue(String value) {
			this.value = value;
			return this;
		}
		public Builder setBuildingId(Integer buildingId) {
			this.buildingId = buildingId;
			return this;
		}
		public RentAreaSearchBuilder build() {
			return new RentAreaSearchBuilder(this);
		}
	}
}
