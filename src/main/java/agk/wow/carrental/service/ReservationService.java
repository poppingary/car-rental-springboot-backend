package agk.wow.carrental.service;

import agk.wow.carrental.constant.DailyMileageUnlimited;
import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.constant.VehicleAvailable;
import agk.wow.carrental.model.*;
import agk.wow.carrental.repository.*;
import agk.wow.carrental.rpcdomain.ResponseBody;
import agk.wow.carrental.rpcdomain.request.PaymentRequest;
import agk.wow.carrental.rpcdomain.request.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public ResponseEntity<?> getReservations() {
        Iterable<Reservation> reservations = this.reservationRepository.findAll();

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), reservations), HttpStatus.OK);
    }

    public ResponseEntity<?> getReservation(String customerId) {
        Reservation reservation = this.reservationRepository.findReservationByCustomerId(customerId);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), reservation), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> reserveVehicle(ReservationRequest reservationRequest) {
        String reservationId = UUID.randomUUID().toString();

        Reservation reservation = this.saveReservation(reservationRequest, reservationId);
        this.savePayment(reservationRequest, reservationId, reservation);
        this.updateVehicleAvailable(reservation);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    private Reservation saveReservation(ReservationRequest reservationRequest, String reservationId) {
        Reservation reservation = new Reservation();

        reservation.setReservationId(reservationId);
        this.copyReservationProperties(reservationRequest, reservation);
        this.checkIsUnlimitedAndSetDailyMileageLimit(reservationRequest, reservation);
        this.reservationRepository.save(reservation);

        return reservation;
    }

    private void copyReservationProperties(ReservationRequest reservationRequest, Reservation reservation) {
        Optional<Customer> customerOptional = this.customerRepository.findById(reservationRequest.getCustomerId());
        Optional<Vehicle> vehicleOptional = this.vehicleRepository.findById(reservationRequest.getVehicleId());
        Optional<Location> pickupLocationOptional = this.locationRepository.findById(reservationRequest.getPickupLocationId());
        Optional<Location> dropOffLocationOptional = this.locationRepository.findById(reservationRequest.getDropOffLocationId());
        LocalDateTime pickupDate = LocalDateTime.parse(reservationRequest.getPickupDate(), FORMATTER);
        LocalDateTime dropOffDate = LocalDateTime.parse(reservationRequest.getDropOffDate(), FORMATTER);

        customerOptional.ifPresent(reservation::setCustomer);
        vehicleOptional.ifPresent(reservation::setVehicle);
        pickupLocationOptional.ifPresent(reservation::setPickupLocation);
        dropOffLocationOptional.ifPresent(reservation::setDropOffLocation);
        reservation.setPickupDate(pickupDate);
        reservation.setDropOffDate(dropOffDate);
    }

    private void checkIsUnlimitedAndSetDailyMileageLimit(ReservationRequest reservationRequest, Reservation reservation) {
        if (reservationRequest.getIsUnlimited().equals(DailyMileageUnlimited.YES.getUnlimitation())) {
            reservation.setDailyMileageLimit((float) -1);
        } else {
            reservation.setDailyMileageLimit(Float.valueOf(reservationRequest.getDailyMileageLimit()));
        }
    }

    private void savePayment(ReservationRequest reservationRequest, String reservationId, Reservation reservation) {
        List<PaymentRequest> paymentRequests = reservationRequest.getPayments();
        List<Payment> payments = new ArrayList<>();

        for (PaymentRequest paymentRequest : paymentRequests) {
            Payment payment = new Payment();
            payment.setPaymentId(reservationId);
            payment.setPaymentMethod(paymentRequest.getPaymentMethod());
            payment.setCardNumber(paymentRequest.getCardNumber());
            payment.setAmount(Float.valueOf(paymentRequest.getAmount()));
            payment.setPaymentDate(LocalDateTime.parse(paymentRequest.getPaymentDate(), FORMATTER));
            payment.setReservation(reservation);
            payments.add(payment);
        }

        this.paymentRepository.saveAll(payments);
    }

    private void updateVehicleAvailable(Reservation reservation) {
        String vehicleId = reservation.getVehicle().getVehicleId();

        this.vehicleRepository.updateIsAvailable(vehicleId, VehicleAvailable.NO.getAvailable());
    }
}