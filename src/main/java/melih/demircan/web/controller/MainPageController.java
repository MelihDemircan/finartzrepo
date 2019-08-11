package melih.demircan.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import melih.demircan.captcha.ICaptchaService;
import melih.demircan.convertes.UserToUserDto;
import melih.demircan.service.IUserService;
import melih.demircan.web.dto.UserDto;
import melih.demircan.web.util.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainPageController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserService userService;

	@Autowired
	private ICaptchaService captchaService;

	@Autowired
	private UserToUserDto userToUserDto;

	public MainPageController() {
		super();
	}

	@GetMapping("/")
	public String index(Model model) {
		LOGGER.debug("mainpage GET");

		model.addAttribute("userdto", userToUserDto.convertList(userService.listAll()));
		return "mainpage";
	}

	@PostMapping("/api/delete/{id}")
	public ResponseEntity<?> getDeleteResultViaAjax(@PathVariable String id) {
		LOGGER.debug("/api/delete/{id} POST");

		userService.delete(id);

		GenericResponse genericResponse = new GenericResponse("success");

		genericResponse.setUserDtos(userToUserDto.convertList(userService.listAll()));

		LOGGER.debug(genericResponse.getUserDtos().size() + " size");
		LOGGER.debug(id + " id");
//		LOGGER.debug(accountDto.getId() + " id");

		return ResponseEntity.ok(genericResponse);
	}

	@PostMapping("/api/reflesh/{id}")
	public ResponseEntity<?> getRefleshResultViaAjax(@PathVariable String id) {

		GenericResponse genericResponse = new GenericResponse("success");

		UserDto userDto = userToUserDto.convert(userService.getById(id));

		genericResponse.setUserDtoEdit(userDto);

		return ResponseEntity.ok(genericResponse);
	}

	@RequestMapping(value = "/mainpage", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse xxx(@Valid final UserDto accountDto, final HttpServletRequest request) {
		LOGGER.debug("mainpage POST");

		final String response = request.getParameter("g-recaptcha-response");
		captchaService.processResponse(response);

		LOGGER.debug("Registering user account with information: {}", accountDto);

		userService.saveOrUpdateUserDto(accountDto);

		GenericResponse genericResponse = new GenericResponse("success");

		genericResponse.setUserDtos(userToUserDto.convertList(userService.listAll()));
//        final User registered = userService.registerNewUserAccount(accountDto);
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
		return genericResponse;
	}

	@RequestMapping(value = "/user/mainpage", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse captchaRegisterUserAccount(@Valid final UserDto accountDto,
			final HttpServletRequest request) {

		final String response = request.getParameter("g-recaptcha-response");
		captchaService.processResponse(response);

		LOGGER.debug("Registering user account with information: {}", accountDto);

		userService.saveOrUpdateUserDto(accountDto);

		;

		GenericResponse genericResponse = new GenericResponse("success");

		genericResponse.setUserDtos(userToUserDto.convertList(userService.listAll()));
//        final User registered = userService.registerNewUserAccount(accountDto);
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
		return genericResponse;
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}
