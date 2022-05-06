package agk.wow.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "agk_employee")
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "employee_id")
    private String id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "middle_name", length = 20)
    private String middleName = "";

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @Column(nullable = false, length = 10)
    private String role;

    public Employee() {

    }

    public Employee(String id, String firstName, String middleName, String lastName, String email, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id +
                ", first name='" + this.firstName +
                "', last name='" + this.lastName +
                "', email='" + this.email + '\'' +
                ", role='" + this.role + '\'' +
                '}';
    }
}