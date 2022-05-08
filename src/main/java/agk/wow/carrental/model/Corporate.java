package agk.wow.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "agk_corporate")
public class Corporate {
    @Id
    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "corporate_name", nullable = false)
    private String corporateName;

    @Column(name = "corporate_discount", nullable = false, precision = 2)
    private Float corporateDiscount;

    @JsonIgnore
    @OneToMany(mappedBy = "corporate", cascade = CascadeType.ALL)
    private Set<Customer> cutomers;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public Float getCorporateDiscount() {
        return corporateDiscount;
    }

    public void setCorporateDiscount(Float corporateDiscount) {
        this.corporateDiscount = corporateDiscount;
    }

    public Set<Customer> getCutomers() {
        return cutomers;
    }

    public void setCutomers(Set<Customer> cutomers) {
        this.cutomers = cutomers;
    }
}