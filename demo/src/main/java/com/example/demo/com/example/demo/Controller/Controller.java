package com.example.demo.com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.com.example.demo.exception.CustomException;
import com.example.demo.com.example.demo.requestBody.ActiveInactiveRequestBody;
import com.example.demo.com.example.demo.requestBody.SaveEmployeeRequestBody;
import com.example.demo.com.example.demo.result.Result;
import com.example.demo.com.example.demo.service.EmployeeServiceClass;

@RestController
public class Controller {

	@Autowired
	EmployeeServiceClass service;
	
	@GetMapping("/get-all-employees")
	public Result getAllEmployees()    {
		return service.getAllEmployees();
	}

	@GetMapping("/get-by-employeename")
	public Result getEmployeeByName(@RequestParam("employeeName") String employeeName)    {
		return service.getEmployeeByName(employeeName);
	}
	
	@GetMapping("/employee-email-id-exists/{emailID}")
	public Result getEmployeeemailIdExists(@PathVariable("emailID") String emailID)    {
		return service.getEmployeeByEmailID(emailID);
	}
	
	@PostMapping("/save-employee")
	public Result saveEmployee(@RequestBody List<SaveEmployeeRequestBody> employeeRequestBody)    {
		return service.saveEmployee(employeeRequestBody);
	}
	
	@GetMapping("/get-all-employees-by-department")
	public Result getEmployeesByDepartMents()    {
		return service.getEmployeeByDeptarment();
	}

	@PatchMapping("/inactivate-activate-employee")
	public Result inactivateActivateEmployee(@RequestBody ActiveInactiveRequestBody activeInactiveRequestBody)   {
		return service.inactivateActivateEmployee(activeInactiveRequestBody);
	}
}
