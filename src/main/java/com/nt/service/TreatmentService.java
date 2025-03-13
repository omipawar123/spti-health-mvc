package com.nt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nt.Dto.treatment.TreatmentRequest;
import com.nt.Dto.treatment.TreatmentResponse;
@Service
public interface TreatmentService {

	String addAdmittedPatientTreatmentDetails(List<TreatmentRequest> treatmentRequestList,long id);

	 List<TreatmentResponse>  getTreatmentdetailsByAdimittance(Long id) ;

}
