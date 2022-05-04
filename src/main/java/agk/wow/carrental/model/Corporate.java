package agk.wow.carrental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "agk_corporate")
public class Corporate {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "corporate_name")
    private String corporateName;

    @Column(name = "corporate_discount", precision = 2)
    private Float corporateDiscount;

    @OneToMany(mappedBy = "corporate")
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