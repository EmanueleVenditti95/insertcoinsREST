package it.academylab.insertcoinsREST;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// import it.academylab.insertcoinsREST.entities.Commento;
// import it.academylab.insertcoinsREST.entities.Ruolo;
// import it.academylab.insertcoinsREST.entities.Utente;
import it.academylab.insertcoinsREST.services.RuoloService;
import it.academylab.insertcoinsREST.services.UtenteService;

@SpringBootApplication
public class InsertcoinsRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsertcoinsRestApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	 @Bean
	 CommandLineRunner run(UtenteService utenteService, RuoloService ruoloService) {
	 	return args -> {
	 		// ruoloService.save(new Ruolo(null, "ROLE_USER"));
	 		// ruoloService.save(new Ruolo(null, "ROLE_ADMIN"));

	 		// utenteService.save(new Utente(1, "rossi","rossi@gmail.com", "1234","", new ArrayList<Ruolo>(),new ArrayList<Commento>()));
	 		// utenteService.save(new Utente(2, "bianchi","bianchi@gmail.com", "1234","", new ArrayList<Ruolo>(),new ArrayList<Commento>()));

	 		// utenteService.aggiungiRuoloAdUtente("rossi@gmail.com", "ROLE_USER");
	 		// utenteService.aggiungiRuoloAdUtente("bianchi@gmail.com", "ROLE_ADMIN");
	 		// utenteService.aggiungiRuoloAdUtente("bianchi@gmail.com", "ROLE_USER");
	 	};
	 }
}
