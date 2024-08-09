package com.healsmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healsmart.entities.Ward;
@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
	@Modifying
	@Query(value = "insert into wards values (:wardId, :type, :charges, :availability, :max_capacity)",nativeQuery = true)
	int insertIntoWardTable( @Param("wardId")long wardId,@Param("type")String type,@Param("charges")double charges,@Param("availability")double availability,@Param("max_capacity")double max_capacity);
	
	@Query(value="select wardId from wards where type=:type",nativeQuery = true)
	int getWardIdByTypeOfWard(String type);
	
	@Query
	Ward findByType(String type);
}
