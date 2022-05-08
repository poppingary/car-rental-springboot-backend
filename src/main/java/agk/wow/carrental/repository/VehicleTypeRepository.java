package agk.wow.carrental.repository;

import agk.wow.carrental.model.VehicleType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends CrudRepository<VehicleType, String> {
    @Modifying
    @Query("UPDATE agk_vehicle_type vt " +
            "SET " +
            "vt.excessMileageFee = :excessMileageFee, " +
            "vt.serviceRate = :serviceRate, " +
            "vt.vehicleType = :vehicleType " +
            "WHERE vt.vehicleTypeId = :vehicleTypeId")
    void updateVehicleType(@Param("vehicleTypeId") String vehicleTypeId,
                       @Param("excessMileageFee") Float excessMileageFee,
                       @Param("serviceRate") Float serviceRate,
                       @Param("vehicleType") String vehicleType);
}