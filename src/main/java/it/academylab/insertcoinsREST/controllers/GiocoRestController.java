package it.academylab.insertcoinsREST.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.academylab.insertcoinsREST.dto.PreferitoDto;
import it.academylab.insertcoinsREST.entities.Gioco;
import it.academylab.insertcoinsREST.services.CommentoService;
import it.academylab.insertcoinsREST.services.GiocoService;
import it.academylab.insertcoinsREST.services.UtenteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/giochi")
public class GiocoRestController {

    @Autowired
    private GiocoService service;

    @Autowired
    private CommentoService commService;

    @Autowired
    private UtenteService utenteService;

    // LISTA ---------------------------------------------------
    @GetMapping("")
    public Map<String, Object> recuperaLista() {
        return service.recuperaTuttiOrdByNome();
    }

    @GetMapping("/cerca")
    public Map<String, Object> recuperaListaDaNome(@RequestParam("nomeGioco") String nomeGioco) {
        return service.recuperaTuttiDaNome(nomeGioco);
    }

    @GetMapping("/sort/{idCategoria}")
    public Map<String, Object> recuperaListaFiltrataPerCategoria(@PathVariable(value = "idCategoria") int idCategoria) {
        return service.recuperaTuttiDaCategoriaId(idCategoria);
    }

    @GetMapping("/console/{idConsole}")
    public Map<String, Object> recuperaListaFiltrataPerConsole(@PathVariable(value = "idConsole") int idConsole) {
        return service.recuperaTuttiDaConsoleId(idConsole);
    }

    // SINGOLO GIOCO ----------------------------------------------
    @GetMapping("/{idgioco}")
    public Map<String, Object> recuperaGioco(@PathVariable(value = "idgioco") int idgioco) {
        return service.recuperaGioco(idgioco);
    }

    // INSERIMENTO ---------------------------------------------
    @PostMapping("/inserimento")
    public ResponseEntity<Object> inserimento(@RequestBody Gioco g) {
        Long giocoId = service.salva(g);

        if (giocoId != null) {
            // return new ResponseEntity<Object>(HttpStatus.CREATED);
            return ResponseEntity.ok().body(giocoId);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/aggiungiPreferito")
    public ResponseEntity<?> aggiungiPreferito(@RequestBody PreferitoDto p) {
        if (utenteService.aggiungiPreferito(p.getUtenteId(), p.getGiocoId()) != null)
            return ResponseEntity.ok().body(null);
        else
            return ResponseEntity.badRequest().body("Errore nel salvataggio del preferito");
    }

    // AGGIORNAMENTO ---------------------------------------------
    @PutMapping("/aggiornamento")
    public ResponseEntity<Object> aggiornamento(@RequestBody Gioco g) {

        if (service.salva(g) != null) {
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    // ELIMINAZIONE ---------------------------------------------
    @DeleteMapping("/{idgioco}")
    public ResponseEntity<Void> eliminaGioco(@PathVariable(value = "idgioco") int idgioco) {
        boolean isRemoved = service.elimina(idgioco);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // RECUPERO COMMENTI
    @GetMapping("/{idgioco}/commenti")
    public Map<String, Object> recupera(@PathVariable(value = "idgioco") long idgioco) {

        Map<String, Object> commenti = commService.recuperaTuttiDaGiocoId(idgioco);

        return commenti;

    }
}
