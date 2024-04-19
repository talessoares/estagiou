package com.lab.estagiou.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lab.estagiou.model.entity.Endereco;

class EnderecoTests {

    @Test
    @DisplayName("Teste de criação de endereço")
    void testCreateEndereco() {
        Endereco endereco = new Endereco("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        assertEquals("Brasil", endereco.getPais());
        assertEquals("SP", endereco.getUf());
        assertEquals("São Paulo", endereco.getMunicipio());
        assertEquals("Vila Mariana", endereco.getBairro());
        assertEquals("Rua Vergueiro", endereco.getLogradouro());
        assertEquals("1000", endereco.getNumero());
        assertEquals("Apto 101", endereco.getComplemento());
    }

    @Test
    @DisplayName("Teste de criação de endereço sem complemento")
    void testCreateEnderecoWithoutComplemento() {
        Endereco endereco = new Endereco("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", null);
        assertEquals("Brasil", endereco.getPais());
        assertEquals("SP", endereco.getUf());
        assertEquals("São Paulo", endereco.getMunicipio());
        assertEquals("Vila Mariana", endereco.getBairro());
        assertEquals("Rua Vergueiro", endereco.getLogradouro());
        assertEquals("1000", endereco.getNumero());
        assertEquals(null, endereco.getComplemento());
    }

    @Test
    @DisplayName("Teste de criação de endereço sem número")
    void testCreateEnderecoWithoutNumero() {
        try {
            new Endereco("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", null, "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Número não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço sem logradouro")
    void testCreateEnderecoWithoutLogradouro() {
        try {
            new Endereco("Brasil", "SP", "São Paulo", "Vila Mariana", null, "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Logradouro não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço sem bairro")
    void testCreateEnderecoWithoutBairro() {
        try {
            new Endereco("Brasil", "SP", "São Paulo", null, "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Bairro não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço sem município")
    void testCreateEnderecoWithoutMunicipio() {
        try {
            new Endereco("Brasil", "SP", null, "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Município não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço sem UF")
    void testCreateEnderecoWithoutUf() {
        try {
            new Endereco("Brasil", null, "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("UF não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço sem país")
    void testCreateEnderecoWithoutPais() {
        try {
            new Endereco(null, "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("País não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço com país vazio")
    void testCreateEnderecoWithEmptyPais() {
        try {
            new Endereco("", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("País não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço com UF vazio")
    void testCreateEnderecoWithEmptyUf() {
        try {
            new Endereco("Brasil", "", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("UF não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço com município vazio")
    void testCreateEnderecoWithEmptyMunicipio() {
        try {
            new Endereco("Brasil", "SP", "", "Vila Mariana", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Município não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço com bairro vazio")
    void testCreateEnderecoWithEmptyBairro() {
        try {
            new Endereco("Brasil", "SP", "São Paulo", "", "Rua Vergueiro", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Bairro não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço com logradouro vazio")
    void testCreateEnderecoWithEmptyLogradouro() {
        try {
            new Endereco("Brasil", "SP", "São Paulo", "Vila Mariana", "", "1000", "Apto 101");
        } catch (IllegalArgumentException e) {
            assertEquals("Logradouro não pode ser nulo ou vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste de criação de endereço com complemento vazio")
    void testCreateEnderecoWithEmptyComplemento() {
        try {
            new Endereco("Brasil", "SP", "São Paulo", "Vila Mariana", "Rua Vergueiro", "1000", "");
        } catch (IllegalArgumentException e) {
            assertEquals("Complemento não pode ser nulo ou vazio", e.getMessage());
        }
    }

}
