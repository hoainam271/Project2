package com.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>,BuildingRepositoryCustom {
	// thêm cái hàm BuildingRepositoryCustom này vào trên là để cutsom JPA để khi ta gọi hàm thì có nhiều hàm để gọi them cái mà ta custom
	
	

	// enxtends JpaRepository<T, ID> 
	//Trong đó T là cái entity, còn ID là kiểu dữ liệu
	
//	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);

	
	// hàm này để xóa 1 list Id
	void deleteByIdIn(Long[] ids); // thêm in vào giúp xóa xả 1 mảng hoặc list

	
	// hàm này tìm kiếm 1 list theo name
	List<BuildingEntity> findByNameContaining(String s);
}
