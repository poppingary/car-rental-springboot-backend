package agk.wow.carrental.repository;

import agk.wow.carrental.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    Employee findByEmail(String email);
}