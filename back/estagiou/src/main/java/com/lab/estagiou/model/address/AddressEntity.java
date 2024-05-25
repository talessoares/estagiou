package com.lab.estagiou.model.address;

import java.io.Serializable;
import java.util.UUID;

import com.lab.estagiou.dto.request.model.util.RequestAddress;
import com.lab.estagiou.exception.generic.RegisterException;
import com.lab.estagiou.exception.generic.UpdateException;

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

    public AddressEntity(RequestAddress address) {
        if (address == null) {
            throw new RegisterException("Endereço não pode ser nulo");
        }

        if (address.getCountry() == null || address.getCountry().isBlank()) {
            throw new RegisterException("País não pode ser nulo");
        }

        if (address.getState() == null || address.getState().isBlank()) {
            throw new RegisterException("Estado não pode ser nulo");
        }

        if (address.getCity() == null || address.getCity().isBlank()) {
            throw new RegisterException("Cidade não pode ser nula");
        }

        if (address.getNeighborhood() == null || address.getNeighborhood().isBlank()) {
            throw new RegisterException("Bairro não pode ser nulo");
        }

        if (address.getStreet() == null || address.getStreet().isBlank()) {
            throw new RegisterException("Rua não pode ser nula");
        }

        if (address.getNumber() == null || address.getNumber().isBlank()) {
            throw new RegisterException("Número não pode ser nulo");
        }

        this.id = null;
        this.country = address.getCountry();
        this.state = address.getState();
        this.city = address.getCity();
        this.neighborhood = address.getNeighborhood();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.complement = address.getComplement();
    }

    public void update(RequestAddress address) {
        if (address == null) {
            throw new UpdateException("Endereço não pode ser nulo");
        }

        this.country = validateAndAssign(country, address.getCountry(), "País não pode ser nulo");
        this.state = validateAndAssign(state, address.getState(), "Estado não pode ser nulo");
        this.city = validateAndAssign(city, address.getCity(), "Cidade não pode ser nula");
        this.neighborhood = validateAndAssign(neighborhood, address.getNeighborhood(), "Bairro não pode ser nulo");
        this.street = validateAndAssign(street, address.getStreet(), "Rua não pode ser nula");
        this.number = validateAndAssign(number, address.getNumber(), "Número não pode ser nulo");

        if (address.getComplement() != null) {
            this.complement = address.getComplement();
        }
    }

    private String validateAndAssign(String originalValue, String value, String errorMessage) {
        if (value == null) {
            return originalValue;
        }

        if (value.isBlank()) {
            throw new UpdateException(errorMessage);
        }

        return value;
    }
    
}
