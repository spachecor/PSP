package com.spachecor.micro_contacto.controller;

import com.spachecor.micro_contacto.model.Contacto;
import com.spachecor.micro_contacto.service.AgendaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgendaController {
    private final AgendaServiceImpl agendaServiceImpl;

    @Autowired
    public AgendaController(AgendaServiceImpl agendaServiceImpl) {
        this.agendaServiceImpl = agendaServiceImpl;
    }

    // Metodo para recuperar todos los contactos
    @GetMapping(value="contactos", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contacto>> recuperarContactos() {
        // Llamada al servicio para obtener la lista de contactos
        List<Contacto> contactos = agendaServiceImpl.devolverContactos();

        // Creación de un objeto HttpHeaders para añadir encabezados personalizados
        HttpHeaders headers = new HttpHeaders();

        // Añadir un encabezado con el número total de contactos
        headers.add("total", String.valueOf(contactos.size()));
        System.out.println(headers.get("total"));

        try{
            System.out.println("Esperando...");
            Thread.sleep(5000);
            System.out.println("Finalizado.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        // Devolver una respuesta HTTP con la lista de contactos, los encabezados y un estado HTTP 200 (OK)
        return new ResponseEntity<>(contactos, headers, HttpStatus.OK);
    }
    /*@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Contacto> getContactos() {
        return this.agendaServiceImpl.devolverContactos();
    }*/

    @PostMapping(value = "contactos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postAgenda(@RequestBody Contacto contacto) {
        this.agendaServiceImpl.agregarContacto(contacto);
    }

    @PutMapping(value = "contactos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putAgenda(@RequestBody Contacto contacto) {
        if(contacto.getId()!=null)this.agendaServiceImpl.actualizarContacto(contacto);
        else{
            List<Contacto> contactos = this.agendaServiceImpl.devolverContactos();
            for(Contacto contactoTemp : contactos){
                if(contactoTemp.equals(contacto))this.agendaServiceImpl.actualizarContacto(contacto);
            }
        }
    }

    @DeleteMapping(value = "contactos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAgenda(@RequestBody Contacto contacto) {
        this.agendaServiceImpl.eliminarContacto(contacto.getEmail());
    }
}
