package com.nt.Dto.patients;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DischargePatientRequestDto {

	private Long dischargePatientId;

//	@NotNull(message = "MLC number must not be null")
	private Long MLC_No;

//	@NotNull(message = "IPD number must not be null")
	private Long IPD_No;

	private String consultatnt;

//	@NotBlank(message = "Diagnosis must not be blank")
	private String diagnosis;

	private String clinical_History;

	private Long patientId;

	private Long admitPatientId;

//	@NotBlank(message = "Admit discharge status must not be blank")
	private String admitDischargeStatus;

//	@Positive(message = "Amount paid must be a positive number")
	private Long amountPaid;

	private Long amounttobePaid;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime dischargedAt;

}
