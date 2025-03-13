package com.nt.cntrl;

import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nt.Dto.patients.AdmitPatientPageDto;
import com.nt.Dto.patients.AdmitPatientRequestDto;
import com.nt.Dto.patients.AdmitPatientResponseDto;
import com.nt.Dto.patients.PatientResponseDto;
import com.nt.service.AdmitPatientService;
import com.nt.service.PatientService;

@Controller
@RequestMapping("/admits")
public class AdmitPatientController {

	@Autowired
	private AdmitPatientService admitPatientService;

	@Autowired
	private PatientService patientService;

	@GetMapping("/AdmitProfile/{id}")
	public String AdmitProfilePage(@PathVariable Long id, Model model, HttpSession session, RedirectAttributes ra) {
		String user = (String) session.getAttribute("username");
		if (user != null) {
			PatientResponseDto result = patientService.getPatientById(id);
			model.addAttribute("patientList", result);
		}
		return "patients/AdmitProfile";
	}

	@GetMapping("/PatientDetails/{id}")
	public String PatientDetailsPage(@PathVariable Long id, Model model, HttpSession session, RedirectAttributes ra) {
		String user = (String) session.getAttribute("username");
		if (user != null) {
			AdmitPatientResponseDto result = patientService.getAdmitPatientById(id);
			model.addAttribute("patientList", result);
		}
		return "patients/Admitpatienthistory";
	}

	@PostMapping
	public String addAdmitPatientdetails(@Valid @ModelAttribute AdmitPatientRequestDto dto, Model model,
			HttpSession session, RedirectAttributes ra) {
		String user = (String) session.getAttribute("username");

		PatientResponseDto result = patientService.getPatientById(dto.getPatientId());
		model.addAttribute("patientList", result);

		if (user != null) {

			admitPatientService.addAdmitPatientdetails(dto);

		}
		return "redirect:/admits";
	}

	@GetMapping
	public String allAdmitPatients(Model model, HttpSession session) {

		String user = (String) session.getAttribute("username");

		AdmitPatientPageDto listofAdmitPatient = admitPatientService.allAdmitPatients();

		if (user != null) {
			if (listofAdmitPatient != null) {
				model.addAttribute("list", listofAdmitPatient);
				return "patients/allAdmitPatient";
			} else {
				model.addAttribute("errorMsg", "something went wrong");
				return "error";
			}
		}

		return "login";

	}

	@GetMapping("/AdmitProfileByDate/{admissionDate}")
	@ResponseBody
	public ResponseEntity<List<PatientResponseDto>> admitProfilePageByDate(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate admissionDate, HttpSession session) {
		String user = (String) session.getAttribute("username");
		List<PatientResponseDto> resultList = admitPatientService.findByAdmissionDate(admissionDate);
		if (user != null) {
			if (resultList != null && !resultList.isEmpty()) {
				return ResponseEntity.ok(resultList);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return null;
		}
	}

	@GetMapping("/AdmitProfileByYear/{year}")
	@ResponseBody
	public ResponseEntity<List<PatientResponseDto>> admitProfilePageByYear(@PathVariable int year,
			HttpSession session) {
		String user = (String) session.getAttribute("username");
		List<PatientResponseDto> resultOfYear = admitPatientService.findByYear(year);
		if (user != null) {
			if (resultOfYear != null && !resultOfYear.isEmpty()) {
				return ResponseEntity.ok(resultOfYear);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return null;
		}
	}

	@GetMapping("/admissionStartAndEndDate/{startDate}/{endDate}")
	@ResponseBody
	public ResponseEntity<List<PatientResponseDto>> getDataByStartAndEndDates(
			@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			HttpSession session) {

		String user = (String) session.getAttribute("username");
		List<PatientResponseDto> resultOfRandom = admitPatientService.findByStartAndEndDates(startDate, endDate);
		if (user != null) {
			if (resultOfRandom != null && !resultOfRandom.isEmpty()) {
				return ResponseEntity.ok(resultOfRandom);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return null;
		}
	}

	@GetMapping("/getAllPatient")
	@ResponseBody
	public ResponseEntity<List<PatientResponseDto>> allPatient() {
		List<PatientResponseDto> patientResponseDtos = admitPatientService.findallPatient();
		if (patientResponseDtos != null) {
			return ResponseEntity.ok(patientResponseDtos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ResponseBody
	@GetMapping("/todaysWeeksMonthPatient/{todayrecord}")
	public ResponseEntity<?> getTodayWeekMonthData(@PathVariable String todayrecord) {
		List<PatientResponseDto> opdPatients = admitPatientService.getTodaysWeekMonthPatient(todayrecord);
		if (opdPatients.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the specified date.");
		}
		return ResponseEntity.ok(opdPatients);
	}

	@ResponseBody
	@GetMapping("/getCurrentdateAdmitPatient/{todayrecord}")
	public List<AdmitPatientResponseDto> todayAdmitPatient(@PathVariable String todayrecord) {

		List<AdmitPatientResponseDto> listofAdmitPatient = admitPatientService.todayAdmitPatient(todayrecord);

		return listofAdmitPatient;
	}

	@ResponseBody
	@GetMapping("/getCurrentdateDischargePatient/{todayrecord}")
	public List<AdmitPatientResponseDto> todayDischargePatient(@PathVariable String todayrecord) {
		List<AdmitPatientResponseDto> listofAdmitPatient = admitPatientService.todayDischargePatient(todayrecord);

		return listofAdmitPatient;
	}

	@ResponseBody
	@GetMapping("/getTodayWeeklyMonthlyDischargePatient/{todayrecord}")
	public ResponseEntity<?> getTodayWeeklyMonthlyDischargePatients(@PathVariable String todayrecord) {
		try {
			List<AdmitPatientResponseDto> todayWeeklyMonthlyDischargePatients = admitPatientService
					.todayWeeklyMonthlyDischargePatient(todayrecord);
			if (todayWeeklyMonthlyDischargePatients != null && !todayWeeklyMonthlyDischargePatients.isEmpty()) {
				return ResponseEntity.ok(todayWeeklyMonthlyDischargePatients);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while fetching today's discharge patients.");
		}
	}

	/*
	 * @GetMapping("/admissionStartAndEndDate/{todayrecord}")
	 * 
	 * @ResponseBody public ResponseEntity<List<PatientResponseDto>>
	 * getDataByStartToEndDate(@PathVariable String todayrecord ,HttpSession session
	 * ) {
	 * 
	 * String user = (String) session.getAttribute("username");
	 * List<PatientResponseDto> resultOfRandom =
	 * admitPatientService.findDataByStartToEndDate(todayrecord); if (user != null)
	 * { if (resultOfRandom != null && !resultOfRandom.isEmpty()) { return
	 * ResponseEntity.ok(resultOfRandom); } else { return
	 * ResponseEntity.notFound().build(); } } else { return null; } }
	 */

}
