package com.learn.java.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learn.java.model.enums.GenderUser;
import com.learn.java.model.enums.PositionUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {
	private String firstName;
	private String lastName;
	private String middleName;
	private GenderUser gender;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	@Email(message = "Email must be correct")
	private String email;
	@Pattern(regexp = "\\+7\\s\\d{3}\\s\\d{3}-\\d{2}-\\d{2}",
			message = "Phone  number user must be in the format - +7 *** ***-**-**")
	private String phone;
	private PositionUser position;
	private Boolean active;
}
