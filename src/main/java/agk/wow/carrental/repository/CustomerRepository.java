package agk.wow.carrental.repository;

import agk.wow.carrental.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    Customer findByEmail(String email);

    @Modifying
    @Query("UPDATE agk_customer c " +
            "SET " +
            "c.firstName = :firstName, " +
            "c.middleName = :middleName, " +
            "c.lastName = :lastName, " +
            "c.phoneNumber = :phoneNumber, " +
            "c.street = :street, " +
            "c.city = :city, " +
            "c.state = :state, " +
            "c.zipcode = :zipcode, " +
            "c.insuranceCompany = :insuranceCompany, " +
            "c.insuranceNumber = :insuranceNumber, " +
            "c.driverLicense = :driverLicense " +
            "WHERE c.id = :customerId")
    void updateIndividualCustomer(@Param("customerId") String customerId,
                           @Param("firstName") String firstName,
                           @Param("middleName") String middleName,
                           @Param("lastName") String lastName,
                           @Param("phoneNumber") String phoneNumber,
                           @Param("street") String street,
                           @Param("city") String city,
                           @Param("state") String state,
                           @Param("zipcode") String zipcode,
                           @Param("insuranceCompany") String insuranceCompany,
                           @Param("insuranceNumber") String insuranceNumber,
                           @Param("driverLicense") String driverLicense);

    @Modifying
    @Query("UPDATE agk_customer c " +
            "SET " +
            "c.password = :password " +
            "WHERE c.id = :customerId")
    void updateCredential(@Param("customerId") String customerId,
                          @Param("password") String password);
}