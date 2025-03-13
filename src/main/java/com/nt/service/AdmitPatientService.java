package com.nt.service;

import java.time.LocalDate;
import java.util.List;
import com.nt.Dto.patients.AdmitPatientPageDto;
import com.nt.Dto.patients.AdmitPatientRequestDto;
import com.nt.Dto.patients.AdmitPatientResponseDto;
import com.nt.Dto.patients.PatientResponseDto;

public interface AdmitPatientService {

	String addAdmitPatientdetails(AdmitPatientRequestDto dto);

	AdmitPatientResponseDto getAdmitPatientBypatienId(Long id);

	AdmitPatientPageDto allAdmitPatients();

	List<PatientResponseDto> findByAdmissionDate(LocalDate admissionDate);

	List<PatientResponseDto> findByYear(int year);

	List<PatientResponseDto> findByStartAndEndDates(LocalDate startDate, LocalDate endDate);

	List<PatientResponseDto> findallPatient();

	List<PatientResponseDto> getTodaysWeekMonthPatient(String todayrecord);

	List<AdmitPatientResponseDto> todayAdmitPatient(String todayrecord);

	List<AdmitPatientResponseDto> todayDischargePatient(String todayrecord);

	List<AdmitPatientResponseDto> todayWeeklyMonthlyDischargePatient(String todayrecord);

//	List<PatientResponseDto> findDataByStartToEndDate(String todayrecord);


}
