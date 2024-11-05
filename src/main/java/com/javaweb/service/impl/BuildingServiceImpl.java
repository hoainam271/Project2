package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConvert;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired 
	private BuildingDTOConverter buildingDTOConverter;
	
	@Autowired
	private BuildingSearchBuilderConvert buildingSearchBuilderConvert;
	@Override
	public List<BuildingDTO> findAll(Map <String,Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		BuildingSearchBuilder buildingSearchBuilder =buildingSearchBuilderConvert.toBuildingSearchBuilder(params, typeCode); 
//		List<BuildingEntity> buildingEntities = buildingRepository.findAll(); // hàm findAll này của StringDataJPA khi ta khai báo bên BuildingRepository
//		BuildingEntity buildingEntities = buildingRepository.findById(1L).get();  // tìm theo ID
		
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder); // khi custom thì nó ra
		List<BuildingDTO> result= new ArrayList<BuildingDTO>();
		for(BuildingEntity item : buildingEntities){
			BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
			
			result.add(building);
		}
		
		return result;
	}

}
