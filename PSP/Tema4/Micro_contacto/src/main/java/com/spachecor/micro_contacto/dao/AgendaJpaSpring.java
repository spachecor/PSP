package com.spachecor.micro_contacto.dao;

import com.spachecor.micro_contacto.model.Contacto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AgendaJpaSpring extends JpaRepository<Contacto, Integer> {
    Contacto findByEmail(String email);
    @Transactional
    @Modifying
    @Query("Delete from Contacto c Where c.email=?1")
    void eliminarPorEmail(String email);
}
