package com.nt.cntrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nt.Dto.patients.DiagnosisResponseDto;
import com.nt.service.StaticDataService;

@Controller
@RequestMapping("/diagnosiss")
public class StaticDataController {

    @Autowired
    private StaticDataService staticService;

    @GetMapping("/getAllPatient")
    public String report(Model model) {
        List<DiagnosisResponseDto> diagnosisList = staticService.getAllDiagnosis();
        model.addAttribute("Diagnosis", diagnosisList);
        return "opdPatient/diagnosisHistory";
    }
    @GetMapping("/getDiagnosisData")
    @ResponseBody
    public ResponseEntity<List<DiagnosisResponseDto>> getDiagnosisData() {
        List<DiagnosisResponseDto> diagnosisList = staticService.getAllDiagnosis();
        return ResponseEntity.ok(diagnosisList);
}}
