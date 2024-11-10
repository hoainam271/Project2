package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.connectionJDBCUtil;
@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{

	@Override
	public List<RentAreaEntity> getValueByBuildingId(long id) {
		String sql="SELECT * FROM rentarea WHERE rentarea.buildingid = "+id;
		List<RentAreaEntity> rentAreas = new ArrayList<>();
		try(Connection conn = connectionJDBCUtil.getConnection();
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql);){
			while(rs.next()) {
				RentAreaEntity rentAreaEntity= new RentAreaEntity();
				rentAreaEntity.setValue(rs.getLong("value"));
				rentAreaEntity.setBuildingId(rs.getInt("buildingId"));
				rentAreas.add(rentAreaEntity);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return rentAreas;
	}

}
