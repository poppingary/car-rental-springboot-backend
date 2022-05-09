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
import java.util.Optional;

@Service
public class ReservationService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    public ResponseEntity<?> reserveVehicle(ReservationRequest reservationRequest) {
        Optional<Customer> customerOptional = this.customerRepository.findById(reservationRequest.getCustomerId());
        Optional<Vehicle> vehicleOptional = this.vehicleRepository.findById(reservationRequest.getVehicleId());
        Optional<Location> pickupLocationOptional = this.locationRepository.findById(reservationRequest.getPickupLocationId());
        Optional<Location> dropOffLocationOptional = this.locationRepository.findById(reservationRequest.getDropOffLocationId());
        LocalDateTime pickupDate = LocalDateTime.parse(reservationRequest.getPickupDate(), FORMATTER);
        LocalDateTime dropOffDate = LocalDateTime.parse(reservationRequest.getPickupDate(), FORMATTER);

        Reservation reservation = new Reservation();
        customerOptional.ifPresent(reservation::setCustomer);
        vehicleOptional.ifPresent(reservation::setVehicle);
        pickupLocationOptional.ifPresent(reservation::setPickupLocation);
        dropOffLocationOptional.ifPresent(reservation::setDropOffLocation);
        reservation.setPickupDate(pickupDate);
        reservation.setDropOffDate(dropOffDate);

        this.reservationRepository.save(reservation);
        this.vehicleRepository.updateIsAvailable(vehicleOptional.get().getVehicleId());

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }
}