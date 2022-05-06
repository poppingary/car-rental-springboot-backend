package agk.wow.carrental.repository;

import agk.wow.carrental.model.VehicleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends CrudRepository<VehicleType, String> {

}