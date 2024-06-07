package it.academylab.insertcoinsREST.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.academylab.insertcoinsREST.dto.CommentoDto;
import it.academylab.insertcoinsREST.entities.Commento;
import it.academylab.insertcoinsREST.repositories.CommentoRepository;



@Service
public class CommentoServiceImpl implements CommentoService{
	
	@Autowired
	private CommentoRepository repo;

	@Override
	public boolean salva(Commento c) {

		boolean esito = true;
		
		try{
			repo.save(c);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			esito = false;
		}

		System.err.println(esito);
		
		
		return esito;
	}


	@Override
	public Map<String, Object> recuperaTuttiDaGiocoId(long id) {
		
		List<Commento> commenti = repo.findAllByGiocoId(id);
		
		List<CommentoDto> dto = new ArrayList<CommentoDto>();
		
		for(Commento c : commenti)
			dto.add(new CommentoDto(c.getId(), c.getTesto(), c.getData(), c.getVoto(), c.getUtente().getNome(),c.getGioco().getId()));

		Map<String, Object> commentiMap = new HashMap<>();
		commentiMap.put("commenti", dto);
		
		Map<String, Object> mappa = new HashMap<>();
		mappa.put("_embedded", commentiMap);
		
		return mappa;
	}
	
	

}
