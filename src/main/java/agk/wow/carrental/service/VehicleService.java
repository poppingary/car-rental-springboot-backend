package agk.wow.carrental.service;

import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.model.Customer;
import agk.wow.carrental.model.Location;
import agk.wow.carrental.model.Reservation;
import agk.wow.carrental.model.Vehicle;
import agk.wow.carrental.repository.CustomerRepository;
import agk.wow.carrental.repository.LocationRepository;
import agk.wow.carrental.repository.ReservationRepository;
import agk.wow.carrental.repository.VehicleRepository;
import agk.wow.carrental.rpcdomain.ResponseBody;
import agk.wow.carrental.rpcdomain.request.ReservationRequest;
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
    private LocationRepository locationRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public ResponseEntity getLocationByLocationId(String locationId) {
        Location location = this.locationRepository.findById(locationId).get();

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), location), HttpStatus.OK);
    }

    public ResponseEntity getLocations() {
        Iterable<Location> locations = this.locationRepository.findAll();

        return new ResponseEntity(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), locations), HttpStatus.OK);
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