package it.academylab.insertcoinsREST.services;

import java.util.Map;

import it.academylab.insertcoinsREST.entities.Gioco;

public interface GiocoService {
    
    public Map<String, Object> recuperaTuttiDaNome();
    public Long salva(Gioco g);

}
