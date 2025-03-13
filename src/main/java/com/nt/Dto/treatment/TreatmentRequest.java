package com.nt.Dto.treatment;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentRequest {

	
		private int admittanceId;
		private String medicineName;
		private String medicineTime;
		@DateTimeFormat( pattern = "yyyy-MM-dd" )
		private  LocalDate treatmentDate;
		private int bill;
		private  String billPaid;
		private String paymentMode;
		private String createdBy;

	}



