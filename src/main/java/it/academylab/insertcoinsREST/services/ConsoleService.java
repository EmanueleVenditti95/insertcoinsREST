package it.academylab.insertcoinsREST.services;

import java.util.Map;

public interface ConsoleService {

    public Map<String, Object> recuperaTuttiOrdByNome();
    public Map<String, Object> recuperaConsoleDaId(long id);
}
