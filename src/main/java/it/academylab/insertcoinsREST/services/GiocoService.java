package it.academylab.insertcoinsREST.services;

import java.util.Map;

import it.academylab.insertcoinsREST.entities.Gioco;

public interface GiocoService {
    
    public Map<String, Object> recuperaTuttiOrdByNome();
    public Map<String, Object> recuperaTuttiDaNome(String nome);
    public Map<String, Object> recuperaTuttiDaCategoriaId(long id);
    public Map<String, Object> recuperaTuttiDaConsoleId(long id);
    public Map<String, Object> recuperaGioco(long id);
    public Long salva(Gioco g);
    public boolean elimina(long id);
    public Map<String, Object> recuperaCasuali();
}
