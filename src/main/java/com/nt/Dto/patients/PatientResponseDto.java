package com.nt.Dto.patients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String address;
	private int age;
	private String phoneNumber;
	private String email;
	private int branch;
	private String admitDischargeStatus;

}
