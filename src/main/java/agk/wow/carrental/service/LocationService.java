package agk.wow.carrental.service;

import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.model.Location;
import agk.wow.carrental.repository.LocationRepository;
import agk.wow.carrental.rpcdomain.ResponseBody;
import agk.wow.carrental.rpcdomain.request.AddLocationRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public ResponseEntity getLocationByLocationId(String locationId) {
        Location location = this.locationRepository.findById(locationId).get();

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), location), HttpStatus.OK);
    }

    public ResponseEntity getLocations() {
        Iterable<Location> locations = this.locationRepository.findAll();

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), locations), HttpStatus.OK);
    }

    public ResponseEntity addLocation(AddLocationRequest addLocationRequest) {
        Location newLocation = new Location();
        BeanUtils.copyProperties(addLocationRequest, newLocation);

        this.locationRepository.save(newLocation);

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }
}