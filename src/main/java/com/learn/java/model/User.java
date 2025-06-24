package com.learn.java.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learn.java.model.enums.GenderUser;
import com.learn.java.model.enums.PositionUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "usr")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@Builder.Default
	@NotNull(message = "Id user required")
	private String id = UUID.randomUUID().toString().replace("-","").toLowerCase();
	
	@Column(nullable = false)
	@NotNull(message = "FirstName user required")
	private String firstName;
	
	@Column(nullable = false)
	@NotNull(message = "LastName user required")
	private String lastName;
	
	private String middleName;
	
	@Column(nullable = false)
	@NotNull(message = "Gender user required")
	private GenderUser gender;
	
	@Column(nullable = false)
	@NotNull(message = "DateOfBirth user required")
	@JsonFormat(pattern = "dd.MM.yyyy")
	private LocalDate dateOfBirth;
	
	@Column(nullable = false)
	private Integer age;
	
	@Column(nullable = false, unique = true)
	@Email(message = "Email must be correct")
	@NotNull(message = "Email user required")
	private String email;
	
	@Column(length = 16, unique = true)
	@Pattern(regexp = "\\+7\\s\\d{3}\\s\\d{3}-\\d{2}-\\d{2}",
			message = "Phone  number user must be in the format - +7 *** ***-**-**")
	private String phone;
	
	@Column(nullable = false)
	@NotNull(message = "Position user required")
	private PositionUser position;
	
	@Column(nullable = false)
	@NotNull(message = "Active user required")
	private Boolean active;
}
