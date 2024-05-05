package com.lab.estagiou.model.address;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
    
}
