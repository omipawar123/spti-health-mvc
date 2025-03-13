package com.nt.service;

import java.time.LocalDate;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.nt.Dto.patients.AdmitPatientPageDto;
import com.nt.Dto.patients.AdmitPatientRequestDto;
import com.nt.Dto.patients.AdmitPatientResponseDto;
import com.nt.Dto.patients.PatientResponseDto;


@Service
public class AdmitPatientServiceImpl implements AdmitPatientService {

	@Override
	public String addAdmitPatientdetails(AdmitPatientRequestDto dto) {
		RestTemplate template = new RestTemplate();

		String url = "http://localhost:9696/admits";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<AdmitPatientRequestDto> request = new HttpEntity<>(dto, headers);
		try {
			ResponseEntity<String> result = template.exchange(url, HttpMethod.POST, request, String.class);
			return result.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AdmitPatientResponseDto getAdmitPatientBypatienId(Long id) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/admits/patient/id/" + id;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		try {

			ResponseEntity<AdmitPatientResponseDto> res = template.exchange(url, HttpMethod.GET, entity,
					AdmitPatientResponseDto.class);
			return res.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public AdmitPatientPageDto allAdmitPatients() {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/admits/?pageNo=0";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		try {

			ResponseEntity<AdmitPatientPageDto> res = template.exchange(url, HttpMethod.GET, entity,
					AdmitPatientPageDto.class);
			return res.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<PatientResponseDto> findByAdmissionDate(LocalDate admissionDate) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/admits/getByTodaysPatient/" + admissionDate;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		try {

			ResponseEntity<List<PatientResponseDto>> res = template.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<PatientResponseDto>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<PatientResponseDto> findByYear(int year) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/admits/getByYear/" + year;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		try {

			ResponseEntity<List<PatientResponseDto>> res = template.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<PatientResponseDto>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<PatientResponseDto> findByStartAndEndDates(LocalDate startDate, LocalDate endDate) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/admits/admit-patients/" + startDate + "/" + endDate;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		try {

			ResponseEntity<List<PatientResponseDto>> res = template.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<PatientResponseDto>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
    
	@Override
	public List<PatientResponseDto> findallPatient() {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/admits/getAllPatient";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		try {

			ResponseEntity<List<PatientResponseDto>> res = template.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<PatientResponseDto>>() {
					});
			return res.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
}

	@Override
	public List<PatientResponseDto> getTodaysWeekMonthPatient(String todayrecord) {
	    RestTemplate template = new RestTemplate();
	    String url = "http://localhost:9696/admits/todaysPatient/" + todayrecord;
	    HttpHeaders headers = new HttpHeaders();
	    HttpEntity<String> entity = new HttpEntity<>("body", headers);
	    try {
	        ResponseEntity<List<PatientResponseDto>> res = template.exchange(url, HttpMethod.GET, entity,
	                new ParameterizedTypeReference<List<PatientResponseDto>>() {});
	        return res.getBody();
	    } catch (RestClientException e) {
	        
	        return Collections.emptyList();
	    }
	}

	public List<AdmitPatientResponseDto> todayAdmitPatient(String todayrecord) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/admits/todayAdmitPatientDashbord/"+todayrecord;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
	        ResponseEntity<List<AdmitPatientResponseDto>> res = template.exchange(url, HttpMethod.GET, entity,
	                new ParameterizedTypeReference<List<AdmitPatientResponseDto>>() {});
	        return res.getBody();
	    } catch (RestClientException e) {
	        
	        return Collections.emptyList();
	    }
	}
	

	@Override
	public List<AdmitPatientResponseDto> todayDischargePatient(String todayrecord) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/admits/disacharge/todayDischargePatientDashbord/"+todayrecord;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
	        ResponseEntity<List<AdmitPatientResponseDto>> res = template.exchange(url, HttpMethod.GET, entity,
	                new ParameterizedTypeReference<List<AdmitPatientResponseDto>>() {});
	        return res.getBody();
	    } catch (RestClientException e) {
	        
	        return Collections.emptyList();
	    }
	}

	@Override
	public List<AdmitPatientResponseDto> todayWeeklyMonthlyDischargePatient(String todayrecord) {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:9696/admits/disacharge/todayWeeklyMonthlyDischargePatient/"+todayrecord;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>( "body", headers );
		try {
	        ResponseEntity<List<AdmitPatientResponseDto>> res = template.exchange(url, HttpMethod.GET, entity,
	                new ParameterizedTypeReference<List<AdmitPatientResponseDto>>() {});
	        return res.getBody();
	    } catch (RestClientException e) {
	        
	        return Collections.emptyList();
	    }
	}

	/*
	 * @Override public List<PatientResponseDto> findDataByStartToEndDate(String
	 * todayrecord) { RestTemplate template = new RestTemplate(); String url =
	 * "http://localhost:9696/admits/getdataWithStartAndEndDate/" + todayrecord;
	 * HttpHeaders headers = new HttpHeaders(); HttpEntity<String> entity = new
	 * HttpEntity<>("body", headers); try {
	 * 
	 * ResponseEntity<List<PatientResponseDto>> res = template.exchange(url,
	 * HttpMethod.GET, entity, new
	 * ParameterizedTypeReference<List<PatientResponseDto>>() { }); return
	 * res.getBody();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return null; }
	 */
}
