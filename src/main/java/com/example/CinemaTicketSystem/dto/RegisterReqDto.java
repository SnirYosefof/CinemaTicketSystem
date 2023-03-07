package com.example.CinemaTicketSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//Created by sniryosefof on 22 פבר׳
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReqDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @Length(min=4, max =16)
    private String password;
}
