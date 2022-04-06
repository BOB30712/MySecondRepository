package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	
	@Query(value = "UPDATE employee SET paidleave=?1, WHERE id=?2",nativeQuery = true)
	public void updateboss(@Param("paidleave")Integer paidleave,@Param("id")Long id);
}
