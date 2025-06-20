package com.learn.java.mapper;

import com.learn.java.dto.UserCreateRequestDto;
import com.learn.java.dto.UserInternalResponseDto;
import com.learn.java.dto.UserUpdateRequestDto;
import com.learn.java.model.User;
import org.mapstruct.*;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUserFromDto(UserUpdateRequestDto userUpdateRequestDto, @MappingTarget User user);
	
	@AfterMapping
	default void updateAgeAfterDateOfBirth(UserUpdateRequestDto userUpdateRequestDto, @MappingTarget User user) {
		if (userUpdateRequestDto.getDateOfBirth() != null) {
			user.setAge(LocalDate.now().getYear() - userUpdateRequestDto.getDateOfBirth().getYear());
		}
	}
	
	@Mapping(target = "fullName", expression = "java(makeFullName(user.getFirstName(),user.getLastName(),user.getMiddleName()))")
	UserInternalResponseDto toInternalDto(User user);
	
	default String makeFullName(String firstName, String lastName, String middleName) {
		return lastName + " " + firstName + (middleName != null ? " " + middleName : "");
	}
	
	@Mapping(target = "age", expression = "java(calculateAge(userCreateRequestDto.getDateOfBirth()))")
	User toUser(UserCreateRequestDto userCreateRequestDto);
	
	default int calculateAge(LocalDate dateOfBirth) {
		return dateOfBirth != null ? LocalDate.now().getYear() - dateOfBirth.getYear() : 0;
	}
}
