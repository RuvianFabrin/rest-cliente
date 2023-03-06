package com.ru.restcliente.dto;

import com.ru.restcliente.entities.Cliente;

import java.io.Serializable;
import java.util.Objects;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public ClienteDTO(){

    }

    public ClienteDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClienteDTO(Cliente entity){
        this.id= entity.getId();
        this.name = entity.getName();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteDTO that = (ClienteDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
