package agk.wow.carrental.service;

import agk.wow.carrental.model.Employee;
import agk.wow.carrental.model.EmployeeDto;
import agk.wow.carrental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeService implements UserDetailsService {
	private static final String EMPLOYEE = "Employee";

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public Employee save(EmployeeDto employeeDto) {
		Employee newEmployee = new Employee();
		newEmployee.setFirstName(employeeDto.getFirstName());
		newEmployee.setLastName(employeeDto.getLastName());
		newEmployee.setEmail(employeeDto.getEmail());
		newEmployee.setPassword(bcryptEncoder.encode(employeeDto.getPassword()));
		newEmployee.setRole(EMPLOYEE);

		return this.employeeRepository.save(newEmployee);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee = this.employeeRepository.findByEmail(email);
		if (employee == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}

		return new User(employee.getEmail(), employee.getPassword(), new ArrayList<>());
	}
}