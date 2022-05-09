package agk.wow.carrental.service;

import agk.wow.carrental.config.TokenUtil;
import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.constant.UserType;
import agk.wow.carrental.model.*;
import agk.wow.carrental.repository.CustomerRepository;
import agk.wow.carrental.repository.EmployeeRepository;
import agk.wow.carrental.rpcdomain.ResponseBody;
import agk.wow.carrental.rpcdomain.request.*;
import agk.wow.carrental.rpcdomain.response.JwtResponse;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Optional;

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

	public ResponseEntity<?> register(RegisterRequest registerRequest, UserType userType) {
		switch(userType) {
			case CUSTOMER:
				CustomerRegisterRequest customerRegisterRequest = (CustomerRegisterRequest) registerRequest;
				Customer newCustomer = new Customer();
				BeanUtils.copyProperties(customerRegisterRequest, newCustomer);

				if (isCustomerRegistered(newCustomer)) {
					return new ResponseEntity(new ResponseBody(ResponseBodyMessage.USER_IS_REGISTERED.getMessage()), HttpStatus.BAD_REQUEST);
				}
				newCustomer.setPassword(this.bcryptEncoder.encode(newCustomer.getPassword()));
				this.customerRepository.save(newCustomer);
				break;
			case EMPLOYEE:
				EmployeeRegisterRequest employeeRegisterRequest = (EmployeeRegisterRequest) registerRequest;
				Employee newEmployee = new Employee();
				BeanUtils.copyProperties(employeeRegisterRequest, newEmployee);

				if (isEmployeeRegistered(newEmployee)) {
					return new ResponseEntity(new ResponseBody(ResponseBodyMessage.USER_IS_REGISTERED.getMessage()), HttpStatus.BAD_REQUEST);
				}
				newEmployee.setPassword(this.bcryptEncoder.encode(newEmployee.getPassword()));
				newEmployee.setRole(UserType.EMPLOYEE.getType());
				this.employeeRepository.save(newEmployee);
				break;
		}

		return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
	}

	private boolean isCustomerRegistered(Customer customer) {
		return !ObjectUtils.isEmpty(this.customerRepository.findByEmail(customer.getEmail()));
	}

	private boolean isEmployeeRegistered(Employee employee) {
		return !ObjectUtils.isEmpty(this.employeeRepository.findByEmail(employee.getEmail()));
	}

	public ResponseEntity<?> login(JwtRequest jwtRequest) throws Exception {
		String email = jwtRequest.getEmail();
		String password = jwtRequest.getPassword();

		this.authenticate(email, password);
		String token = this.tokenUtil.generateToken(email);
		Customer customer = this.customerRepository.findByEmail(email);
		Employee employee = this.employeeRepository.findByEmail(email);
		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setToken(token);
		jwtResponse.setEmail(email);
		if (!ObjectUtils.isEmpty(customer)) {
			jwtResponse.setCustomerId(customer.getId());
			jwtResponse.setRole(UserType.CUSTOMER.getType());
			jwtResponse.setFirstName(customer.getFirstName());
			jwtResponse.setLastName(customer.getLastName());
		} else {
			jwtResponse.setEmployeeId(employee.getId());
			jwtResponse.setRole(employee.getRole());
			jwtResponse.setFirstName(employee.getFirstName());
			jwtResponse.setLastName(employee.getLastName());
		}

		return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), jwtResponse), HttpStatus.OK);
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

	@Transactional
	public ResponseEntity<?> updateIndividualCustomer(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		String customerId = updateIndividualCustomerRequest.getCustomerId();
		String firstName = updateIndividualCustomerRequest.getFirstName();
		String middleName = updateIndividualCustomerRequest. getMiddleName();
		String lastName = updateIndividualCustomerRequest.getLastName();
		String phoneNumber = updateIndividualCustomerRequest.getPhoneNumber();
		String street = updateIndividualCustomerRequest.getStreet();
		String city = updateIndividualCustomerRequest.getCity();
		String state = updateIndividualCustomerRequest.getState();
		String zipcode = updateIndividualCustomerRequest.getZipcode();
		String insuranceCompany = updateIndividualCustomerRequest.getInsuranceCompany();
		String insuranceNumber = updateIndividualCustomerRequest.getInsuranceNumber();
		String driverLicense = updateIndividualCustomerRequest.getDriverLicense();

		this.customerRepository.updateIndividualCustomer(customerId, firstName, middleName, lastName, phoneNumber, street, city, state, zipcode, insuranceCompany, insuranceNumber, driverLicense);

		return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> updateEmployeeProfile(UpdateEmployeeProfileRequest updateEmployeeProfileRequest) {
		String employeeId = updateEmployeeProfileRequest.getEmployeeId();
		String firstName = updateEmployeeProfileRequest.getFirstName();
		String middleName = updateEmployeeProfileRequest.getMiddleName();
		String lastName = updateEmployeeProfileRequest.getLastName();

		this.employeeRepository.updateEmployee(employeeId, firstName, middleName, lastName);

		return new ResponseEntity<>((new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage())), HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> updateCustomerCredential(UpdateCustomerCredentialRequest updateCustomerCredentialRequest) {
		String customerId = updateCustomerCredentialRequest.getCustomerId();
		String currentPassword = updateCustomerCredentialRequest.getCurrentPassword();
		String newPassword = this.bcryptEncoder.encode(updateCustomerCredentialRequest.getNewPassword());

		Optional<Customer> customerOptional = this.customerRepository.findById(customerId);
		Customer customer = null;
		if (customerOptional.isPresent()) {
			customer = customerOptional.get();
		}
		if (ObjectUtils.isEmpty(customer)) {
			return new ResponseEntity<>((new ResponseBody(ResponseBodyMessage.NO_CUSTOMER.getMessage())), HttpStatus.BAD_REQUEST);
		}
		if (!this.bcryptEncoder.matches(currentPassword, customer.getPassword())) {
			return new ResponseEntity<>((new ResponseBody(ResponseBodyMessage.PASSWORD_NOT_MATCH.getMessage())), HttpStatus.BAD_REQUEST);
		}

		this.customerRepository.updateCredential(customerId, newPassword);

		return new ResponseEntity<>((new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage())), HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> updateEmployeeCredential(UpdateEmployeeCredentialRequest updateEmployeeCredentialRequest) {
		String employeeId = updateEmployeeCredentialRequest.getEmployeeId();
		String currentPassword = updateEmployeeCredentialRequest.getCurrentPassword();
		String newPassword = this.bcryptEncoder.encode(updateEmployeeCredentialRequest.getNewPassword());

		Optional<Employee> employeeOptional = this.employeeRepository.findById(employeeId);
		Employee employee = null;
		if (employeeOptional.isPresent()) {
			employee = employeeOptional.get();
		}
		if (ObjectUtils.isEmpty(employee)) {
			return new ResponseEntity<>((new ResponseBody(ResponseBodyMessage.NO_CUSTOMER.getMessage())), HttpStatus.BAD_REQUEST);
		}
		if (!this.bcryptEncoder.matches(currentPassword, employee.getPassword())) {
			return new ResponseEntity<>((new ResponseBody(ResponseBodyMessage.PASSWORD_NOT_MATCH.getMessage())), HttpStatus.BAD_REQUEST);
		}

		this.employeeRepository.updateCredential(employeeId, newPassword);

		return new ResponseEntity<>((new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage())), HttpStatus.OK);
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