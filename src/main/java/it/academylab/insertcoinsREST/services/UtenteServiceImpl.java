package it.academylab.insertcoinsREST.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.academylab.insertcoinsREST.entities.Ruolo;
import it.academylab.insertcoinsREST.entities.Utente;
import it.academylab.insertcoinsREST.repositories.RuoloRepository;
import it.academylab.insertcoinsREST.repositories.UtenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private final UtenteRepository utenteRepo;
    private final RuoloRepository ruoloRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Utente save(Utente utente) {
        log.info("Salvando utente {} nel database", utente.getNome());
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepo.save(utente);
    }

    @Override
    public Utente recuperaDaEmail(String email) {
        return utenteRepo.findByEmail(email);
    }

    @Override
    public Utente aggiungiRuoloAdUtente(String email, String nomeRuolo) {
        log.info("Aggiungendo ruolo {} all'utente {}", nomeRuolo, email);
        Utente utente = utenteRepo.findByEmail(email);
        Ruolo ruolo = ruoloRepo.findByNome(nomeRuolo);
        utente.getRuoli().add(ruolo);
        return utente;
    }

    @Override
    public List<Utente> recuperaTutti() {
        return utenteRepo.findAllByOrderByNome();
    }
}
