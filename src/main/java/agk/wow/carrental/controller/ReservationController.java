package agk.wow.carrental.controller;

import agk.wow.carrental.rpcdomain.request.ReservationRequest;
import agk.wow.carrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping(value = "/get/reservations")
    public ResponseEntity<?> getReservations() {
        return this.reservationService.getReservations();
    }

    @GetMapping(value = "/get/reservation")
    public ResponseEntity<?> getReservation(@RequestParam String customerId) {
        return this.reservationService.getReservation(customerId);
    }

    @PostMapping(value = "/reserve/vehicle")
    public ResponseEntity<?> reserveVehicle(@RequestBody ReservationRequest reservationRequest) {
        return this.reservationService.reserveVehicle(reservationRequest);
    }
}