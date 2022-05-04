package agk.wow.carrental.controller;

import agk.wow.carrental.config.TokenUtil;
import agk.wow.carrental.constant.UserType;
import agk.wow.carrental.model.EmployeeDto;
import agk.wow.carrental.model.JwtRequest;
import agk.wow.carrental.model.JwtResponse;
import agk.wow.carrental.model.UserDto;
import agk.wow.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/register")
	public ResponseEntity register(@RequestBody EmployeeDto employeeDto) {
		return this.userService.register(employeeDto, UserType.EMPLOYEE);
	}

	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody JwtRequest jwtRequest) throws Exception {
		String email = jwtRequest.getEmail();
		String password = jwtRequest.getPassword();

		this.authenticate(email, password);
		String token = this.tokenUtil.generateToken(email);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String email, String password) throws Exception {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}