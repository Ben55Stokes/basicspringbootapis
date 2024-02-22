package com.example.demo.com.example.demo.service;

import java.util.List;

import com.example.demo.com.example.demo.exception.CustomException;
import com.example.demo.com.example.demo.requestBody.ActiveInactiveRequestBody;
import com.example.demo.com.example.demo.requestBody.SaveEmployeeRequestBody;
import com.example.demo.com.example.demo.result.Result;

public interface EmployeeService {
	public Result getAllEmployees() throws CustomException;

	public Result getEmployeeByName(String employeeName) throws CustomException;

	public Result getEmployeeByEmailID(String emailID) throws CustomException;
	
	public Result saveEmployee(List<SaveEmployeeRequestBody> employeeRequestBody) throws Exception;
	
	public Result getEmployeeByDeptarment() throws Exception;

	public Result inactivateActivateEmployee(ActiveInactiveRequestBody activeInactiveRequestBody) throws Exception;
}
