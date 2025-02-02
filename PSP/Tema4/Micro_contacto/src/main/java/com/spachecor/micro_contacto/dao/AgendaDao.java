package com.spachecor.micro_contacto.dao;

import com.spachecor.micro_contacto.model.Contacto;

import java.util.List;

public interface AgendaDao {
    void agregarContacto(Contacto contacto);

    Contacto recuperarContacto(String email);

    void eliminarContacto(String email);

    List<Contacto> devolverContactos();

    void eliminarContacto(int idContacto);

    void actualizarContacto(Contacto contacto);
}
