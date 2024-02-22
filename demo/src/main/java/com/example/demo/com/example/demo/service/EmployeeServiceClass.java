package com.example.demo.com.example.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.com.example.demo.Repository.EmployeeRepository;
import com.example.demo.com.example.demo.entity.EmployeeEntity;
import com.example.demo.com.example.demo.exception.CustomException;
import com.example.demo.com.example.demo.requestBody.ActiveInactiveRequestBody;
import com.example.demo.com.example.demo.requestBody.SaveEmployeeRequestBody;
import com.example.demo.com.example.demo.result.Result;
import com.example.demo.com.example.demo.util.RequiredConstants;
import com.example.demo.com.example.demo.util.Utility;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceClass implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Result getAllEmployees() throws CustomException {
		Optional<List<EmployeeEntity>> entities = null;
		Result result = null;
		try {
			log.info("inside getAllEmployees before hitting db");
			entities = employeeRepository.findAllEmployees();
			result = entities.map(Utility::successResult).orElseGet(Utility::failResult);
			log.info("inside getAllEmployees after hitting db");
		} catch (Exception e) {
			log.error(RequiredConstants.error);
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		return result;
	}

	public Result getEmployeeByName(String employeeName) throws CustomException {
		Optional<EmployeeEntity> employeeEntity = null;
		Result result = null;
		try {
			log.info("inside getEmployeeByName before hitting db");
			employeeEntity = employeeRepository.findEmployeeByName(employeeName);
			result = employeeEntity.map(Utility::successResult).orElse(Utility.failResult());
			log.info("inside getEmployeeByName after hitting db");
		} catch (Exception e) {
			log.error(RequiredConstants.error);
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		return result;
	}

	public Result getEmployeeByEmailID(String emailID) throws CustomException {

		Optional<EmployeeEntity> employeeEntity = null;
		Result result = null;
		try {
			log.info("inside getEmployeeByEmailID before hitting db");
			employeeEntity = employeeRepository.findEmployeeByEmailID(emailID);
			result = employeeEntity.map(Utility::successResult).orElse(Utility.failResult());
			log.info("inside getEmployeeByEmailID after hitting db");
		} catch (Exception e) {
			log.error(RequiredConstants.error);
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		return result;
	}
	/*
	 *  checks whether any emailId exists or not if exists it won't add
	 */
	public Result saveEmployee(List<SaveEmployeeRequestBody> employeeRequestBody) throws Exception {
		List<EmployeeEntity> employeeEntities = new ArrayList<>();
		List<EmployeeEntity> employeeEntitiesRtrn = new ArrayList<>();
		Result result= new Result();
		try {
			List<String> emailIds= employeeRequestBody.stream().map(SaveEmployeeRequestBody::getEmailId).
					collect(Collectors.toList());
			Optional<List<String>> existingEmails= employeeRepository.findEmployeeByEmailIDIn(emailIds);
			List<SaveEmployeeRequestBody> employeeRequestBodyExisting= employeeRequestBody.stream().
					filter(v->!existingEmails.get().contains(v.getEmailId())).collect(Collectors.toList());
			for (SaveEmployeeRequestBody requestBody : employeeRequestBodyExisting) {
				EmployeeEntity entity= new EmployeeEntity();
			    BeanUtils.copyProperties(requestBody, entity);
				entity.setPassword(Utility.encode(requestBody.getPassword()));
				employeeEntities.add(entity);
			}
			employeeEntitiesRtrn= employeeRepository.saveAll(employeeEntities);
			result= Utility.successResult(employeeEntitiesRtrn);
			result.setMessage("Could Not add " + existingEmails.get() +" as they already exist" );
		} catch (Exception e) {
			log.error(RequiredConstants.error);
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());

		}
		return result;
	}

	@Override
	public Result getEmployeeByDeptarment() throws Exception {
		Map<String, List<EmployeeEntity>> map= new HashMap<>();
		try {
			Optional<List<EmployeeEntity>> employeeEntities= employeeRepository.findAllEmployees();
			map= employeeEntities.get().stream().collect(Collectors.groupingBy(v->v.getDepartment()));
		}catch (Exception e) {
			log.error(RequiredConstants.error);
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		return (map!=null)? Utility.successResult(map):Utility.failResult() ;
	}

	@Transactional
	public Result inactivateActivateEmployee(ActiveInactiveRequestBody activeInactiveRequestBody) throws Exception{
		try {
			employeeRepository.deactivateEmployee(activeInactiveRequestBody.getEmployeeId(),
					activeInactiveRequestBody.getIsActive());
		}catch (Exception e) {
			log.error(RequiredConstants.error);
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		return Utility.successResult(activeInactiveRequestBody);
	}

	
}
