package it.academylab.insertcoinsREST.services;

import org.springframework.stereotype.Service;

import it.academylab.insertcoinsREST.entities.Ruolo;
import it.academylab.insertcoinsREST.repositories.RuoloRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RuoloServiceImpl implements RuoloService {

    private final RuoloRepository ruoloRepository;

    @Override
    public Ruolo save(Ruolo ruolo) {
        log.info("Saving role {} to the database", ruolo.getNome());
        return ruoloRepository.save(ruolo);
    }
}
