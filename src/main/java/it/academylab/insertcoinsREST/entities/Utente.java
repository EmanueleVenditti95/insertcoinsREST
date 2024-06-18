package it.academylab.insertcoinsREST.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.FetchType;

@Entity
@Table(name="utente")
@AllArgsConstructor @NoArgsConstructor @Data
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="foto")
	private String foto;
	
	@ManyToMany(fetch = FetchType.EAGER)
    private Collection<Ruolo> ruoli = new ArrayList<>();
	
	@OneToMany(mappedBy = "utente")
	@ToString.Exclude
	private List<Commento> commenti;
	
}
