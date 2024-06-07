package it.academylab.insertcoinsREST.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.academylab.insertcoinsREST.entities.Utente;
import it.academylab.insertcoinsREST.repositories.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository repo;

    @Override
    public Utente recuperaDaEmail(String email) {
        return repo.findByEmail(email);
    }

}
