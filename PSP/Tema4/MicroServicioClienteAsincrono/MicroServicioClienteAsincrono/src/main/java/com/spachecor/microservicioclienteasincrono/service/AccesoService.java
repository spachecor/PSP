package com.spachecor.microservicioclienteasincrono.service;

import com.spachecor.microservicioclienteasincrono.model.Persona;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AccesoService {
    CompletableFuture<List<Persona>> llamadaServicio(Persona persona);
}
