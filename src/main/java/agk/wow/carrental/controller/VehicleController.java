package agk.wow.carrental.controller;

import agk.wow.carrental.rpcdomain.request.ReservationRequest;
import agk.wow.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping(value = "/location")
    public ResponseEntity getLocation(@RequestParam String locationId) {
        return this.vehicleService.getLocationByLocationId(locationId);
    }

    @GetMapping(value = "/locations")
    public ResponseEntity getLocations() {
        return this.vehicleService.getLocations();
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