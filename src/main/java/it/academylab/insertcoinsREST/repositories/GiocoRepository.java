package it.academylab.insertcoinsREST.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import it.academylab.insertcoinsREST.entities.Gioco;

@Repository
@RepositoryRestResource(collectionResourceRel = "giochi", path = "giochi")
public interface GiocoRepository extends JpaRepository<Gioco, Long>{
	
	public List<Gioco> findAllByOrderByNome();
}
