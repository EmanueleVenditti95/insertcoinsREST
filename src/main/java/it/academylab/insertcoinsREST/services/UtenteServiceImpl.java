package it.academylab.insertcoinsREST.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UtenteServiceImpl implements UtenteService,UserDetailsService {

    @Autowired
    private final UtenteRepository utenteRepo;
    private final RuoloRepository ruoloRepo;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_NOT_FOUND_MESSAGE = "User with username %s not found";

    @Override
    public Utente save(Utente utente) {
        log.info("Salvando utente {} nel database", utente.getUsername());
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepo.save(utente);
    }

    @Override
    public Utente recuperaDaUsername(String username) {
        return utenteRepo.findByUsername(username);
    }

    @Override
    public Utente aggiungiRuoloAdUtente(String username, String nomeRuolo) {
        log.info("Aggiungendo ruolo {} all'utente {}", nomeRuolo, username);
        Utente utente = utenteRepo.findByUsername(username);
        Ruolo ruolo = ruoloRepo.findByNome(nomeRuolo);
        utente.getRuoli().add(ruolo);
        return utente;
    }

    @Override
    public Utente rimuoviRuoloAdUtente(String username, String nomeRuolo) {
        log.info("Rimuovendo ruolo {} all'utente {}", nomeRuolo, username);
        Utente utente = utenteRepo.findByUsername(username);
        Ruolo ruolo = ruoloRepo.findByNome(nomeRuolo);
        utente.getRuoli().remove(ruolo);
        return utente;
    }


    @Override
    public List<Utente> recuperaTutti() {
        return utenteRepo.findAllByOrderByUsername();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepo.findByUsername(username);
        if(utente == null) {
            String message = String.format(USER_NOT_FOUND_MESSAGE, username);
            log.error(message);
            throw new UsernameNotFoundException(message);
        } else {
            log.debug("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            utente.getRuoli().forEach(ruolo -> {
                authorities.add(new SimpleGrantedAuthority(ruolo.getNome()));
            });
            return new User(utente.getEmail(), utente.getPassword(), authorities);
        }
    }
}
