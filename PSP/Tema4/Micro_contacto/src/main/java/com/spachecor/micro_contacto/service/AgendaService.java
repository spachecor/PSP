package com.spachecor.micro_contacto.service;

import com.spachecor.micro_contacto.model.Contacto;

import java.util.List;

public interface AgendaService {
    boolean agregarContacto(Contacto contacto);
    boolean eliminarContacto(String email);
    List<Contacto> devolverContactos();
    boolean eliminarContacto(int idContacto);
    Contacto recuperarContacto(String email);
    boolean actualizarContacto(Contacto contacto);

}
