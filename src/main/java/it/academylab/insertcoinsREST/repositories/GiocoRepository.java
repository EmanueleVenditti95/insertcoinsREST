package it.academylab.insertcoinsREST.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import it.academylab.insertcoinsREST.entities.Gioco;

@Repository
@RepositoryRestResource(collectionResourceRel = "giochi", path = "giochi")
public interface GiocoRepository extends JpaRepository<Gioco, Long>{
	
	public List<Gioco> findAllByOrderByNomeAsc();
	public List<Gioco> findAllByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
	public Gioco findById(long id);
	public List<Gioco> findAllByCategoriaIdOrderByNomeAsc(long id);
	public List<Gioco> findAllByConsolesIdOrderByNomeAsc(long id);
}
