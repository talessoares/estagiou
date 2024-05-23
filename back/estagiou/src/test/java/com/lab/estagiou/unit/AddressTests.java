package com.lab.estagiou.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lab.estagiou.dto.request.model.util.RequestAddress;
import com.lab.estagiou.model.address.AddressEntity;

class AddressTests {

    @Test
    @DisplayName("Test create address")
    void testCreateAddress() {
        RequestAddress request = new RequestAddress("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        AddressEntity address = new AddressEntity(request);
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
        RequestAddress request = new RequestAddress("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", null);
        AddressEntity address = new AddressEntity(request);
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
            RequestAddress request = new RequestAddress("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", null, "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("Número não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address without street")
    void testCreateAddressWithoutStreet() {
        try {
            RequestAddress request = new RequestAddress("Brasil", "SP", "São Paulo", "Vila Mariana", null, "1000", "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("Rua não pode ser nula", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address without neighborhood")
    void testCreateAddressWithoutNeighborhood() {
        try {
            RequestAddress request = new RequestAddress("Brasil", "SP", "São Paulo", null, "Rua Vergueiro", "1000", "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("Bairro não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address without city")
    void testCreateAddressWithoutCity() {
        try {
            RequestAddress request = new RequestAddress("Brasil", "SP", null, "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("Cidade não pode ser nula", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address without state")
    void testCreateAddressWithoutState() {
        try {
            RequestAddress request = new RequestAddress("Brasil", null, "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("Estado não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address without country")
    void testCreateAddressWithoutCountry() {
        try {
            RequestAddress request = new RequestAddress(null, "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("País não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty country")
    void testCreateAddressWithEmptyContry() {
        try {
            RequestAddress request = new RequestAddress("", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("País não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty state")
    void testCreateAddressWithEmptyState() {
        try {
            RequestAddress request = new RequestAddress("Brasil", "", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("Estado não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty city")
    void testCreateAddressWithEmptyCity() {
        try {
            RequestAddress request = new RequestAddress("Brasil", "SP", "", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("Cidade não pode ser nula", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty neighborhood")
    void testCreateAddressWithEmptyNeighborhood() {
        try {
            RequestAddress request = new RequestAddress("Brasil", "SP", "São Paulo", "", "Rua Vergueiro", "1000", "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("Bairro não pode ser nulo", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty street")
    void testCreateAddressWithEmptyStreet() {
        try {
            RequestAddress request = new RequestAddress("Brasil", "SP", "São Paulo", "Vila Mariana", "", "1000", "Apto 101");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("Rua não pode ser nula", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test create address with empty complement")
    void testCreateAddressWithEmptyComplement() {
        try {
            RequestAddress request = new RequestAddress("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "");
            new AddressEntity(request);
        } catch (IllegalArgumentException e) {
            assertEquals("Complemento não pode ser nulo", e.getMessage());
        }
    }

}
