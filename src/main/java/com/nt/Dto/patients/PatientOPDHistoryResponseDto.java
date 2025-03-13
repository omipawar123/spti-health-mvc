package com.nt.Dto.patients;

import java.time.LocalDate;
import java.time.LocalDateTime;



import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientOPDHistoryResponseDto {

	private Long id;
	private String seenByDoctor;
	private DiagnosisResponseDto diagnosis;
	private String treatment;
	private String bill;
	private String billStatus;
	private String pendingAmount;
	private PatientResponseDto patient;
	private int branch;
	private LocalDate treatmentDate;
	private String paymentType;
	
	private float paidBill;
	private float totalBillOpd;
	private float totalPendingBill;
	private float totalPaidBill;
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	private LocalDateTime dateOfTreatment;

	
}
