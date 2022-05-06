package agk.wow.carrental.repository;

import agk.wow.carrental.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    Employee findByEmail(String email);

    @Modifying
    @Query("UPDATE agk_employee e " +
            "SET " +
            "e.firstName = :firstName, " +
            "e.middleName = :middleName, " +
            "e.lastName = :lastName " +
            "WHERE e.id = :employeeId")
    void updateEmployee(@Param("employeeId") String employeeId,
                        @Param("firstName") String firstName,
                        @Param("middleName") String middleName,
                        @Param("lastName") String lastName);

    @Modifying
    @Query("UPDATE agk_employee e " +
            "SET " +
            "e.password = :password " +
            "WHERE e.id = :employeeId")
    void updateCredential(@Param("employeeId") String employeeId,
                          @Param("password") String password);
}