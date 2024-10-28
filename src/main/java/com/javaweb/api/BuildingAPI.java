package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;


@RestController
@PropertySource("classpath:application.properties")
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	@Value("${dev.do}")
	private String data;
	
	
	@GetMapping(value="/api/building/")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String,Object> params,
			@RequestParam(name="typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> result = buildingService.findAll(params, typeCode);
		return result;
	}
	
	
	// thêm or sửa thì nên đẩy dạng body
	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping(value="/api/building/")
	@Transactional // phải có anotation này thì mới có được persits,...
	public void createBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildEntity = new BuildingEntity();
		buildEntity.setName(buildingRequestDTO.getName());
		buildEntity.setStreet(buildingRequestDTO.getStreet());
		buildEntity.setWard(buildingRequestDTO.getWard());
		
		// Muốn lấy để set District thì phải khai báo nó ra vì nó là khóa được tạo nhờ quan hệ ManyToOne 
		// khi mà nó join bảng
		
		DistrictEntity districtEntity = new DistrictEntity();
		// khai báo ra
		districtEntity.setId(buildingRequestDTO.getDistrictId());
		// phải lấy từ cái districtEntity
		buildEntity.setDistrict(districtEntity);
		
		entityManager.persist(buildEntity);
		//persist là hàm để thêm(thêm dữ liệu)
		System.out.println("ok");
	}

	// Update
	@PutMapping(value="/api/building/")
	@Transactional
	public void updateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildEntity = new BuildingEntity();
		//set cứng id là 1 có định dạng Long
		buildEntity.setId(5L);
		buildEntity.setName(buildingRequestDTO.getName());
		buildEntity.setStreet(buildingRequestDTO.getStreet());
		buildEntity.setWard(buildingRequestDTO.getWard());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictId());
		buildEntity.setDistrict(districtEntity);
		entityManager.merge(buildEntity);
		System.out.println("ok");
	}
	
	
	@DeleteMapping(value="/api/building/{id}")
	@Transactional
	public void deleteBuilding(@PathVariable Long id) { // @PathVariable có thể để cả 1 list : @PathVariable Long[] id
		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
		entityManager.remove(buildingEntity);
		System.out.println(data);
	}
	
	
	
	
	
	
	
	
}



