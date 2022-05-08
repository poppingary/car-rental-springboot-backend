package agk.wow.carrental.controller;

import agk.wow.carrental.rpcdomain.request.LocationRequest;
import agk.wow.carrental.rpcdomain.request.ReservationRequest;
import agk.wow.carrental.rpcdomain.request.UpdateLocationRequest;
import agk.wow.carrental.service.LocationService;
import agk.wow.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    private LocationService locationService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping(value = "/location")
    public ResponseEntity getLocation(@RequestParam String locationId) {
        return this.locationService.getLocationByLocationId(locationId);
    }

    @GetMapping(value = "/locations")
    public ResponseEntity getLocations() {
        return this.locationService.getLocations();
    }

    @PostMapping(value = "/add/location")
    public ResponseEntity addLocation(@RequestBody LocationRequest locationRequest) {
        return this.locationService.addLocation(locationRequest);
    }

    @PostMapping(value = "/update/location")
    public ResponseEntity updateLocation(@RequestBody UpdateLocationRequest updateLocationRequest) {
        return this.locationService.updateLocation(updateLocationRequest);
    }

    @GetMapping(value = "/types")
    public ResponseEntity getTypes() {
        return this.vehicleService.getTypes();
    }

    @GetMapping(value = "/search")
    public ResponseEntity getVehicleByLocation(@RequestParam String locationId) {
        return this.vehicleService.getVehicleByLocation(locationId);
    }

    @PostMapping(value = "/reserve")
    public ResponseEntity reserveVehicle(@RequestBody ReservationRequest reservationRequest) {
        return this.vehicleService.reserveVehicle(reservationRequest);
    }
}