package agk.wow.carrental.service;

import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.model.Location;
import agk.wow.carrental.repository.LocationRepository;
import agk.wow.carrental.rpcdomain.ResponseBody;
import agk.wow.carrental.rpcdomain.request.LocationRequest;
import agk.wow.carrental.rpcdomain.request.UpdateLocationRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public ResponseEntity<?> getLocationByLocationId(String locationId) {
        Optional<Location> locationOptional = this.locationRepository.findById(locationId);
        Location location = null;
        if (locationOptional.isPresent()) {
            location = locationOptional.get();
        }

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), location), HttpStatus.OK);
    }

    public ResponseEntity<?> getLocations() {
        Iterable<Location> locations = this.locationRepository.findAll();

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), locations), HttpStatus.OK);
    }

    public ResponseEntity<?> addLocation(LocationRequest locationRequest) {
        Location newLocation = new Location();
        BeanUtils.copyProperties(locationRequest, newLocation);

        this.locationRepository.save(newLocation);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> updateLocation(UpdateLocationRequest updateLocationRequest) {
        Location location = new Location();
        BeanUtils.copyProperties(updateLocationRequest, location);

        String locationId = updateLocationRequest.getLocationId();
        String locationName = updateLocationRequest.getLocationName();
        String phoneNumber = updateLocationRequest.getPhoneNumber();
        String street = updateLocationRequest.getStreet();
        String city = updateLocationRequest.getCity();
        String state = updateLocationRequest.getState();
        String zipcode = updateLocationRequest.getZipcode();
        this.locationRepository.updateLocation(locationId, locationName, phoneNumber, street, city, state, zipcode);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteLocation(String locationId) {
        this.locationRepository.deleteById(locationId);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }
}