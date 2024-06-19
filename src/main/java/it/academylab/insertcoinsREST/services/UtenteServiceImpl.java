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
    public Utente rimuoviRuoloAdUtente(String email, String nomeRuolo) {
        log.info("Rimuovendo ruolo {} all'utente {}", nomeRuolo, email);
        Utente utente = utenteRepo.findByEmail(email);
        Ruolo ruolo = ruoloRepo.findByNome(nomeRuolo);
        utente.getRuoli().remove(ruolo);
        return utente;
    }


    @Override
    public List<Utente> recuperaTutti() {
        return utenteRepo.findAllByOrderByNome();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente utente = utenteRepo.findByEmail(email);
        if(utente == null) {
            String message = String.format(USER_NOT_FOUND_MESSAGE, email);
            log.error(message);
            throw new UsernameNotFoundException(message);
        } else {
            log.debug("User found in the database: {}", email);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            utente.getRuoli().forEach(ruolo -> {
                authorities.add(new SimpleGrantedAuthority(ruolo.getNome()));
            });
            return new User(utente.getEmail(), utente.getPassword(), authorities);
        }
    }
}
