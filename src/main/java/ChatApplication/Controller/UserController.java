package ChatApplication.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ChatApplication.Model.User;
import ChatApplication.Repository.UserRepository;
import ChatApplication.Response.SuccesResponse;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("userController")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("loginUser")
	public SuccesResponse LoginUser(@RequestBody User user) {

		SuccesResponse response = new SuccesResponse();

		if (user.getMobile_no() == null || user.getPassword() == null) {
			response.plzCheckField();
			return response;
		}

		String mobile_no = user.getMobile_no();
		String password = user.getPassword();

		Optional<User> use = userRepository.userGetByMobileNo(mobile_no);

		if (!use.isPresent()) {
			response.userNotFound(use);
			return response;
		}

		User user2 = use.get();
		if (user.getPassword().equals(user2.getPassword())) {
			response.UserLoginSuccesfully(user2);
			return response;
		} else {
			response.passwordWrong();
			return response;
		}

	}

}
