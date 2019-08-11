package melih.demircan.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

	private String id;

	@NotNull
	@Size(min = 1, message = "{Size.userDto.firstName}")
	private String firstName;

	@NotNull
	@Size(min = 1, message = "{Size.userDto.lastName}")
	private String lastName;

	@Pattern(regexp = "(^$|[0-9]{10})", message = "{Size.userDto.telephoneNumber}")
	private String telephoneNumber;

	public UserDto() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(final String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("UserDto [firstName=").append(firstName).append(", lastName=").append(lastName)
				.append(", telephone=").append(telephoneNumber).append("]");
		return builder.toString();
	}

}
