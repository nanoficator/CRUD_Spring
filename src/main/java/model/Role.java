package model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "roles")
public class Role {
    @Id
    @Column (name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "roleName")
    private String roleName;

    @Column (name = "users")
    @ManyToMany
    @JoinTable (name = "users", joinColumns = @JoinColumn (name = "role_id"), inverseJoinColumns = @JoinColumn (name = "user_id"))
    private Set<User> users;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}