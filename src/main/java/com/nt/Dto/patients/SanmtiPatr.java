package com.nt.Dto.patients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SanmtiPatr {
    
    @NotEmpty(message = "Responsible Person cannot be empty")
    @Size(max = 100, message = "Responsible Person cannot be longer than 100 characters")
    private String Responsible_Person;
}
