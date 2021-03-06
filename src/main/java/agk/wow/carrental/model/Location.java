package agk.wow.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "agk_location")
public class Location implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "location_id")
    private String locationId;

    @Column(name = "location_name", unique = true, nullable = false, length = 70)
    private String locationName;

    @Column(name = "phone_number", unique = true, nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 30)
    private String street;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 30)
    private String state;

    @Column(nullable = false, length = 10)
    private String zipcode;

    @JsonIgnore
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    @JsonIgnore
    @OneToMany(mappedBy = "pickupLocation", cascade = CascadeType.ALL)
    private Set<Reservation> reservationPick;

    @JsonIgnore
    @OneToMany(mappedBy = "dropOffLocation", cascade = CascadeType.ALL)
    private Set<Reservation> reservationDrop;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<Reservation> getReservationPick() {
        return reservationPick;
    }

    public void setReservationPick(Set<Reservation> reservationPick) {
        this.reservationPick = reservationPick;
    }

    public Set<Reservation> getReservationDrop() {
        return reservationDrop;
    }

    public void setReservationDrop(Set<Reservation> reservationDrop) {
        this.reservationDrop = reservationDrop;
    }
}