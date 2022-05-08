package agk.wow.carrental.repository;

import agk.wow.carrental.model.Location;
import agk.wow.carrental.model.Vehicle;
import agk.wow.carrental.model.VehicleType;
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

    @Modifying
    @Query("UPDATE agk_vehicle v " +
            "SET " +
            "v.vehicleId = :vehicleId, " +
            "v.brand = :brand, " +
            "v.licensePlate = :licensePlate, " +
            "v.model = :model, " +
            "v.year = :year, " +
            "v.location = :location, " +
            "v.vehicleType = :vehicleType " +
            "WHERE v.vehicleId = :vehicleId")
    void updateVehicle(@Param("vehicleId") String vehicleId,
                        @Param("brand") String brand,
                        @Param("licensePlate") String licensePlate,
                        @Param("model") String model,
                        @Param("year") String year,
                        @Param("location") Location location,
                        @Param("vehicleType") VehicleType vehicleType);
}