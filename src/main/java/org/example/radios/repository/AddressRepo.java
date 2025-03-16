package org.example.radios.repository;

import org.example.radios.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepo extends JpaRepository<Address , UUID> {
}
