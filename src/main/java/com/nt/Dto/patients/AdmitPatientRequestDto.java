package com.nt.Dto.patients;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdmitPatientRequestDto {
    
   // @Positive(message = "ID must be a positive number")
    private long id;

    @NotBlank(message = "Admit/Discharge status is mandatory")
    private String admitDischargeStatus;

    @NotNull(message = "Patient ID cannot be null")
    @Positive(message = "Patient ID must be a positive number")
    private Long patientId;

    @NotNull(message = "Admission date cannot be null")
   
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime admissionDate;
}
