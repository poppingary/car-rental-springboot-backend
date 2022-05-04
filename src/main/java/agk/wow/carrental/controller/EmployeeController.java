package agk.wow.carrental.controller;

import agk.wow.carrental.constant.UserType;
import agk.wow.carrental.rpcdomain.request.EmployeeRegisterRequest;
import agk.wow.carrental.rpcdomain.request.JwtRequest;
import agk.wow.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	private UserService userService;

	@PostMapping(value = "/register")
	public ResponseEntity register(@RequestBody EmployeeRegisterRequest employeeRegisterRequest) {
		return this.userService.register(employeeRegisterRequest, UserType.EMPLOYEE);
	}

	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody JwtRequest jwtRequest) throws Exception {
		return this.userService.login(jwtRequest);
	}
}