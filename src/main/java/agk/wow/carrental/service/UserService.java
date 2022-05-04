package agk.wow.carrental.service;

import agk.wow.carrental.config.TokenUtil;
import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.constant.UserType;
import agk.wow.carrental.model.*;
import agk.wow.carrental.repository.CustomerRepository;
import agk.wow.carrental.repository.EmployeeRepository;
import agk.wow.carrental.rpcdomain.ResponseBody;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	public ResponseEntity register(UserDto userDto, UserType userType) {
		switch(userType) {
			case CUSTOMER:
				CustomerDto customerDto = (CustomerDto) userDto;
				Customer newCustomer = new Customer();
				BeanUtils.copyProperties(customerDto, newCustomer);

				if (isCustomerRegistered(newCustomer)) {
					return new ResponseEntity(new ResponseBody(ResponseBodyMessage.USER_IS_REGISTERED.getMessage()), HttpStatus.BAD_REQUEST);
				}
				newCustomer.setPassword(this.bcryptEncoder.encode(newCustomer.getPassword()));
				this.customerRepository.save(newCustomer);
				break;
			case EMPLOYEE:
				EmployeeDto employeeDto = (EmployeeDto) userDto;
				Employee newEmployee = new Employee();
				BeanUtils.copyProperties(employeeDto, newEmployee);

				if (isEmployeeRegistered(newEmployee)) {
					return new ResponseEntity(new ResponseBody(ResponseBodyMessage.USER_IS_REGISTERED.getMessage()), HttpStatus.BAD_REQUEST);
				}
				newEmployee.setPassword(this.bcryptEncoder.encode(newEmployee.getPassword()));
				this.employeeRepository.save(newEmployee);
				break;
		}

		return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
	}

	private boolean isCustomerRegistered(Customer customer) {
		return !ObjectUtils.isEmpty(this.customerRepository.findByEmail(customer.getEmail()));
	}

	private boolean isEmployeeRegistered(Employee employee) {
		return !ObjectUtils.isEmpty(this.employeeRepository.findByEmail(employee.getEmail()));
	}

	public ResponseEntity login(JwtRequest jwtRequest) throws Exception {
		String email = jwtRequest.getEmail();
		String password = jwtRequest.getPassword();

		this.authenticate(email, password);
		String token = this.tokenUtil.generateToken(email);

		return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), token), HttpStatus.OK);
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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = this.customerRepository.findByEmail(email);
		Employee employee = this.employeeRepository.findByEmail(email);
		if (customer == null && employee == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}

		if (!ObjectUtils.isEmpty(customer)) {
			return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
		} else {
			return new User(employee.getEmail(), employee.getPassword(), new ArrayList<>());
		}
	}
}