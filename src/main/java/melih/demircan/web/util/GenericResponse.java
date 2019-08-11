package melih.demircan.web.util;

import java.util.List;
import java.util.stream.Collectors;

import melih.demircan.web.dto.UserDto;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class GenericResponse {

	private String message;
	private String error;
	private List<UserDto> userDtos;
	private UserDto userDtoEdit;

	public GenericResponse(final String message) {
		super();
		this.message = message;
	}

	public GenericResponse(final String message, final String error) {
		super();
		this.message = message;
		this.error = error;
	}

	public GenericResponse(List<ObjectError> allErrors, String error) {
		this.error = error;
		String temp = allErrors.stream().map(e -> {
			if (e instanceof FieldError) {
				return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\""
						+ e.getDefaultMessage() + "\"}";
			} else {
				return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e.getDefaultMessage()
						+ "\"}";
			}
		}).collect(Collectors.joining(","));
		this.message = "[" + temp + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(final String error) {
		this.error = error;
	}

	public List<UserDto> getUserDtos() {
		return userDtos;
	}

	public void setUserDtos(List<UserDto> userDtos) {
		this.userDtos = userDtos;
	}

	public UserDto getUserDtoEdit() {
		return userDtoEdit;
	}

	public void setUserDtoEdit(UserDto userDtoEdit) {
		this.userDtoEdit = userDtoEdit;
	}
}