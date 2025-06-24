package com.learn.java.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learn.java.model.enums.GenderUser;
import com.learn.java.model.enums.PositionUser;
import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "First name user", example = "Ivan")
	private String firstName;
	
	@NotNull(message = "LastName user required")
	@Schema(description = "Last name user", example = "Ivanov")
	private String lastName;
	
	@Schema(description = "Middle name user", example = "Ivanovich")
	private String middleName;
	
	@NotNull(message = "Gender user required")
	@Schema(description = "Gender user", example = "MALE")
	private GenderUser gender;
	
	@NotNull(message = "DateOfBirth user required")
	@JsonFormat(pattern = "dd.MM.yyyy")
	@Schema(description = "Date of birthday user", example = "01.01.2001")
	private LocalDate dateOfBirth;
	
	@Email(message = "Email must be correct")
	@NotNull(message = "Email user required")
	@Schema(description = "Email user", example = "example@mail.ru")
	private String email;
	
	@Pattern(regexp = "\\+7\\s\\d{3}\\s\\d{3}-\\d{2}-\\d{2}",
			message = "Phone  number user must be in the format - +7 *** ***-**-**")
	@Schema(description = "Phone user", example = "+7 999 123-45-67")
	private String phone;
	
	@NotNull(message = "Position user required")
	@Schema(description = "Position user", example = "BOOKER")
	private PositionUser position;
	
	@NotNull(message = "Active user required")
	@Schema(description = "User active", example = "true")
	private Boolean active;
}
