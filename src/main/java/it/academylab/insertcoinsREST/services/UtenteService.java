package it.academylab.insertcoinsREST.services;

import java.util.List;

import it.academylab.insertcoinsREST.entities.Utente;

public interface UtenteService {

    public List<Utente> recuperaTutti();
    public Utente recuperaDaEmail(String email);
    public Utente save(Utente utente);
    public Utente aggiungiRuoloAdUtente(String email, String nomeRuolo);
}

