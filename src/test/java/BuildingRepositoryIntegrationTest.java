//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.javaweb.api.BuildingAPI;
//import com.javaweb.model.BuildingDTO;
//import com.javaweb.service.BuildingService;
//
//@WebMvcTest(BuildingAPI.class)
//public class BuildingAPITest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BuildingService buildingService;
//
//    @Test
//    public void testGetBuilding() throws Exception {
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", "building");
//
//        List<String> typeCode = Arrays.asList("noi-that", "tang-tret");
//
//        BuildingDTO building1 = new BuildingDTO();
//        building1.setName("Nam Giao Building Tower");
//        BuildingDTO building2 = new BuildingDTO();
//        building2.setName("ACM Tower");
//
//        List<BuildingDTO> buildings = Arrays.asList(building1, building2);
//
//        when(buildingService.findAll(params, typeCode)).thenReturn(buildings);
//
//        mockMvc.perform(get("/api/building/")
//                .param("name", "building")
//                .param("typeCode", "noi-that", "tang-tret"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("Nam Giao Building Tower"))
//                .andExpect(jsonPath("$[1].name").value("ACM Tower"));
//    }
//
//    @Test
//    public void testGetBuilding_NoResults() throws Exception {
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", "nonexistent");
//
//        List<String> typeCode = Arrays.asList("invalid-code");
//
//        when(buildingService.findAll(params, typeCode)).thenReturn(Arrays.asList());
//
//        mockMvc.perform(get("/api/building/")
//                .param("name", "nonexistent")
//                .param("typeCode", "invalid-code"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isEmpty());
//    }
//
//    @Test
//    public void testGetBuilding_MissingParams() throws Exception {
//        when(buildingService.findAll(new HashMap<>(), Arrays.asList())).thenReturn(Arrays.asList());
//
//        mockMvc.perform(get("/api/building/"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isEmpty());
//    }
//
//    @Test
//    public void testGetBuilding_ServiceThrowsException() throws Exception {
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", "building");
//
//        List<String> typeCode = Arrays.asList("noi-that", "tang-tret");
//
//        when(buildingService.findAll(params, typeCode)).thenThrow(new RuntimeException("Service error"));
//
//        mockMvc.perform(get("/api/building/")
//                .param("name", "building")
//                .param("typeCode", "noi-that", "tang-tret"))
//                .andExpect(status().isInternalServerError());
//    }
//
//    @Test
//    public void testGetBuilding_InvalidRequest() throws Exception {
//        mockMvc.perform(get("/api/building/")
//                .param("invalidParam", "value"))
//                .andExpect(status().isBadRequest());
//    }
//}

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@DataJpaTest
public class BuildingRepositoryIntegrationTest {

    @Autowired
    private BuildingRepository buildingRepository;

    @Test
    public void testSaveBuilding() {
        // Tạo một building và lưu vào database
        BuildingEntity building = new BuildingEntity();
        building.setName("Tòa nhà A");
        building.setWard("123 Đường ABC");
        building.setManagername("Hoài Nam");;

        BuildingEntity savedBuilding = buildingRepository.save(building);

        // Kiểm tra xem building đã được lưu và có ID
        assertNotNull(savedBuilding.getId());
        assertEquals("Tòa nhà A", savedBuilding.getName());
    }

    @Test
    public void testFindBuildingById() throws InterruptedException {
        // Tạo và lưu một building mới
    	BuildingEntity building = new BuildingEntity();
        building.setName("ACM Tower");
        building.setWard("Phường 4");
        building.setManagername("Chú Thuận");
        Thread.sleep(2000);

        BuildingEntity savedBuilding = buildingRepository.save(building);
        assertNotNull(buildingRepository, "BuildingRepository should not be null");

        System.out.print(savedBuilding);
        // Tìm building theo ID
        Optional<BuildingEntity> foundBuilding = buildingRepository.findById(savedBuilding.getId());

        // Kiểm tra xem building có tồn tại trong database
        assertTrue(foundBuilding.isPresent());
        assertEquals("ACM Tower", foundBuilding.get().getName());
    }

    @Test
    public void testUpdateBuilding() {
        // Tạo và lưu một building
    	BuildingEntity building = new BuildingEntity();
        building.setName("Tòa nhà C");
        building.setWard("789 Đường DEF");
        building.setManagername("Văn Hiếu");

        BuildingEntity savedBuilding = buildingRepository.save(building);

        // Cập nhật thông tin building
        savedBuilding.setManagername("Bá Minh");
        BuildingEntity updatedBuilding = buildingRepository.save(savedBuilding);

        // Kiểm tra xem giá trị đã được cập nhật
        assertEquals("Bá Minh", updatedBuilding.getManagername());
    }

    @Test
    public void testDeleteBuilding() throws InterruptedException {
        // Tạo và lưu một building
    	BuildingEntity building = new BuildingEntity();
        building.setName("Building kkk");
        building.setWard("Phường 7");
        building.setManagername("Anh Long");
        Thread.sleep(2000);
        BuildingEntity savedBuilding = buildingRepository.save(building);

        buildingRepository.deleteById(savedBuilding.getId());

        // Kiểm tra xem building đã bị xóa
        Optional<BuildingEntity> deletedBuilding = buildingRepository.findById(savedBuilding.getId());
        assertFalse(deletedBuilding.isPresent());
    }
}

