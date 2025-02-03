package com.spachecor.micro_contacto.controller;

import com.spachecor.micro_contacto.model.Contacto;
import com.spachecor.micro_contacto.service.AgendaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgendaController {
    private final AgendaServiceImpl agendaServiceImpl;

    @Autowired
    public AgendaController(AgendaServiceImpl agendaServiceImpl) {
        this.agendaServiceImpl = agendaServiceImpl;
    }

    @GetMapping(value = "contacto", produces = MediaType.APPLICATION_JSON_VALUE)
    public Contacto getAgenda(@RequestParam String email) {
        return this.agendaServiceImpl.recuperarContacto(email);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Contacto> getContactos() {
        return this.agendaServiceImpl.devolverContactos();
    }

    @PostMapping(value = "contacto", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postAgenda(@RequestBody Contacto contacto) {
        this.agendaServiceImpl.agregarContacto(contacto);
    }

    @PutMapping(value = "contacto", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putAgenda(@RequestBody Contacto contacto) {
        if(contacto.getId()!=null)this.agendaServiceImpl.actualizarContacto(contacto);
        else{
            List<Contacto> contactos = this.agendaServiceImpl.devolverContactos();
            for(Contacto contactoTemp : contactos){
                if(contactoTemp.equals(contacto))this.agendaServiceImpl.actualizarContacto(contacto);
            }
        }
    }

    @DeleteMapping(value = "contacto", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAgenda(@RequestBody Contacto contacto) {
        this.agendaServiceImpl.eliminarContacto(contacto.getEmail());
    }
}
