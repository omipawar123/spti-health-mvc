package com.nt.Dto.patients;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientContent {
	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String address;
	private int age;
	private String phoneNumber;
	private String email;
	private int branch;
	
	
}
