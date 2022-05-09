package agk.wow.carrental.service;

import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.model.Location;
import agk.wow.carrental.model.Vehicle;
import agk.wow.carrental.model.VehicleType;
import agk.wow.carrental.repository.*;
import agk.wow.carrental.rpcdomain.ResponseBody;
import agk.wow.carrental.rpcdomain.request.UpdateVehicleTypeRequest;
import agk.wow.carrental.rpcdomain.request.VehicleRequest;
import agk.wow.carrental.rpcdomain.request.VehicleTypeRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class VehicleService {
    private static final String IS_AVAILABLE = "Y";

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LocationRepository locationRepository;

    public ResponseEntity getVehicles() {
        Iterable<Vehicle> vehicles = this.vehicleRepository.findAll();

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), vehicles), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity addVehicle(VehicleRequest vehicleRequest) {
        Vehicle newVehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleRequest, newVehicle);
        VehicleType vehicleType = this.vehicleTypeRepository.findById(vehicleRequest.getVehicleTypeId()).get();
        Location location = this.locationRepository.findById(vehicleRequest.getLocationId()).get();

        newVehicle.setIsAvailable(IS_AVAILABLE);
        newVehicle.setVehicleType(vehicleType);
        newVehicle.setLocation(location);

        this.vehicleRepository.save(newVehicle);

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity updateVehicle(VehicleRequest vehicleRequest) {
        String vehicleId = vehicleRequest.getVehicleId();
        String brand = vehicleRequest.getBrand();
        String licensePlate = vehicleRequest.getLicensePlate();
        String model = vehicleRequest.getModel();
        String year = vehicleRequest.getYear();
        VehicleType vehicleType = this.vehicleTypeRepository.findById(vehicleRequest.getVehicleTypeId()).get();
        Location location = this.locationRepository.findById(vehicleRequest.getLocationId()).get();

        this.vehicleRepository.updateVehicle(vehicleId, brand, licensePlate, model, year, location, vehicleType);

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteVehicle(String vehicleId) {
        this.vehicleRepository.deleteById(vehicleId);

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    public ResponseEntity getTypes() {
        Iterable<VehicleType> types = this.vehicleTypeRepository.findAll();

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), types), HttpStatus.OK);
    }

    public ResponseEntity getTypeByVehicleTypeId(String vehicleTypeId) {
        VehicleType vehicleType = this.vehicleTypeRepository.findById(vehicleTypeId).get();

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), vehicleType), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity addType(VehicleTypeRequest vehicleTypeRequest) {
        VehicleType newVehicleType = new VehicleType();
        newVehicleType.setExcessMileageFee(Float.valueOf(vehicleTypeRequest.getExcessMileageFee()));
        newVehicleType.setServiceRate(Float.valueOf(vehicleTypeRequest.getServiceRate()));
        newVehicleType.setVehicleType(vehicleTypeRequest.getVehicleType());

        this.vehicleTypeRepository.save(newVehicleType);

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity updateType(UpdateVehicleTypeRequest updateVehicleTypeRequest) {
        String vehicleTypeId = updateVehicleTypeRequest.getVehicleTypeId();
        Float excessMileageFee = Float.valueOf(updateVehicleTypeRequest.getExcessMileageFee());
        Float serviceRate = Float.valueOf(updateVehicleTypeRequest.getServiceRate());
        String vehicleType = updateVehicleTypeRequest.getVehicleType();

        this.vehicleTypeRepository.updateVehicleType(vehicleTypeId, excessMileageFee, serviceRate, vehicleType);

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity deleteType(String vehicleTypeId) {
        this.vehicleTypeRepository.deleteById(vehicleTypeId);

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    public ResponseEntity getVehicleByLocation(String locationId) {
        Set<Vehicle> vehicles = this.vehicleRepository.findByLocationLocationIdAndIsAvailable(locationId, IS_AVAILABLE);

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), vehicles), HttpStatus.OK);
    }

    public ResponseEntity getVehicleByVehicleId(String vehicleId) {
        Vehicle vehicle = this.vehicleRepository.findById(vehicleId).get();

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), vehicle), HttpStatus.OK);
    }
}