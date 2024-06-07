package it.academylab.insertcoinsREST.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import it.academylab.insertcoinsREST.entities.Categoria;
import it.academylab.insertcoinsREST.entities.Commento;
import it.academylab.insertcoinsREST.entities.Gioco;
import it.academylab.insertcoinsREST.entities.Utente;

@Configuration
public class RestConfig implements RepositoryRestConfigurer{
	
	@SuppressWarnings("rawtypes")
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		cors.addMapping("/**")
			.allowedOrigins("*") // Permette richieste da qualsiasi origine
			.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"); // Permette tutti i metodi HTTP
		
		Class[] classiEntity = {Gioco.class, Categoria.class, Utente.class, Commento.class};
		
		for(Class c : classiEntity) {
			config.exposeIdsFor(c);
		}
	}
}
