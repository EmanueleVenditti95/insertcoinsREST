package it.academylab.insertcoinsREST.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import it.academylab.insertcoinsREST.entities.Categoria;

@Repository
@RepositoryRestResource(collectionResourceRel = "categorie", path = "categorie")
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	public List<Categoria> findAllByOrderByNome();
	public Categoria findById(long id);
}
