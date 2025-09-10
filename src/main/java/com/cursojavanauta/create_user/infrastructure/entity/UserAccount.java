package com.cursojavanauta.create_user.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    private List<Phone> phones = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return this.password;
    }
}
