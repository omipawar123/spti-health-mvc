package com.nt.cntrl;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.Dto.patients.PatientOPDHistoryResponseDto;
import com.nt.Dto.patients.PatientPageDto;
import com.nt.Dto.patients.PatientRequestDto;
import com.nt.Dto.patients.PatientResponseDto;
import com.nt.constants.MessageConstants;
import com.nt.service.OpdPatientHistoryService;
import com.nt.service.PatientService;

@Controller
@RequestMapping( "/patients" )
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private OpdPatientHistoryService opdPatientHistoryService;

	@GetMapping( "/add-form" )
	public String addPatientForm( HttpSession session, Model model ) {
		return "patients/add";
	}
	
	@GetMapping( "/admit-form/{id}" )
	public String admitPatientForm(@PathVariable Long id, HttpSession session, Model model ) {
		PatientResponseDto result = patientService.getPatientById( id );
		model.addAttribute( "patient", result );
		
		return "patients/AdmitForm";
	}
    
	@PostMapping
	public String addPatient(   @Valid @ModelAttribute PatientRequestDto dto, Model model, HttpSession session, RedirectAttributes ra ) {

		String user = ( String ) session.getAttribute( "username" );

		if ( user != null ) {
			Integer branchId = ( Integer ) session.getAttribute( "branchId" );
			dto.setBranch( branchId );
			String result = patientService.addPatient( dto );

			if ( result.equalsIgnoreCase( MessageConstants.ADD_PATIENT_SUCCESS_MESSAGE ) ) {
				ra.addFlashAttribute( "successMessage", MessageConstants.ADD_PATIENT_SUCCESS_MESSAGE );
				return "redirect:/patients";
			} else {
				ra.addFlashAttribute( "errorMessage", MessageConstants.ADD_PATIENT_ERROR_MSG );
				return "error";
			}

		}
		return "login";
	}

	@GetMapping
	public String allPatients( Model model, HttpSession session ) {

		String user = ( String ) session.getAttribute( "username" );
		Integer branchId = ( Integer ) session.getAttribute( "branchId" );

		PatientPageDto listofDoctors = patientService.allPatients( branchId );

		if ( user != null ) {
			if ( listofDoctors != null ) {
				model.addAttribute( "list", listofDoctors );
				return "patients/all";
			} else {
				model.addAttribute( "errorMsg", "something went wrong" );
				return "error";
			}
		}

		return "login";

	}

	@GetMapping( "/id" )
	public String selectPatientById( @RequestParam long id, Model model, HttpSession session ) {

		String user = ( String ) session.getAttribute( "username" );

		PatientResponseDto result = patientService.getPatientById( id );

		if ( user != null ) {

			if ( result != null ) {
				model.addAttribute( "patient", result );
				return "patients/update";
			} else {
				model.addAttribute( "errorMsg", "Unable to update" );
				return "error";
			}
		}
		return "login";

	}

	@PostMapping( "/update" )
	public String updatePatient( @ModelAttribute PatientRequestDto dto, Model model, HttpSession session, RedirectAttributes ra ) {

		String user = ( String ) session.getAttribute( "username" );

		if ( user != null ) {
			Integer branchId = ( Integer ) session.getAttribute( "branchId" );
			dto.setBranch( branchId );
			String result = patientService.updatePatient( dto );

			if ( result.equalsIgnoreCase( MessageConstants.UPDATE_PATIENT_SUCCESS_MESSAGE ) ) {
				ra.addFlashAttribute( "successMessage", MessageConstants.UPDATE_PATIENT_SUCCESS_MESSAGE );
				return "redirect:/patients";
			} else {
				ra.addFlashAttribute( "errorMessage", MessageConstants.UPDATE_PATIENT_ERROR_MESSAGE );
				return "error";
			}

		}
		return "login";
	}

	@GetMapping( "/details-form/{id}" )
	public String getPatientDetailsForm( @PathVariable Long id, Model model ) {

		PatientResponseDto result = patientService.getPatientById( id );
		model.addAttribute( "patientList", result );

		List<PatientOPDHistoryResponseDto> opdHistory = opdPatientHistoryService.getPatientOpdHistory( id ); 
		model.addAttribute( "opdHistory", opdHistory );
		return "patients/details";
	}
	
	@GetMapping("/phoneNumber/{phoneNumber}")
	public String patientByPhoneNumber(@PathVariable String phoneNumber, Model model) {
		List<PatientResponseDto> phone = patientService.findPatientsByPhoneNumber(phoneNumber);

		if (phone.isEmpty()) {
			return "patients/add";
		} else {
			if (phone.size() == 1) {
				Long id = phone.get(0).getId();
				List<PatientOPDHistoryResponseDto> opdHistory = opdPatientHistoryService.getPatientOpdHistory(id);
				model.addAttribute("patientSingleData", phone.get(0));
				model.addAttribute("opd", opdHistory);
				return "patients/details";
			} else {
				model.addAttribute("phoneNumber", phone);
				return "patients/all";
			}

		}
	}
	
	@GetMapping("/reports")
	public String report() {
		
			
		return "patients/reports";
	}

}