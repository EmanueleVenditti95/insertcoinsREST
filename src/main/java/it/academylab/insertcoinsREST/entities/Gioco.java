package it.academylab.insertcoinsREST.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="gioco")
@AllArgsConstructor @NoArgsConstructor @Data
public class Gioco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="descrizione")
	private String descrizione;
	
	@Column(name="video")
	private String video;

	@Column(name="img")
	private String img;
	
	@ManyToOne
	@JoinColumn(name="id_categoria")
	@ToString.Exclude
	private Categoria categoria;
	
	@OneToMany(mappedBy="gioco")
	@ToString.Exclude
	private List<Commento> commenti;
	
	@ManyToMany(fetch = FetchType.EAGER)
    private Collection<Console> consoles = new ArrayList<>();
}