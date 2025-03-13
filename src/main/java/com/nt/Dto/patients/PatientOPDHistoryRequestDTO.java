package com.nt.Dto.patients;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientOPDHistoryRequestDTO {

   // @NotBlank(message = "Doctor's name cannot be blank")
   //   @Size(min = 1, max = 100, message = "Doctor's name must be between 1 and 100 characters")
    private String seenByDoctor;

   // @NotBlank(message = "Diagnosis cannot be blank")
  //  @Size(min = 1, max = 255, message = "Diagnosis must be between 1 and 255 characters")
    private Diagnosis diagnosis;

//    @NotBlank(message = "Treatment cannot be blank")
//    @Size(min = 1, max = 255, message = "Treatment must be between 1 and 255 characters")
    private String treatment;

//    @NotBlank(message = "Bill information cannot be blank")
 //   @Size(max = 50, message = "Bill information must not exceed 50 characters")
    private String bill;

 //   @NotBlank(message = "Bill status cannot be blank")
    private String billStatus;

 //   @NotBlank(message = "Payment type cannot be blank")
 //   @Size(min = 1, max = 50, message = "Payment type must be between 1 and 50 characters")
    private String paymentType;

 //   @NotNull(message = "Patient ID cannot be null")
//    @Positive(message = "Patient ID must be a positive number")
    private Long patientId;

  //  @NotBlank(message = "Pending amount cannot be blank")
    private String pendingAmount;

//    @Size(max = 500, message = "Note must not exceed 500 characters")
    private String note;

 //   @NotNull(message = "Treatment date cannot be null")
    private LocalDate treatmentDate;

 //   @NotNull(message = "Date of treatment cannot be null")
    private LocalDateTime dateOfTreatment;
}
