package com.example.demo.com.example.demo.requestBody;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveEmployeeRequestBody {

	private Long employeeID;
	private String employeeName;
	private String department;
	private String emailId;
	private String password;
	private String joiningdate;
	private String dateOfBirth; 
	private String isActive;
}
