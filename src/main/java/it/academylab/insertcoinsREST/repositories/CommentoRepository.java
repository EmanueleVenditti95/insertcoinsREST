package it.academylab.insertcoinsREST.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import it.academylab.insertcoinsREST.entities.Commento;


@Repository
@RepositoryRestResource(collectionResourceRel = "commmenti", path = "commenti")
public interface CommentoRepository extends JpaRepository<Commento, Long>{
	
	public List<Commento> findAllByGiocoId(long id);
}
