package agk.wow.carrental.service;

import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.model.Vehicle;
import agk.wow.carrental.repository.LocationRepository;
import agk.wow.carrental.repository.VehicleRepository;
import agk.wow.carrental.rpcdomain.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VehicleService {
    private static final String IS_AVAILABLE = "Y";

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public ResponseEntity getLocations() {
        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), this.locationRepository.findAll()), HttpStatus.OK);
    }

    public ResponseEntity getVehicleByLocation(String locationId) {
        Set<Vehicle> vehicle = this.vehicleRepository.findByLocationLocationIdAndIsAvailable(locationId, IS_AVAILABLE);

        ResponseEntity responseEntity = new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), vehicle), HttpStatus.OK);

        return responseEntity;
    }
}