package com.javaweb.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		// JPQL: Làm việc 100% với entity
		String sql="FROM BuildingEntity b WHERE b.name like '%ACM%' ";
		Query query=entityManager.createQuery(sql, BuildingEntity.class);
		return query.getResultList();
		
		
		// SQL navive
//		String sql="SELECT * FROM building b WHERE b.name like '%building%' ";
//		Query query= entityManager.createNativeQuery(sql,BuildingEntity.class);
//		return query.getResultList();
	}

}
