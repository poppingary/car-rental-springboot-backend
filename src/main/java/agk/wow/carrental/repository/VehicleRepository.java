package agk.wow.carrental.repository;

import agk.wow.carrental.model.Vehicle;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, String> {
    Set<Vehicle> findByLocationLocationIdAndIsAvailable(String locationId, String isAvailable);

    @Modifying
    @Query("UPDATE agk_vehicle v SET v.isAvailable = \'N\' WHERE v.vehicleId = :vehicleId")
    void updateIsAvailable(@Param("vehicleId") String vehicleId);
}