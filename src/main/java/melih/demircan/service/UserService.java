package melih.demircan.service;

import java.util.ArrayList;
import java.util.List;

import melih.demircan.convertes.UserDtoToUser;
import melih.demircan.persistence.dao.UserRepository;
import melih.demircan.persistence.model.User;
import melih.demircan.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

	private UserRepository userRepository;
	private UserDtoToUser userDtoToUser;

	@Autowired
	public UserService(UserRepository userRepository, UserDtoToUser userDtoToUser) {
		this.userRepository = userRepository;
		this.userDtoToUser = userDtoToUser;
	}

	@Override
	public List<User> listAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add); // fun with Java 8
		return users;
	}

	@Override
	public User getById(String id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User saveOrUpdate(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public void delete(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public User saveOrUpdateUserDto(UserDto userDto) {
		User savedUser = saveOrUpdate(userDtoToUser.convert(userDto));
		System.out.println("Saved User Id: " + savedUser.get_id());
		return savedUser;
	}

}
