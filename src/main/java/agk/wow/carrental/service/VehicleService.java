package agk.wow.carrental.service;

import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.repository.LocationRepository;
import agk.wow.carrental.rpcdomain.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    private LocationRepository locationRepository;

    public ResponseEntity getLocations() {
        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), this.locationRepository.findAll()), HttpStatus.OK);
    }
}