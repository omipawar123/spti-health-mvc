package com.nt.cntrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.Dto.patients.PatientRequestDto;
import com.nt.Dto.patients.PatientResponseDto;
import com.nt.Dto.treatment.TreatmentRequest;
import com.nt.Dto.treatment.TreatmentResponse;
import com.nt.constants.MessageConstants;
import com.nt.service.PatientService;
import com.nt.service.TreatmentService;

@Controller
@RequestMapping("/treatment")
public class TreatmentController {

	@Autowired
	TreatmentService treatmentService;

	@Autowired
	private PatientService patientService;

	@GetMapping("/treatment-form/{id}")
	public String getTreatmentForm(@PathVariable Long id, Model model) {
		PatientResponseDto result1 = patientService.getPatientById(id);
		model.addAttribute("patient", result1);
		List<TreatmentResponse> treatmentResponse=treatmentService.getTreatmentdetailsByAdimittance(id);
		model.addAttribute("treatmentList", treatmentResponse);
		return "patients/treatment";
	}
	@GetMapping("/admittreatment/{id}")
	public String admitTreatmentForm(@PathVariable Long id, Model model) {
		PatientResponseDto result1 = patientService.getPatientById(id);
		model.addAttribute("patient", result1);
		List<TreatmentResponse> treatmentResponse=treatmentService.getTreatmentdetailsByAdimittance(id);
		model.addAttribute("treatmentList", treatmentResponse);
		return "patients/Admitpatientdetails";
	}

	@PostMapping("/add/{id}")
	public String addAdmittedPatientTreatmentDetails(@PathVariable Long id,
			@ModelAttribute TreatmentRequest treatmentRequestdto, Model model, HttpSession session,
			RedirectAttributes ra) {
		String user = (String) session.getAttribute("username");
		if (user != null) {
			//treatmentRequestdto.setCreatedBy(user);
			String result = treatmentService.addAdmittedPatientTreatmentDetails(null,id);
			if (result.equalsIgnoreCase(MessageConstants.ADD_Treatment_SUCCESS_MESSAGE)) {
				ra.addFlashAttribute("successMessage", MessageConstants.ADD_Treatment_SUCCESS_MESSAGE);
				PatientResponseDto admittedPatientResult = patientService.getPatientById(id);
				model.addAttribute("patientList", admittedPatientResult);
				return "redirect:/admits/AdmitProfile/"+id;
			} else {
				ra.addFlashAttribute("errorMessage", MessageConstants.ADD_Treatment_ERROR_MESSAGE);
				return "error";
			}

		}
		return "login";
	}
	
	 @PostMapping("/saveTreatmentData/{id}")
	 public  String saveData(@PathVariable Long id,@RequestBody List<TreatmentRequest> treatmentRequestList) {
	        // Your logic to save the data to a database or perform any other action
	        // For simplicity, we'll just log the data
	        //System.out.println(data);
	        String result = treatmentService.addAdmittedPatientTreatmentDetails(treatmentRequestList,id);

	        return "redirect:/treatment/treatment-form/{id}"+id;
	    }

}
