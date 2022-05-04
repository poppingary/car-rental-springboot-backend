package agk.wow.carrental.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "agk_corporate")
public class Corporate {
    @Id
    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "corporate_name")
    private String corporateName;

    @Column(name = "corporate_discount", precision = 2)
    private Float corporateDiscount;

    @OneToMany(mappedBy = "corporate")
    private Set<Customer> cutomers;
}