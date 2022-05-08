package agk.wow.carrental.controller;

import agk.wow.carrental.constant.UserType;
import agk.wow.carrental.rpcdomain.request.*;
import agk.wow.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	private UserService userService;

	@PostMapping(value = "/register")
	public ResponseEntity<?> register(@RequestBody EmployeeRegisterRequest employeeRegisterRequest) {
		return this.userService.register(employeeRegisterRequest, UserType.EMPLOYEE);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) throws Exception {
		return this.userService.login(jwtRequest);
	}

	@PostMapping(value = "/update/profile")
	public ResponseEntity<?> updateProfile(@RequestBody UpdateEmployeeProfileRequest updateEmployeeProfileRequest) {
		return this.userService.updateEmployeeProfile(updateEmployeeProfileRequest);
	}

	@PostMapping(value = "/update/credential")
	public ResponseEntity<?> updateCredential(@RequestBody UpdateEmployeeCredentialRequest updateEmployeeCredentialRequest) {
		return this.userService.updateEmployeeCredential(updateEmployeeCredentialRequest);
	}
}