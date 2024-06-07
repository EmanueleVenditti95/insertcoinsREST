package it.academylab.insertcoinsREST.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.academylab.insertcoinsREST.entities.Utente;
import it.academylab.insertcoinsREST.services.UtenteService;

@RestController
@RequestMapping("/api/utenti")
public class UtenteRestController {

    @Autowired
    private UtenteService service;

    @PostMapping("/autenticazione")
    public ResponseEntity<Object> autentica(@RequestBody String email) {

        Utente utente = service.recuperaDaEmail(email);

        if(utente != null) {
            return new ResponseEntity<Object>(utente,HttpStatus.OK);
        }

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
