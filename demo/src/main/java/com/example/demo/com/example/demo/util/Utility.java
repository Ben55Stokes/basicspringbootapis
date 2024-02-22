package com.example.demo.com.example.demo.util;

import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.demo.com.example.demo.result.Result;

@Component
public class Utility {

	public static  Result successResult(Object data) {
		Result result = new Result();
		result.setMessage(RequiredConstants.successMessage);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(data);
		return result;
	}

	public static Result failResult() {
		Result result = new Result();
		result.setMessage(RequiredConstants.empty);
		result.setStatusCode(HttpStatus.NOT_FOUND.value());
		return result;
	}
	
	public static String encode(String password) {
		return new String(Base64.getEncoder().encode(password.getBytes()));
	}

}