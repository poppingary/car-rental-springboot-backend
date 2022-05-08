package agk.wow.carrental.service;

import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.model.*;
import agk.wow.carrental.model.VehicleType;
import agk.wow.carrental.repository.*;
import agk.wow.carrental.rpcdomain.ResponseBody;
import agk.wow.carrental.rpcdomain.request.ReservationRequest;
import agk.wow.carrental.rpcdomain.request.VehicleRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class VehicleService {
    private static final String IS_AVAILABLE = "Y";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

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

    public ResponseEntity getVehicleByLocation(String locationId) {
        Set<Vehicle> vehicles = this.vehicleRepository.findByLocationLocationIdAndIsAvailable(locationId, IS_AVAILABLE);

        ResponseEntity responseEntity = new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), vehicles), HttpStatus.OK);

        return responseEntity;
    }

    @Transactional
    public ResponseEntity reserveVehicle(ReservationRequest reservationRequest) {
        Customer customer = this.customerRepository.findById(reservationRequest.getCustomerId()).get();
        Vehicle vehicle = this.vehicleRepository.findById(reservationRequest.getVehicleId()).get();
        Location pickupLocation = this.locationRepository.findById(reservationRequest.getPickupLocationId()).get();
        Location dropOffLocation = this.locationRepository.findById(reservationRequest.getDropOffLocationId()).get();
        LocalDateTime pickupDate = LocalDateTime.parse(reservationRequest.getPickupDate(), formatter);
        LocalDateTime dropOffDate = LocalDateTime.parse(reservationRequest.getPickupDate(), formatter);

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setVehicle(vehicle);
        reservation.setPickupLocation(pickupLocation);
        reservation.setDropOffLocation(dropOffLocation);
        reservation.setPickupDate(pickupDate);
        reservation.setDropOffDate(dropOffDate);

        this.reservationRepository.save(reservation);
        this.vehicleRepository.updateIsAvailable(vehicle.getVehicleId());

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }
}