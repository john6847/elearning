package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 100)
    private String name;
    @NotNull
    @Size(min = 4, max = 30)
    @Column(unique=true)
    private String username;
    private  Boolean habilitado= true;
    @Column(name="enabled", nullable = false, columnDefinition = "int default 1")
    private int enable = 1;
    @NotNull(message="La contrasena es invalida")
    @Size(max=60)
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rol> roles;

    public Usuario() {
    }

    public Usuario(String name, String username, Boolean habilitado, int enable, String password, List<Rol> roles) {
        this.name = name;
        this.username = username;
        this.habilitado = habilitado;
        this.enable = enable;
        this.password = password;
        this.roles = roles;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
