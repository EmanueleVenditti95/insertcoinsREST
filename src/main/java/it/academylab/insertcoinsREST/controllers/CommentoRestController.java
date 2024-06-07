package it.academylab.insertcoinsREST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.academylab.insertcoinsREST.entities.Commento;
import it.academylab.insertcoinsREST.entities.Gioco;
import it.academylab.insertcoinsREST.entities.Utente;
import it.academylab.insertcoinsREST.services.CommentoService;


@RestController
@RequestMapping("/api/commenti")
public class CommentoRestController {

	@Autowired
	private CommentoService service;
	

		//	/api/commenti/gioco/1/utente/2
		
	@PostMapping("/gioco/{idgioco}/utente/{idutente}")
	public ResponseEntity<Object> aggiungi(
				@PathVariable(value = "idgioco") int idgioco,
				@PathVariable(value = "idutente") int idutente,
				@RequestBody Commento c){
		
		Utente autore = new Utente();
		autore.setId(idutente);
		
		Gioco g = new Gioco();
		g.setId(idgioco);
		
		c.setUtente(autore);
		c.setGioco(g);
		
		
		boolean esito = service.salva(c);
		
	 	if(esito)
	 		return new ResponseEntity<Object>(HttpStatus.CREATED);

	 	return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	 	
	}
	
	
	

	// @GetMapping("/info/gioco/{idgioco}")
	// public Map<String, Object> recupera(@PathVariable(value = "idgioco") long idgioco){

	// 	Map<String, Object> commenti = service.recuperaTutti();
		
	//     return commenti;
		
	// }
	
}
	 

		
