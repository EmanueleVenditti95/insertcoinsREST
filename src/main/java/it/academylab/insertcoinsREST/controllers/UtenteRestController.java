package it.academylab.insertcoinsREST.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import it.academylab.insertcoinsREST.dto.RuoloDto;
import it.academylab.insertcoinsREST.entities.Utente;
import it.academylab.insertcoinsREST.services.UtenteService;

@RestController
@RequestMapping("/api/utenti")
public class UtenteRestController {

    @Autowired
    private UtenteService service;

   @GetMapping
   public ResponseEntity<List<Utente>> recuperaTutti() {
      return ResponseEntity.ok().body(service.recuperaTutti());
   }

   @GetMapping("/{email}")
   public ResponseEntity<Utente> recuperaDaEmail(@PathVariable String email) {
      return ResponseEntity.ok().body(service.recuperaDaEmail(email));
   }

   @PostMapping
   public ResponseEntity<Utente> save(@RequestBody Utente utente) {
    Utente userEntity = service.save(utente);
      URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}")
              .buildAndExpand(userEntity.getEmail()).toUriString());
      return ResponseEntity.created(uri).build();
   }


   @PostMapping("/{email}/aggiungiRuoloAdUtente")
   public ResponseEntity<?> aggiungiRuoloAdUtente(@PathVariable String email, @RequestBody RuoloDto request) {
    Utente userEntity = service.aggiungiRuoloAdUtente(email, request.getNome());
      return ResponseEntity.ok(userEntity);
   }
}

