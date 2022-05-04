package agk.wow.carrental.service;

import agk.wow.carrental.model.*;
import agk.wow.carrental.repository.CustomerRepository;
import agk.wow.carrental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	private static final String CUSTOMER = "Customer";
	private static final String EMPLOYEE = "Employee";

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee save(UserDto userDto, String user) {
		if (user.equals(CUSTOMER)) {
			CustomerDto customerDto = (CustomerDto) userDto;
			Customer newCustomer = new Customer();

			newCustomer.setEmail(customerDto.getEmail());
			newCustomer.setPassword(bcryptEncoder.encode(customerDto.getPassword()));
			newCustomer.setPhoneNumber(customerDto.getPhoneNumber());
			newCustomer.setStreet(customerDto.getStreet());
			newCustomer.setCity(customerDto.getCity());
			newCustomer.setState(customerDto.getState());
			newCustomer.setZipcode(customerDto.getZipcode());
			newCustomer.setFirstName(customerDto.getFirstName());
			newCustomer.setLastName(customerDto.getLastName());

			Customer individualCustomer = this.customerRepository.save(newCustomer);
			Employee employee = new Employee();
			employee.setFirstName(individualCustomer.getFirstName());
			employee.setLastName(individualCustomer.getLastName());
			employee.setEmail(individualCustomer.getEmail());

			return employee;
		} else {
			EmployeeDto employeeDto = (EmployeeDto) userDto;
			Employee newEmployee = new Employee();

			newEmployee.setFirstName(employeeDto.getFirstName());
			newEmployee.setLastName(employeeDto.getLastName());
			newEmployee.setEmail(employeeDto.getEmail());
			newEmployee.setPassword(bcryptEncoder.encode(employeeDto.getPassword()));
			newEmployee.setRole(EMPLOYEE);

			return this.employeeRepository.save(newEmployee);
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