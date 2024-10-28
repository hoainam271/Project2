package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
//		BuildingDTO building = new BuildingDTO();
//		building.setName(item.getName());
//		building.setStreet(item.getStreet());
//		building.setWard(item.getWard());
//		building.setDistrictid(item.getDistrictid());
//		building.setNumberOfBasement(item.getNumberOfBasement());
//		building.setFloorarea(item.getFloorarea());
//		building.setRentprice(item.getRentprice());
//		building.setRentpricedescription(item.getRentpricedescription());
//		building.setManagername(item.getManagername());
//		building.setManagerphonenumber(item.getManagerPhoneNumber());
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		
//		DistrictEntity districtEntity = districtRepository.findNameByID(item.getDistrictid());
		List<RentAreaEntity> rentAreas = item.getItems();
		String areaResult= rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResult);
		return building;
	}
	
}
