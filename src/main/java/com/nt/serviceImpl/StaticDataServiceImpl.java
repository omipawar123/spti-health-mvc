package com.nt.serviceImpl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nt.Dto.patients.DiagnosisResponseDto;
import com.nt.service.StaticDataService;

@Service
public class StaticDataServiceImpl implements StaticDataService {

    @Override
    public List<DiagnosisResponseDto> getAllDiagnosis() {
        RestTemplate template = new RestTemplate();
        String url = "http://localhost:9696/diagnosiss/getAllDiagnosis";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        try {
            ResponseEntity<List<DiagnosisResponseDto>> res = template.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<List<DiagnosisResponseDto>>() {});
            return res.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
   
   }
}