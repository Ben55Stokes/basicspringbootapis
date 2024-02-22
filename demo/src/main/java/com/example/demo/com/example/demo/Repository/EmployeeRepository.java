package com.example.demo.com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.com.example.demo.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	@Query(value = "select * from employee order by employee_id asc" ,nativeQuery = true)
	Optional<List<EmployeeEntity>> findAllEmployees();
	
	@Query(value = "select * from employee where employeename=:employeeName" ,nativeQuery = true)
	Optional<EmployeeEntity> findEmployeeByName(@Param("employeeName") String employeeName);
	
	@Query(value = "select * from employee where email_id=:emailID" ,nativeQuery = true)
	Optional<EmployeeEntity> findEmployeeByEmailID(@Param("emailID") String emailID);

	@Modifying
	@Query(value = "update employee set is_Active=:isActive where employee_id=:employeeID" ,nativeQuery = true)
	void deactivateEmployee(@Param("employeeID") Integer employeeID,@Param("isActive") String isActive);

	@Query(value = "select email_id from employee where email_id in (:emailID)" ,nativeQuery = true)
	Optional<List<String>>  findEmployeeByEmailIDIn(@Param("emailID") List<String> emailId);

}
