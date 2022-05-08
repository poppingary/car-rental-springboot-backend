package agk.wow.carrental.controller;

import agk.wow.carrental.rpcdomain.request.LocationRequest;
import agk.wow.carrental.rpcdomain.request.UpdateLocationRequest;
import agk.wow.carrental.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("branch")
public class BranchController {
    @Autowired
    private LocationService locationService;

    @GetMapping(value = "/get/locations")
    public ResponseEntity<?> getLocations() {
        return this.locationService.getLocations();
    }

    @GetMapping(value = "/get/location")
    public ResponseEntity<?> getLocation(@RequestParam String locationId) {
        return this.locationService.getLocationByLocationId(locationId);
    }

    @PostMapping(value = "/add/location")
    public ResponseEntity<?> addLocation(@RequestBody LocationRequest locationRequest) {
        return this.locationService.addLocation(locationRequest);
    }

    @PostMapping(value = "/update/location")
    public ResponseEntity<?> updateLocation(@RequestBody UpdateLocationRequest updateLocationRequest) {
        return this.locationService.updateLocation(updateLocationRequest);
    }

    @DeleteMapping(value = "/delete/location")
    public ResponseEntity<?> deleteLocation(@RequestParam String locationId) {
        return this.locationService.deleteLocation(locationId);
    }
}