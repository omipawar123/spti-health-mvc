package com.nt.Dto.patients;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdmitPatientContent {

	private Long id;
	private String admitAndDischargeStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime admissionDate;
	
	private PatientResponseDto patient;
}
