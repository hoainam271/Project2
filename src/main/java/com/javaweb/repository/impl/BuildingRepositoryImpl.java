package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository // cái này của spring framework
// @Primary // cái này để khi có 2 cái findAll ở cùng 1 respo thì nó sẽ nhảy vào cái có anno này
public class BuildingRepositoryImpl implements BuildingRepository {
	@PersistenceContext // nó giúp chạy đúng luồng vào đây(của jpa)
	private EntityManager entityManager; 
	
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			sql.append("INNER JOIN assignmentbuilding a on a.buildingid=b.id ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			sql.append("INNER JOIN buildingrenttype on buildingrenttype.buildingid= b.id ");
			sql.append("INNER JOIN renttype on renttype.id= buildingrenttype.renttypeid ");
		}
		
	}

	// field của chính những cái bảng mà kh cần inner join
	public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
//		
		try {
			// java refection
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
						&& !fieldName.startsWith(("rentPrice"))) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
						if (item.getType().getName().equals("java.lang.Long")|| item.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND b." + fieldName + " = " + value);
						} else {
							where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
						}

					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// field mà phải inner join mới tìm kiếm được với bảng khác và mấy field đặc
	// biệt kiểu >= <= vv...
	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}
		Long rentAreaTo = buildingSearchBuilder.getAreaTo();
		Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
		if (rentAreaTo != null || rentAreaFrom != null) {
			where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id=r.buildingid");
			if (rentAreaTo != null) {
				where.append(" AND r.value <=" + rentAreaTo);
			}
			if (rentAreaFrom != null) {
				where.append(" AND r.value >=" + rentAreaFrom);
			}
			where.append(") ");
		}
		Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		if (rentPriceTo!= null || rentPriceFrom!= null) {
			if (rentPriceTo!= null) {
				where.append(" AND b.rentprice <= " + rentPriceTo);
			}
			if (rentPriceFrom!= null) {
				where.append(" AND b.rentprice >= " + rentPriceFrom);
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
		List<String> typeCode= buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			where.append(" AND (");
			String sql = typeCode.stream().map(it -> "renttype.code LIKE" + "'%" + it + "%' ")
					.collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder(
				"SELECT *"
						+ " FROM building b ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1=1");
		queryNomal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
//		where.append(" GROUP BY b.id; ");
		where.append(" ; ");
		sql.append(where);
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class); // SQL Native
		return query.getResultList();
	}

}
