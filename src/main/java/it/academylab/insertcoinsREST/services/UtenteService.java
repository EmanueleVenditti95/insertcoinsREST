package it.academylab.insertcoinsREST.services;

import java.util.List;

import it.academylab.insertcoinsREST.entities.Utente;

public interface UtenteService {

    public List<Utente> recuperaTutti();
    public Utente recuperaDaUsername(String username);
    public Utente save(Utente utente);
    public Utente aggiungiRuoloAdUtente(String email, String nomeRuolo);
    public Utente rimuoviRuoloAdUtente(String email, String nomeRuolo);
    public Utente aggiungiPreferito(long utenteId, long giocoId);
    public boolean eliminaPreferito(long utenteId, long giocoId);
}

