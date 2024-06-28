package it.academylab.insertcoinsREST.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.academylab.insertcoinsREST.services.ConsoleService;

@RestController
@RequestMapping("/api/consoles")
public class ConsoleRestController {

    @Autowired
    private ConsoleService service;

    // LISTA ---------------------------------------------------
    @GetMapping("")
    public Map<String, Object> recuperaLista() {       
        return this.service.recuperaTuttiOrdByNome();
    }
}
