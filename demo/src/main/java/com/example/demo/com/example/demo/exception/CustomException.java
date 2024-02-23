package com.example.demo.com.example.demo.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class CustomException extends RuntimeException{

	/**
	 * for version and compatibility while serializing and deserializing
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer statusCode;
	private String message;

}
