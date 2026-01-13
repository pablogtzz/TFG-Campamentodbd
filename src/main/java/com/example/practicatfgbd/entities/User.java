package com.example.practicatfgbd.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {

    //private static final long serialVersionUID = 289560162104381506L;
    @Id
    @GeneratedValue
    long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String username;

    private String password;

    @Column(nullable = false)
    private String telefono;

    private String email;

    @Enumerated(EnumType.STRING)
    private Rol role;

    @ManyToOne
    @JoinColumn(name = "ID_Grupo")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "ID_Subgrupo")
    private SubGrupo subgrupo;

    @ManyToMany
    @JoinTable(
            name = "Monitores_Alergias",
            joinColumns = @JoinColumn(name = "ID_Monitores"),
            inverseJoinColumns = @JoinColumn(name = "ID_Alergias")
    )
    private List<Alergias> alergias;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
