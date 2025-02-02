package com.spachecor.micro_contacto.dao;


import com.spachecor.micro_contacto.model.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgendaDaoImpl implements AgendaDao {

    private final AgendaJpaSpring agenda;

    @Autowired
    public AgendaDaoImpl(AgendaJpaSpring agenda) {
        this.agenda = agenda;
    }

    @Override
    public void agregarContacto(Contacto contacto) {
        agenda.save(contacto);
    }

    @Override
    public void eliminarContacto(String email) {
        agenda.eliminarPorEmail(email);
    }

    @Override
    public List<Contacto> devolverContactos() {
        return agenda.findAll();
    }

    @Override
    public void eliminarContacto(int idContacto) {
        agenda.deleteById(idContacto);
    }

    @Override
    public Contacto recuperarContacto(String email) {
        return agenda.findByEmail(email);
    }

    @Override
    public void actualizarContacto(Contacto contacto) {
        agenda.save(contacto);
    }
}
