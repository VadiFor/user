package com.learn.java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

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
	@NotNull(message = "Name user required")
	private String name;
	
	@Column(nullable = false, unique = true)
	@Email(message = "Email must be correct")
	@NotNull(message = "Email user required")
	private String email;
	
	@Column(length = 16, unique = true)
	@Pattern(regexp = "\\+7\\s\\d{3}\\s\\d{3}-\\d{2}-\\d{2}",
			message = "Phone  number user must be in the format - +7 *** ***-**-**")
	private String phone;
}
