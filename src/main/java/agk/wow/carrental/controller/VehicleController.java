package agk.wow.carrental.controller;

import agk.wow.carrental.rpcdomain.request.UpdateVehicleTypeRequest;
import agk.wow.carrental.rpcdomain.request.VehicleRequest;
import agk.wow.carrental.rpcdomain.request.VehicleTypeRequest;
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

    @GetMapping(value = "/get/vehicles")
    public ResponseEntity<?> getVehicles() {
        return this.vehicleService.getVehicles();
    }

    @GetMapping(value = "/get/vehicle/by/locationId")
    public ResponseEntity<?> getVehicleByLocation(@RequestParam String locationId) {
        return this.vehicleService.getVehicleByLocation(locationId);
    }

    @GetMapping(value = "/get/vehicle/by/vehicleId")
    public ResponseEntity<?> getVehicleByVehicleId(@RequestParam String vehicleId) {
        return this.vehicleService.getVehicleByVehicleId(vehicleId);
    }

    @PostMapping(value = "/add/vehicle")
    public ResponseEntity<?> addVehicle(@RequestBody VehicleRequest vehicleRequest) {
        return this.vehicleService.addVehicle(vehicleRequest);
    }

    @PostMapping(value = "/update/vehicle")
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleRequest vehicleRequest) {
        return this.vehicleService.updateVehicle(vehicleRequest);
    }

    @DeleteMapping(value = "/delete/vehicle")
    public ResponseEntity<?> deleteVehicle(@RequestParam String vehicleId) {
        return this.vehicleService.deleteVehicle(vehicleId);
    }

    @GetMapping(value = "/get/types")
    public ResponseEntity<?> getTypes() {
        return this.vehicleService.getTypes();
    }

    @GetMapping(value = "/get/type/by/vehicleTypeId")
    public ResponseEntity<?> getTypeByVehicleTypeId(@RequestParam String vehicleTypeId) {
        return this.vehicleService.getTypeByVehicleTypeId(vehicleTypeId);
    }

    @PostMapping(value = "/add/type")
    public ResponseEntity<?> addType(@RequestBody VehicleTypeRequest vehicleTypeRequest) {
        return this.vehicleService.addType(vehicleTypeRequest);
    }

    @PostMapping(value = "/update/type")
    public ResponseEntity<?> updateType(@RequestBody UpdateVehicleTypeRequest updateVehicleTypeRequest) {
        return this.vehicleService.updateType(updateVehicleTypeRequest);
    }

    @DeleteMapping(value = "/delete/type")
    public ResponseEntity<?> deleteType(@RequestParam String vehicleTypeId) {
        return this.vehicleService.deleteType(vehicleTypeId);
    }
}