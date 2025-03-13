package com.nt.cntrl;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.Dto.patients.DiagnosisResponseDto;
import com.nt.Dto.patients.OpdPatienPageDto;
import com.nt.Dto.patients.PatientOPDHistoryRequestDTO;
import com.nt.Dto.patients.PatientOPDHistoryResponseDto;
import com.nt.Dto.patients.PatientPageDto;
import com.nt.Dto.patients.PatientResponseDto;
import com.nt.constants.MessageConstants;
import com.nt.service.OpdPatientHistoryService;
import com.nt.service.PatientService;
import com.nt.service.StaticDataService;

@Controller
@RequestMapping( "/opds" )
public class OpdPatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private OpdPatientHistoryService opdPatientHistoryService;
	
	@ResponseBody
	@GetMapping("/todayOpdPatientHistory/{disease}/{todayrecord}/{ages}")
	public List<PatientOPDHistoryResponseDto> opdPatientHistory(@PathVariable String disease,@PathVariable String todayrecord, @PathVariable String ages) {

		List<PatientOPDHistoryResponseDto>opdPatientHistorys =  opdPatientHistoryService.opdPatientHistory(disease, todayrecord, ages);
		if(opdPatientHistorys == null || opdPatientHistorys.isEmpty()) {
			return Collections.emptyList();
		}else {
			return opdPatientHistorys;
		}
		
	}
	@GetMapping("/historyPatientDetails-form/{id}")
	public String historyPatientDetails(@PathVariable Long id, Model model) {
		PatientOPDHistoryResponseDto OPDpatient = opdPatientHistoryService.getHistoryPatientDetailspatientId( id );
		model.addAttribute( "OPDHistory", OPDpatient );
		return "patients/historyPatientDetails" ;
				
	}

	@GetMapping("/historyPatient-from/patients/{id}")
	public String historyPatient(@PathVariable Long id, Model model) {
		List<PatientOPDHistoryResponseDto> patient = opdPatientHistoryService.getHistoryPatientpatientId( id );
		model.addAttribute( "PatientOPDHistory", patient );
		return "patients/historyPatient";
	}
	
	
	@GetMapping( "/history-form/patients/{id}" )
	public String addOpdHistoryForm( @PathVariable Long id, Model model ) {
		  
		PatientResponseDto patient = patientService.getPatientById( id );
		model.addAttribute( "patient", patient );
		return "patients/addOpdHistory";
	}
	
	@GetMapping( "/billingHistory-form/patients/{id}" )
	public String addOpdBillingHistoryForm(  @Valid @PathVariable Long id, Model model ) {
//		PatientResponseDto patient = patientService.getPatientById( id );
//		model.addAttribute( "patient", patient );
		
		PatientOPDHistoryResponseDto opdHistory = opdPatientHistoryService.getOpdRecordByid( id );
		
		model.addAttribute( "opdHistory", opdHistory );
		
		return "patients/addOpdBillingHistory";
	}
	

	@PostMapping( "/history" )
	public String addOpdHistory( @ModelAttribute PatientOPDHistoryRequestDTO dto, RedirectAttributes ra ) {
		
		String result = opdPatientHistoryService.addOpdHistory( dto );
		if ( result.equals( "OPD added Successfully" ) ) {
			ra.addFlashAttribute( "successMessage", MessageConstants.ADD_OPD_HISTORY_SUCCESS_MESSAGE );
		} else {
			ra.addFlashAttribute( "errorMessage", MessageConstants.ADD_OPD_HISTORY_ERROR_MSG );
		}
		return "redirect:/patients/details-form/" + dto.getPatientId();

	}
	
	@PostMapping( "/updatePaidBill/{id}" )
	public String updatePaidBill( @ModelAttribute PatientOPDHistoryRequestDTO dto, RedirectAttributes ra ,@PathVariable long id ) {
		
		String result = opdPatientHistoryService.updatePaidBill( dto );
		if ( result.equals( "OPD added Successfully" ) ) {
			ra.addFlashAttribute( "successMessage", MessageConstants.ADD_OPD_HISTORY_SUCCESS_MESSAGE );
		} else {
			ra.addFlashAttribute( "errorMessage", MessageConstants.ADD_OPD_HISTORY_ERROR_MSG );
		}
		return "redirect:/patients/details-form/" + id;

	}
	

	
	@ResponseBody
	@GetMapping("/opdPatienBillDashbord/{todayrecord}")
	public PatientOPDHistoryResponseDto opdPatienBill2 ( @RequestBody @PathVariable String todayrecord) {
		
		PatientOPDHistoryResponseDto listofopdPatient = opdPatientHistoryService.opdPatienBill(todayrecord);
		return listofopdPatient;
	}
	
	@ResponseBody
	@GetMapping("/opdPatienBillOnlineDashbord/{todayrecord}")
	public PatientOPDHistoryResponseDto opdPatienBillOnlineDashbord ( @RequestBody @PathVariable String todayrecord) {
		
		PatientOPDHistoryResponseDto listofopdPatient = opdPatientHistoryService.opdPatienBillOnlineDashbord(todayrecord);
		return listofopdPatient;
	}
	
	@ResponseBody
	@GetMapping("/opdPatienBillCasheDashbord/{todayrecord}")
	public PatientOPDHistoryResponseDto opdPatienBillCasheDashbord ( @RequestBody @PathVariable String todayrecord) {
		
		PatientOPDHistoryResponseDto listofopdPatient = opdPatientHistoryService.opdPatienBillCasheDashbord(todayrecord);
		return listofopdPatient;
	}
	
	@ResponseBody
	@GetMapping("/getCurrentdatePatient/{todayrecord}")
	public List<PatientOPDHistoryResponseDto>  todayOpdPatient2( @PathVariable String todayrecord) {

		List<PatientOPDHistoryResponseDto> listofopdPatient = opdPatientHistoryService.todayOpdPatient2(todayrecord);
		
		return listofopdPatient;

	}

}
