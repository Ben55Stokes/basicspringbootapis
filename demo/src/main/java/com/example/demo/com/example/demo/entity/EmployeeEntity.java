package com.example.demo.com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class EmployeeEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_id")
	private Integer employeeId;
	@Column(name="employeename")
	private String employeeName;
	@Column(name="department")
	private String department;
	@Column(name="email_id")
	private String emailId;
	@Column(name="password")
	private String password;
	@Column(name="joiningdate")
	private String joiningdate;
	@Column(name="date_of_birth")
	private String dateOfBirth; 
	@Column(name="is_Active")
	private String isActive;
}
