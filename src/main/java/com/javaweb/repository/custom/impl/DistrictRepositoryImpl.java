package com.javaweb.repository.custom.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.connectionJDBCUtil;


@Repository
public class DistrictRepositoryImpl implements DistrictRepository{
	

	@Override
	public DistrictEntity findNameByID(Long id) {
		String sql= "SELECT d.name FROM district d WHERE d.id = "+ id +";";
		DistrictEntity districtEntity = new DistrictEntity();
		try(Connection conn = connectionJDBCUtil.getConnection();
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql);){
			while(rs.next()) {
				districtEntity.setName(rs.getString("name"));
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return  districtEntity;
	}

}
