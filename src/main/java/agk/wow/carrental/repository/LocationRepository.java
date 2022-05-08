package agk.wow.carrental.repository;

import agk.wow.carrental.model.Location;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, String> {
    @Modifying
    @Query("UPDATE agk_location l " +
            "SET " +
            "l.locationName = :locationName, " +
            "l.phoneNumber = :phoneNumber, " +
            "l.street = :street, " +
            "l.city = :city, " +
            "l.state = :state, " +
            "l.zipcode = :zipcode " +
            "WHERE l.locationId = :locationId")
    void updateLocation(@Param("locationId") String locationId,
                                  @Param("locationName") String locationName,
                                  @Param("phoneNumber") String phoneNumber,
                                  @Param("street") String street,
                                  @Param("city") String city,
                                  @Param("state") String state,
                                  @Param("zipcode") String zipcode);
}