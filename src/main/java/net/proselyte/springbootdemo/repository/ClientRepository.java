package net.proselyte.springbootdemo.repository;

import net.proselyte.springbootdemo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, Long> {
}
