package it.academylab.insertcoinsREST.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.academylab.insertcoinsREST.entities.Gioco;
import it.academylab.insertcoinsREST.repositories.GiocoRepository;

@Service
public class GiocoServiceImpl implements GiocoService{

    @Autowired
    private GiocoRepository repo;

    @Override
    public Long salva(Gioco g) {

        try {
            Gioco giocoSalvato = repo.save(g);
            return giocoSalvato.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
