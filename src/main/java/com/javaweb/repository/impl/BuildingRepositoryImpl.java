package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.connectionJDBCUtil;
import com.javaweb.utils.numberUtil;
import com.javaweb.utils.stringUtil;


@Repository // cái này của spring framework
public class BuildingRepositoryImpl implements BuildingRepository{
	public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
		String staffId= (String)params.get("staffId");
		if(stringUtil.checkString(staffId)) {
			sql.append("INNER JOIN assignmentbuilding a on a.buildingid=b.id ");
		}
		if(typeCode != null && typeCode.size() !=0) {
			sql.append("INNER JOIN buildingrenttype on buildingrenttype.buildingid= b.id ");
			sql.append("INNER JOIN renttype on renttype.id= buildingrenttype.renttypeid ");
		}
		String rentAreaTo= (String)params.get("areaTo");
		String rentAreaFrom= (String)params.get("areaFrom");
		if(stringUtil.checkString(rentAreaTo) || stringUtil.checkString(rentAreaFrom)) {
			sql.append("INNER JOIN rentarea on rentarea.buildingid= b.id");
		}
	}
	// field của chính những cái bảng mà kh cần inner join 
	public static void queryNomal(Map<String, Object> params, StringBuilder where) {
		for(Map.Entry<String, Object> it:params.entrySet()) {
			if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") 
					&& !it.getKey().startsWith("area") && !it.getKey().startsWith(("rentPrice"))){
				String value=it.getValue().toString();
				if(stringUtil.checkString(value)) {
					if(numberUtil.checkNumber(value)) {
						where.append(" AND b."+it.getKey()+" = " +value);
					}
					else {
						where.append(" AND b." +it.getKey() +" LIKE '%"+ value+"%' ");
					}
				}
			}
		}
	}
	
	
	
	// field mà phải inner join mới tìm kiếm được với bảng khác và mấy field đặc biệt kiểu >= <= vv...
	public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		String staffId= (String) params.get("staffId");
		if(stringUtil.checkString(staffId)) {
			where.append(" AND assignmentbuilding.staffid = "+staffId );
		}
		String rentAreaTo= (String)params.get("areaTo");
		String rentAreaFrom= (String)params.get("areaFrom");
		if(stringUtil.checkString(rentAreaTo) || stringUtil.checkString(rentAreaFrom)) {
			where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id=r.buildingid");
			if(numberUtil.checkNumber(rentAreaTo)) {
				where.append(" AND r.value <=" + rentAreaTo);
			}
			if(numberUtil.checkNumber(rentAreaFrom)) {
				where.append(" AND r.value >=" + rentAreaFrom);
			}
			where.append(") ");
		}
		String rentPriceTo = (String) params.get("rentPriceTo");
		String rentPriceFrom = (String) params.get("rentPriceFrom");
		if(stringUtil.checkString(rentPriceTo) || stringUtil.checkString(rentPriceFrom)) {
			if(numberUtil.checkNumber(rentPriceTo)) {
				where.append(" AND b.rentprice <= " + rentPriceTo);
			}
			if(numberUtil.checkNumber(rentPriceFrom)) {
				where.append(" AND b.rentprice >= "+ rentPriceFrom);
			}
		}
		// làm theo java7
//		if(typeCode!=null && typeCode.size() !=0) {
//			List<String> code = new ArrayList<>();
//			for(String item : typeCode) {
//				code.add("'"+item+"'");
//			}
//			where.append(" AND renttype.code IN ("+String.join(",", code)+ ") ");
//		}
		
		
		// làm theo java 8
		if(typeCode!=null && typeCode.size() !=0){
			where.append(" AND (");
			String sql=typeCode.stream().map(it -> "renttype.code LIKE"+"'%"+it+"%' " ).collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
	}
	
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		StringBuilder sql= new StringBuilder("SELECT b.id, b.name, b.street, b.ward, b.districtid, b.numberofbasement, b.floorarea, b.rentprice, b.rentpricedescription, b.managername, b.managerphonenumber"
				+ " FROM building b ") ;
		joinTable(params, typeCode, sql);
		StringBuilder where = new StringBuilder(" WHERE 1=1");
		queryNomal(params, where);
		querySpecial(params, typeCode, where);
		where.append(" GROUP BY b.id");
		sql.append(where);
		List<BuildingEntity> result = new ArrayList<>();
		try(Connection conn =connectionJDBCUtil.getConnection();
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql.toString());){
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictid(rs.getInt("districtid"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				building.setFloorarea(rs.getInt("floorarea"));
				building.setRentprice(rs.getInt("rentprice"));
				building.setRentpricedescription(rs.getString("rentpricedescription"));
				building.setManagername(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getLong("managerphonenumber"));
				result.add(building);
			}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Connected database failed ... ");
			}
		return result;
	}
	
}
