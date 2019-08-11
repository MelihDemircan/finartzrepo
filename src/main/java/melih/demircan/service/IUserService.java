package melih.demircan.service;

import java.util.List;

import melih.demircan.persistence.model.User;
import melih.demircan.web.dto.UserDto;

public interface IUserService {

	List<User> listAll();

	User getById(String id);

	User saveOrUpdate(User user);

	void delete(String id);

	User saveOrUpdateUserDto(UserDto userDto);


}
