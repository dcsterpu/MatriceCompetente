package com.expleo.webcm.entity.securitydb;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "webcm_security")
public class LoginRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_roles")
    private int id;

    @Column(name = "user_roles")
    private String roles;

//    @ManyToMany(mappedBy = "role")
        @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
        name = "authorities",
        joinColumns = {@JoinColumn(name = "authority")},
        inverseJoinColumns = {@JoinColumn(name = "username")}
)
    private Set<LoginUser> users;

    public LoginRoles() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Set<LoginUser> getUsers() {
        return users;
    }

    public void setUsers(Set<LoginUser> users) {
        this.users = users;
    }

    public void addUsers(LoginUser user){
        if(users == null){
            users = new HashSet<>();
        }

        users.add(user);
    }

    @Override
    public String toString() {
        return "LoginRoles{" +
                "id=" + id +
                ", roles='" + roles + '\'' +
                "}\n";
    }
}
