package com.lab.estagiou.model.address;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "address")
@Table(name = "tb_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String country;

    private String state;

    private String city;

    private String neighborhood;

    private String street;

    private String number;

    private String complement;

    public AddressEntity(String country, String state, String city, String neighborhood, String street, String number,
            String complement) {

        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("País não pode ser nulo ou vazio");
        }

        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("UF não pode ser nulo ou vazio");
        }

        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Município não pode ser nulo ou vazio");
        }

        if (neighborhood == null || neighborhood.isBlank()) {
            throw new IllegalArgumentException("Bairro não pode ser nulo ou vazio");
        }

        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Logradouro não pode ser nulo ou vazio");
        }

        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Número não pode ser nulo ou vazio");
        }

        this.id = null;
        this.country = country;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.complement = complement;
    }
    
}
