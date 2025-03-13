package com.nt.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nt.Dto.treatment.TreatmentRequest;
import com.nt.Dto.treatment.TreatmentResponse;
import com.nt.service.TreatmentService;

@Service
public class TreatmentserviceImpl implements TreatmentService {

	@Override
	public String addAdmittedPatientTreatmentDetails(List<TreatmentRequest> treatmentRequestdto,long id) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String url = "http://localhost:9696/admits/treatment/"+id;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );
		headers.setContentType( MediaType.APPLICATION_JSON );

		HttpEntity<List<TreatmentRequest>> request = new HttpEntity<>( treatmentRequestdto, headers );
		try {
			ResponseEntity<String> result = restTemplate.exchange( url, HttpMethod.POST, request, String.class );
			return result.getBody();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TreatmentResponse> getTreatmentdetailsByAdimittance(Long id) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate=new RestTemplate();
		String url = "http://localhost:9696/admits/treatment/"+id;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );
		headers.setContentType( MediaType.APPLICATION_JSON );

		HttpEntity<TreatmentRequest> request = new HttpEntity<>( null, headers );
		try {
			ResponseEntity<List<TreatmentResponse>> result = restTemplate.exchange( url, HttpMethod.GET, request, new ParameterizedTypeReference<List<TreatmentResponse>>() {} );
			return result.getBody();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

}
