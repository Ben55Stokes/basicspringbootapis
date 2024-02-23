package com.example.demo.com.example.demo.service;

import java.util.List;

import com.example.demo.com.example.demo.exception.CustomException;
import com.example.demo.com.example.demo.requestBody.ActiveInactiveRequestBody;
import com.example.demo.com.example.demo.requestBody.SaveEmployeeRequestBody;
import com.example.demo.com.example.demo.result.Result;

public interface EmployeeService {
	public Result getAllEmployees() ;

	public Result getEmployeeByName(String employeeName)   ;

	public Result getEmployeeByEmailID(String emailID)   ;
	
	public Result saveEmployee(List<SaveEmployeeRequestBody> employeeRequestBody)   ;
	
	public Result getEmployeeByDeptarment()   ;

	public Result inactivateActivateEmployee(ActiveInactiveRequestBody activeInactiveRequestBody)   ;
}
