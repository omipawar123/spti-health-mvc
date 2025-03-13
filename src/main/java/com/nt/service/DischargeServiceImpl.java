package com.nt.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nt.Dto.patients.AdmitPatientRequestDto;
import com.nt.Dto.patients.DischargePatientRequestDto;

@Service
public class DischargeServiceImpl implements DischargePatientService {

	@Override
	public String dischargePatient(DischargePatientRequestDto dto) {
		RestTemplate template = new RestTemplate();
		
		String url = "http://localhost:9696/discharge";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );
		headers.setContentType( MediaType.APPLICATION_JSON );

		HttpEntity<DischargePatientRequestDto> request = new HttpEntity<>( dto, headers );
		try {
			ResponseEntity<String> result = template.exchange( url, HttpMethod.POST, request, String.class );
			return result.getBody();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
		
	}

}
