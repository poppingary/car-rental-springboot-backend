package agk.wow.carrental.controller;

import agk.wow.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping(value = "/locations")
    public ResponseEntity getLocations() {
        return ResponseEntity.ok(this.vehicleService.getLocations());
    }
}