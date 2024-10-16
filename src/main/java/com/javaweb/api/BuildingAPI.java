package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;


@RestController
public class BuildingAPI {

//@RequestMapping(value="/api/building/", method = RequestMethod.GET)
//	public void getBuilding() {
//		System.out.print("oke");
//	}
	
	
	
	
//	@RequestMapping(value="/api/building/", method = RequestMethod.GET)
//	public void getBuilding(@RequestParam(value="name", required= false) String name,
//							@RequestParam(value="numberOfBasement", required=false) Integer numberOfBasement) {
//		// Dùng lớp Integer thay cho int bởi vì int không nhận giá trị NULL
//		System.out.print(name+" " + numberOfBasement);
//		
//	}
	
	
	
	
//	@RequestMapping(value="/api/building/", method = RequestMethod.POST)
//	public void getBuilding2(@RequestParam Map<String,String> params ) {
//		System.out.print("Oke");
//		
//	}
	
//	@RequestMapping(value="/api/building/", method = RequestMethod.POST)
//	public void getBuilding2(@RequestBody Map<String,String> params) {
//		System.out.println("ok");
//	}
	
	
//	@RequestMapping(value="/api/building/", method = RequestMethod.POST)
//	public void getBuilding2(@RequestBody BuildingDTO building ) {
//		//Nếu RequestBody thì dùng javaBean chứ kh dùng map
//		System.out.print("Oke");
//	}
	
	
//	@RequestMapping(value="/api/building/", method = RequestMethod.GET)
//	@ResponseBody
//	public BuildingDTO getBuilding(@RequestParam(name="name", required=false) String name,
//							@RequestParam(name="numberOfBasement", required=false) Integer numberOfBasement,
//							@RequestParam(name="ward", required=false) String ward
//							) {
//		BuildingDTO result= new BuildingDTO();
//		result.setName(name);
//		result.setNumbeOfBasement(numberOfBasement);
//		result.setWard(ward);
//		return result;
//	}
	
	
	// Gửi lên font end dưới dạng json
//	@RequestMapping(value="/api/building/", method = RequestMethod.GET)
//	@ResponseBody
//	// Nếu kh dùng @RestponseBody thì ban đầu phải dùng @RestController chứ kh dùng @Controller
//	public List<BuildingDTO> getBuilding(@RequestParam(value="name", required= false) String name,
//							@RequestParam(value="numberOfBasement", required=false) Integer numberOfBasement) {
//		// Dùng lớp Integer thay cho int bởi vì int không nhận giá trị NULL
//		List<BuildingDTO> listBuildings = new ArrayList<>();
//		BuildingDTO buildingDTO1 = new BuildingDTO();
//		buildingDTO1.setName("abc building");
//		buildingDTO1.setNumbeOfBasement(3);
//		buildingDTO1.setStreet("HTM");
//		buildingDTO1.setWard("Ha Noi");
//		
//		BuildingDTO buildingDTO2 = new BuildingDTO();
//		buildingDTO2.setName("def building");
//		buildingDTO2.setNumbeOfBasement(6);
//		buildingDTO2.setStreet("PC");
//		buildingDTO2.setWard("Ha Noi");
//		
//		listBuildings.add(buildingDTO2);
//		listBuildings.add(buildingDTO1);
//		
//		return listBuildings;
//		
//	}
	
	
//	@RequestMapping(value="/api/building/", method = RequestMethod.GET)
//	public Object getBuilding(@RequestParam(value="name", required= false) String name,
//			@RequestParam(value="numberOfBasement", required=false) Integer numberOfBasement) {
//		
//		
//		try {
//			System.out.println(5/1);
//		}
//		catch(Exception e){
//			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//			errorResponseDTO.setError(e.getMessage());
//			List<String> details = new ArrayList<>();
//			details.add("So nguyen lam sao chia cho so 0 duoc");
//			errorResponseDTO.setDetail(details);
//			return errorResponseDTO;
//		}
//		BuildingDTO buildingDTO1 = new BuildingDTO();
//		buildingDTO1.setName("abc building");
//		buildingDTO1.setNumbeOfBasement(3);
//		buildingDTO1.setStreet("HTM");
//		buildingDTO1.setWard("Ha Noi");
//		
//		return buildingDTO1;
//
//}
	
//	@PostMapping(value="/api/building/")
//	public Object getBuilding(@RequestBody BuildingDTO building) {
//		try {
//			validate(building);
//		}
//		catch(FieldRequiredException e){
//			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//			errorResponseDTO.setError(e.getMessage());
//			List<String> details = new ArrayList<>();
//			details.add("Check lai name hoac numberofbasement di boi vi dang bi null");
//			errorResponseDTO.setDetail(details);
//			return errorResponseDTO;
//		}
//		BuildingDTO buildingDTO1 = new BuildingDTO();
//		buildingDTO1.setName("abc building");
//		buildingDTO1.setNumbeOfBasement(3);
//		buildingDTO1.setStreet("HTM");
//		buildingDTO1.setWard("Ha Noi");
//		
//		return buildingDTO1;
//	}
		
//	@PostMapping(value="/api/building/")
//	public Object getBuilding(@RequestBody BuildingDTO building) {
//		System.out.print(5/1);
//		return null;
//	}
//	
//	
//	@PostMapping(value="/api/building/")
//	public Object getBuilding(@RequestBody BuildingDTO building) {
//		validate(building);
//		return null;
//	}
	
	
	// Hàm để bắt lỗi
//	public void validate(BuildingDTO buildingDTO) {
//		if(buildingDTO.getName() == null ||buildingDTO.getName().equals("") || buildingDTO.getNumbeOfBasement()==null) {
//			throw new FieldRequiredException("name or numberofbasement is null");
//		}
//	}
	
	
	
	
	// CÁCH KẾT NỐI CƠ SỞ DỮ LIỆU
//	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
//	static final String USER = "root"; 
//	static final String PASS = "270104"; 
//	
//	@GetMapping(value="/api/building/")
//	public List<BuildingDTO> getBuilding(@RequestParam(name="name") String name){
//		
//		String sql= "SELECT * FROM building b WHERE name like '%" + name + "%' ";
//		List<BuildingDTO> result = new ArrayList<>();
//		try(Connection conn =DriverManager.getConnection(DB_URL, USER, PASS);
//				Statement stmt=conn.createStatement();
//				ResultSet rs=stmt.executeQuery(sql);){
//			while(rs.next()) {
//				BuildingDTO building = new BuildingDTO();
//				building.setName(rs.getString("name"));
//				building.setStreet(rs.getString("street"));
//				building.setWard(rs.getString("ward"));
//				building.setNumberOfBasement(rs.getInt("numberofbasement"));
//				result.add(building);
//			}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				System.out.println("Connected database failed ... ");
//			}
//		return result;
//	}
	
	
	
//	@RequestParam(name="name" , required= false) String name
//	,@RequestParam(name="street", required = false) String street
//	,@RequestParam(name="ward", required= false) Long ward 
//	,@RequestParam(name="districtid", required= false) Integer districtId 
//	,@RequestParam(name="numberOfBasement", required= false) Integer numberOfBasement 
//	,@RequestParam(name="floorArea", required= false) Integer floorArea
//	,@RequestParam(name="rentPrice", required= false) Integer rentPrice
//	,@RequestParam(name="rentPriceDescription", required= false) String rentPriceDescription 
//	,@RequestParam(name="managerName", required= false) String managerName 
//	,@RequestParam(name="managerPhoneNumber", required= false) Long managerPhoneNumber
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value="/api/building/")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String,Object> params,
			@RequestParam(name="typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> result = buildingService.findAll(params, typeCode);
		return result;
	}
	
	

	
//	@DeleteMapping(value="/api/building/{id}/{name}")
//	public void deleteBuilding(@PathVariable Integer id,
//								@PathVariable String name) {
//		System.out.println("da xoa toa nha co id la "+ id + ", " +name+" roi nhe");
//	}
//	
//	
	
	
	
	
}



