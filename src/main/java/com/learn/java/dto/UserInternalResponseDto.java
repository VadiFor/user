package com.learn.java.dto;

import com.learn.java.model.enums.PositionUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserInternalResponseDto {
	private String id;
	private String fullName;
	private String email;
	private String phone;
	private PositionUser position;
	private Boolean active;
}
