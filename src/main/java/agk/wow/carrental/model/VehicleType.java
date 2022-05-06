package agk.wow.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "agk_vehicle_type")
public class VehicleType {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "vehicle_type_id")
    private String vehicleTypeId;

    @Column(name = "vehicle_type", nullable = false, length = 20)
    private String vehicleType;

    @Column(name = "service_rate", nullable = false, precision = 10, scale = 2)
    private Float serviceRate;

    @Column(name = "excess_mileage_fee", nullable = false, precision = 10, scale = 2)
    private Float excessMileageFee;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicleType", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Float getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(Float serviceRate) {
        this.serviceRate = serviceRate;
    }

    public Float getExcessMileageFee() {
        return excessMileageFee;
    }

    public void setExcessMileageFee(Float excessMileageFee) {
        this.excessMileageFee = excessMileageFee;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}