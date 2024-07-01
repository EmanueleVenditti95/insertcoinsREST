package it.academylab.insertcoinsREST.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import it.academylab.insertcoinsREST.entities.Utente;

@Repository
@RepositoryRestResource(collectionResourceRel = "utenti", path = "utenti")
public interface UtenteRepository extends JpaRepository<Utente, Long>{
	
	public List<Utente> findAllByOrderByUsername();

	public Utente findByUsername(String username);
}
