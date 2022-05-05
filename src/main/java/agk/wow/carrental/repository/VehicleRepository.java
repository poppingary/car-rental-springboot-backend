package agk.wow.carrental.repository;

import agk.wow.carrental.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    Set<Vehicle> findByLocationLocationIdAndIsAvailable(String locationId, String isAvailable);
}