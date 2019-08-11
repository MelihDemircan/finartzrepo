package melih.demircan.convertes;

import java.util.List;
import java.util.stream.Collectors;

import melih.demircan.persistence.model.User;
import melih.demircan.web.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDto implements Converter<User, UserDto> {

	@Override
	public UserDto convert(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.get_id().toHexString());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
//		userDto.setTelNumber(user.getTelNumber());
		return userDto;
	}

	public List<UserDto> convertList(List<User> users) {
		return users.stream().map(user -> convert(user)).collect(Collectors.toList());
	}
}