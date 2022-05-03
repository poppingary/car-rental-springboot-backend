package agk.wow.carrental.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "agk_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> map;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getMap() {
        return map;
    }

    public void setMap(Set<UserRole> map) {
        this.map = map;
    }
}