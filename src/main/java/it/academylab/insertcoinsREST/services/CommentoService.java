package it.academylab.insertcoinsREST.services;

import java.util.Map;

import it.academylab.insertcoinsREST.entities.Commento;

public interface CommentoService {

	public boolean salva(Commento c);

	public Map<String, Object> recuperaTuttiDaGiocoId(long id);	
	
}
