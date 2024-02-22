package com.example.demo.com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends Exception{

	/**
	 * for version and compatibility while serializing and deserializing
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer statusCode;
	private String message;

}
