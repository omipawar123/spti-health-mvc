package com.nt.service;

import java.util.List;


import com.nt.Dto.patients.PatientOPDHistoryRequestDTO;
import com.nt.Dto.patients.PatientOPDHistoryResponseDto;


public interface OpdPatientHistoryService {
	public List<PatientOPDHistoryResponseDto> getPatientOpdHistory( Long patientId );

	public String addOpdHistory( PatientOPDHistoryRequestDTO dto );

	public PatientOPDHistoryResponseDto opdPatienBill(String todayrecord);
	
	public List<PatientOPDHistoryResponseDto> todayOpdPatient2(String todayrecord);

	public PatientOPDHistoryResponseDto getOpdRecordByid(Long id);

	public PatientOPDHistoryResponseDto opdPatienBillOnlineDashbord(String todayrecord);

	PatientOPDHistoryResponseDto opdPatienBillCasheDashbord(String todayrecord);

	public String updatePaidBill(PatientOPDHistoryRequestDTO dto);

	public List<PatientOPDHistoryResponseDto> getHistoryPatientpatientId(Long id);

	public PatientOPDHistoryResponseDto getHistoryPatientDetailspatientId(Long id);

	public List<PatientOPDHistoryResponseDto> opdPatientHistory(String disease, String todayrecord ,String ages);



	
}
