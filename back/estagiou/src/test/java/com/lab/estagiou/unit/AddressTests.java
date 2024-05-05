package com.lab.estagiou.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lab.estagiou.model.address.AddressEntity;

class AddressTests {

    @Test
    @DisplayName("Test create address")
    void testCreateAddress() {
        AddressEntity address = new AddressEntity("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        assertEquals("Brasil", address.getCountry());
        assertEquals("SP", address.getState());
        assertEquals("São Paulo", address.getCity());
        assertEquals("Vila Mariana", address.getNeighborhood());
        assertEquals("Rua Vergueiro", address.getStreet());
        assertEquals("1000", address.getNumber());
        assertEquals("Apto 101", address.getComplement());
    }

    @Test
    @DisplayName("Test create address without complement")
    void testCreateAddressWithoutComplement() {
        AddressEntity address = new AddressEntity("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", null);
        assertEquals("Brasil", address.getCountry());
        assertEquals("SP", address.getState());
        assertEquals("São Paulo", address.getCity());
        assertEquals("Vila Mariana", address.getNeighborhood());
        assertEquals("Rua Vergueiro", address.getStreet());
        assertEquals("1000", address.getNumber());
        assertEquals(null, address.getComplement());
    }

    @Test
    @DisplayName("Test create address without number")
    void testCreateAddressWithoutNumber() {
        try {
            new AddressEntity("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", null, "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Número não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address without street")
    void testCreateAddressWithoutStreet() {
        try {
            new AddressEntity("Brasil", "SP", "São Paulo", "Vila Mariana", null, "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Logradouro não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address without neighborhood")
    void testCreateAddressWithoutNeighborhood() {
        try {
            new AddressEntity("Brasil", "SP", "São Paulo", null, "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Bairro não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address without city")
    void testCreateAddressWithoutCity() {
        try {
            new AddressEntity("Brasil", "SP", null, "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Município não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address without state")
    void testCreateAddressWithoutState() {
        try {
            new AddressEntity("Brasil", null, "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("UF não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address without country")
    void testCreateAddressWithoutCountry() {
        try {
            new AddressEntity(null, "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("País não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty country")
    void testCreateAddressWithEmptyContry() {
        try {
            new AddressEntity("", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("País não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty state")
    void testCreateAddressWithEmptyState() {
        try {
            new AddressEntity("Brasil", "", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("UF não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty city")
    void testCreateAddressWithEmptyCity() {
        try {
            new AddressEntity("Brasil", "SP", "", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Município não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty neighborhood")
    void testCreateAddressWithEmptyNeighborhood() {
        try {
            new AddressEntity("Brasil", "SP", "São Paulo", "", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Bairro não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty street")
    void testCreateAddressWithEmptyStreet() {
        try {
            new AddressEntity("Brasil", "SP", "São Paulo", "Vila Mariana", "", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Logradouro não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty complement")
    void testCreateAddressWithEmptyComplement() {
        try {
            new AddressEntity("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "");
        } catch (IllegalArgumentException e) {
            assertEquals("Complemento não pode ser nulo ou vazio", e.getMessage());
        }
    }

}
