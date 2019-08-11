package melih.demircan.convertes;

import melih.demircan.persistence.model.User;
import melih.demircan.web.dto.UserDto;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class UserDtoToUser implements Converter<UserDto, User> {

	@Override
	public User convert(UserDto userDto) {
		User user = new User();
		if (userDto.getId() != null && !StringUtils.isEmpty(userDto.getId())) {
			user.set_id(new ObjectId(userDto.getId()));
		}

		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
//		user.setTelNumber(userDto.getTelNumber());

		return user;
	}
}