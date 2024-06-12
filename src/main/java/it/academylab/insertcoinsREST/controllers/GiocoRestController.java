package it.academylab.insertcoinsREST.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.academylab.insertcoinsREST.entities.Gioco;
import it.academylab.insertcoinsREST.services.CommentoService;
import it.academylab.insertcoinsREST.services.GiocoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/giochi")
public class GiocoRestController {

    @Autowired
    private GiocoService service;

    @Autowired
    private CommentoService commService;

    // LISTA ---------------------------------------------------
    @GetMapping("")
    public Map<String, Object> lista() {       
        return service.recuperaTuttiDaNome();
    }

    // INSERIMENTO ---------------------------------------------
    @PostMapping("/inserimento")
    public ResponseEntity<Object> inserimento(@RequestBody Gioco g) {
        Long giocoId = service.salva(g);

        if(giocoId != null) {
            // return new ResponseEntity<Object>(HttpStatus.CREATED);
            return ResponseEntity.ok().body(giocoId);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // AGGIORNAMENTO ---------------------------------------------
    @PutMapping("/aggiornamento")
    public ResponseEntity<Object> aggiornamento(@RequestBody Gioco g) {

        if(service.salva(g) != null) {
             return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    // RECUPERO COMMENTI
    @GetMapping("/{idgioco}/commenti")
	public Map<String, Object> recupera(@PathVariable(value = "idgioco") long idgioco){

		Map<String, Object> commenti = commService.recuperaTuttiDaGiocoId(idgioco);
		
	    return commenti;
		
	}
}
