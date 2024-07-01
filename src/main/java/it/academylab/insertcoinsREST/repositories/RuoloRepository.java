package it.academylab.insertcoinsREST.repositories;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import it.academylab.insertcoinsREST.entities.Ruolo;

@Repository
@RepositoryRestResource(collectionResourceRel = "ruoli", path = "ruoli")
public interface RuoloRepository extends JpaRepository<Ruolo, Long>{

    public List<Ruolo> findAllByOrderByNome();
    public Ruolo findByNome(String nome);
}
