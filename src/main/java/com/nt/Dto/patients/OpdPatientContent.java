package com.nt.Dto.patients;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpdPatientContent {

	private Long id;
	private String seenByDoctor;
	private String diagnosis;
	private String treatment;
	private String bill;
	private String billStatus;
	private String pendingAmount;
	private PatientResponseDto patient;
	
	private float paidBill;
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private LocalDateTime dateOfTreatment;
	
	
}
