package com.javaweb.builder;

public class DistrictSearchBuilder {
	private Long id;
	private String code; 
	private String name;
	public DistrictSearchBuilder(Builder builder) {
		this.id=builder.id;
		this.code=builder.code;
		this.name=builder.name;
	}
	public Long getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public static class Builder {
		private Long id;
		private String code; 
		private String name;
		public Builder setId(Long id) {
			this.id = id;
			return this;
		}
		public Builder setCode(String code) {
			this.code = code;
			return this;
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public DistrictSearchBuilder build() {
			return new DistrictSearchBuilder(this);
		}
	}
}
