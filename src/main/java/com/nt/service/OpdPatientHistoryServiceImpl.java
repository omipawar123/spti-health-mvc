package com.nt.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nt.Dto.patients.OpdPatienPageDto;
import com.nt.Dto.patients.PatientOPDHistoryRequestDTO;
import com.nt.Dto.patients.PatientOPDHistoryResponseDto;
import com.nt.Dto.patients.PatientPageDto;
import com.nt.service.OpdPatientHistoryService;

@Service
public class OpdPatientHistoryServiceImpl implements OpdPatientHistoryService {
	@Override
	public List<PatientOPDHistoryResponseDto> opdPatientHistory( String disease ,String todayrecord ,String ages ) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/opds/todayOpdPatientHistory/"+disease +"/"+ todayrecord + "/" + ages ;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
			ParameterizedTypeReference<List<PatientOPDHistoryResponseDto>> responseType = new ParameterizedTypeReference<List<PatientOPDHistoryResponseDto>>() {
			};
			ResponseEntity<List<PatientOPDHistoryResponseDto>> res = template.exchange( url, HttpMethod.GET, entity, responseType );
			return res.getBody();

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public PatientOPDHistoryResponseDto getHistoryPatientDetailspatientId(Long id) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/opds/patientsOpd/" + id;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
			ParameterizedTypeReference<PatientOPDHistoryResponseDto> responseType = new ParameterizedTypeReference<PatientOPDHistoryResponseDto>() {
			};
			ResponseEntity<PatientOPDHistoryResponseDto> res = template.exchange( url, HttpMethod.GET, entity, responseType );
			return res.getBody();

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	
	@Override
	public List<PatientOPDHistoryResponseDto> getHistoryPatientpatientId( Long id ) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/opds/patients/" + id;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
			ParameterizedTypeReference<List<PatientOPDHistoryResponseDto>> responseType = new ParameterizedTypeReference<List<PatientOPDHistoryResponseDto>>() {
			};
			ResponseEntity<List<PatientOPDHistoryResponseDto>> res = template.exchange( url, HttpMethod.GET, entity, responseType );
			return res.getBody();

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	
	@Override
	public List<PatientOPDHistoryResponseDto> getPatientOpdHistory( Long patientId ) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/opds/patients/" + patientId;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
			ParameterizedTypeReference<List<PatientOPDHistoryResponseDto>> responseType = new ParameterizedTypeReference<List<PatientOPDHistoryResponseDto>>() {
			};
			ResponseEntity<List<PatientOPDHistoryResponseDto>> res = template.exchange( url, HttpMethod.GET, entity, responseType );
			return res.getBody();

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String addOpdHistory( PatientOPDHistoryRequestDTO dto ) {
		RestTemplate template = new RestTemplate();

		String url = "http://localhost:9696/opds/history";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );
		headers.setContentType( MediaType.APPLICATION_JSON );

		HttpEntity<PatientOPDHistoryRequestDTO> request = new HttpEntity<>( dto, headers );
		try {
			ResponseEntity<String> result = template.exchange( url, HttpMethod.POST, request, String.class );
			return result.getBody();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}


	

	@Override
	public PatientOPDHistoryResponseDto opdPatienBill(String todayrecord) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/opds/opdPatienBill/"+todayrecord;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
			ParameterizedTypeReference<PatientOPDHistoryResponseDto> responseType = new ParameterizedTypeReference<PatientOPDHistoryResponseDto>() {
			};
			ResponseEntity<PatientOPDHistoryResponseDto> res = template.exchange( url, HttpMethod.GET, entity, responseType );
			return res.getBody();

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<PatientOPDHistoryResponseDto> todayOpdPatient2(String todayrecord) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/opds/todayOpdPatientDashbord/"+todayrecord;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
			ParameterizedTypeReference<List<PatientOPDHistoryResponseDto>> responseType = new ParameterizedTypeReference<List<PatientOPDHistoryResponseDto>>() {
			};
			ResponseEntity<List<PatientOPDHistoryResponseDto>> res = template.exchange( url, HttpMethod.GET, entity, responseType );
			return res.getBody();

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public PatientOPDHistoryResponseDto getOpdRecordByid(Long id) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/opds/patientsOpd/" + id;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
			ParameterizedTypeReference<PatientOPDHistoryResponseDto> responseType = new ParameterizedTypeReference<PatientOPDHistoryResponseDto>() {
			};
			ResponseEntity<PatientOPDHistoryResponseDto> res = template.exchange( url, HttpMethod.GET, entity, responseType );
			return res.getBody();

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public PatientOPDHistoryResponseDto opdPatienBillOnlineDashbord(String todayrecord) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/opds/opdPatienBillOnlineDashbord/"+todayrecord;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
			ParameterizedTypeReference<PatientOPDHistoryResponseDto> responseType = new ParameterizedTypeReference<PatientOPDHistoryResponseDto>() {
			};
			ResponseEntity<PatientOPDHistoryResponseDto> res = template.exchange( url, HttpMethod.GET, entity, responseType );
			return res.getBody();

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public PatientOPDHistoryResponseDto opdPatienBillCasheDashbord(String todayrecord) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/opds/opdPatienBillCashDashbord/"+todayrecord;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
			ParameterizedTypeReference<PatientOPDHistoryResponseDto> responseType = new ParameterizedTypeReference<PatientOPDHistoryResponseDto>() {
			};
			ResponseEntity<PatientOPDHistoryResponseDto> res = template.exchange( url, HttpMethod.GET, entity, responseType );
			return res.getBody();

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String updatePaidBill(PatientOPDHistoryRequestDTO dto) {
		RestTemplate template = new RestTemplate();

		String url = "http://localhost:9696/opds/updatePaidBill";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );
		headers.setContentType( MediaType.APPLICATION_JSON );

		HttpEntity<PatientOPDHistoryRequestDTO> request = new HttpEntity<>( dto, headers );
		try {
			ResponseEntity<String> result = template.exchange( url, HttpMethod.POST, request, String.class );
			return result.getBody();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	
}
