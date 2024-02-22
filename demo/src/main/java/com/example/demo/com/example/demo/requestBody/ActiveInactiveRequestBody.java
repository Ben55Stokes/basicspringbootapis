package com.example.demo.com.example.demo.requestBody;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActiveInactiveRequestBody {

	private Integer employeeId;
	private String isActive;
}
