package com.spachecor.micro_contacto.service;

import com.spachecor.micro_contacto.dao.AgendaDao;
import com.spachecor.micro_contacto.model.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaServiceImpl implements AgendaService{

    private final AgendaDao agendaDao;

    @Autowired
    public AgendaServiceImpl(AgendaDao agendaDao) {
        this.agendaDao = agendaDao;
    }

    @Override
    public boolean agregarContacto(Contacto contacto) {
        try{
            this.agendaDao.agregarContacto(contacto);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminarContacto(String email) {
        try{
            this.agendaDao.eliminarContacto(email);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<Contacto> devolverContactos() {
        return this.agendaDao.devolverContactos();
    }

    @Override
    public boolean eliminarContacto(int idContacto) {
        try{
            this.agendaDao.eliminarContacto(idContacto);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Contacto recuperarContacto(String email) {
        return this.agendaDao.recuperarContacto(email);
    }

    @Override
    public boolean actualizarContacto(Contacto contacto) {
        try {
            this.agendaDao.actualizarContacto(contacto);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
