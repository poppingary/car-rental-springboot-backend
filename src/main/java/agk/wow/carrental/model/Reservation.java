package agk.wow.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "agk_reservation")
public class Reservation {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "reservation_id")
    private String reservationId;

    @Column(name = "pickup_date", nullable = false)
    private LocalDateTime pickupDate;

    @Column(name = "drop_off_date", nullable = false)
    private LocalDateTime dropOffDate;

    @Column(name = "start_odometer", precision = 10, scale = 2)
    private Float startOdometer;

    @Column(name = "end_odometer", precision = 10, scale = 2)
    private Float endOdometer;

    @Column(name = "daily_mileage_limit", precision = 10, scale = 2)
    private Float dailyMileageLimit;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_location", nullable = false)
    private Location pickupLocation;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drop_off_location", nullable = false)
    private Location dropOffLocation;

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDateTime getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDateTime pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDateTime getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDateTime dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Float getStartOdometer() {
        return startOdometer;
    }

    public void setStartOdometer(Float startOdometer) {
        this.startOdometer = startOdometer;
    }

    public Float getEndOdometer() {
        return endOdometer;
    }

    public void setEndOdometer(Float endOdometer) {
        this.endOdometer = endOdometer;
    }

    public Float getDailyMileageLimit() {
        return dailyMileageLimit;
    }

    public void setDailyMileageLimit(Float dailyMileageLimit) {
        this.dailyMileageLimit = dailyMileageLimit;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }
}