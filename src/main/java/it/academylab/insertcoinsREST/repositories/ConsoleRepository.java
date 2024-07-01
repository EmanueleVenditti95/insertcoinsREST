package it.academylab.insertcoinsREST.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import it.academylab.insertcoinsREST.entities.Console;


@Repository
@RepositoryRestResource(collectionResourceRel = "consoles", path = "consoles")
public interface ConsoleRepository extends JpaRepository<Console, Long>{

    public List<Console> findAllByOrderByNomeAsc();
    public Console findById(long id);
}
