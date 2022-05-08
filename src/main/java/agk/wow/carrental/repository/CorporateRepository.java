package agk.wow.carrental.repository;

import agk.wow.carrental.model.Corporate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateRepository extends CrudRepository<Corporate, String> {
    @Modifying
    @Query("UPDATE agk_corporate c " +
            "SET " +
            "c.registrationNumber = :registrationNumber, " +
            "c.corporateDiscount = :corporateDiscount, " +
            "c.corporateName = :corporateName " +
            "WHERE c.registrationNumber = :registrationNumber")
    void updateCorporate(@Param("registrationNumber") String registrationNumber,
                         @Param("corporateDiscount") Float corporateDiscount,
                         @Param("corporateName") String corporateName);
}