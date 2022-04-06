package com.example.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long>{

	
	@Query(value = "UPDATE holiday SET boss_id=?1, WHERE id=?2",nativeQuery = true)
	public void updateboss(@Param("boss_id")Long boss_id,@Param("id")Long id);
	 
	@Transactional
	@Modifying
	@Query(value = "UPDATE Holiday SET results=?1 WHERE id=?2",nativeQuery = true)
	public void updateresult(@Param("results")String results,@Param("id")Long id);
}
