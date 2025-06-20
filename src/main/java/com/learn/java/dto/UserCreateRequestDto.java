package com.learn.java.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learn.java.model.enums.GenderUser;
import com.learn.java.model.enums.PositionUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDto {
	@NotNull(message = "FirstName user required")
	private String firstName;
	
	@NotNull(message = "LastName user required")
	private String lastName;
	
	private String middleName;
	
	@NotNull(message = "Gender user required")
	private GenderUser gender;
	
	@NotNull(message = "DateOfBirth user required")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@Email(message = "Email must be correct")
	@NotNull(message = "Email user required")
	private String email;
	
	@Pattern(regexp = "\\+7\\s\\d{3}\\s\\d{3}-\\d{2}-\\d{2}",
			message = "Phone  number user must be in the format - +7 *** ***-**-**")
	private String phone;
	
	@NotNull(message = "Position user required")
	private PositionUser position;
	
	@NotNull(message = "Active user required")
	private Boolean active;
}
