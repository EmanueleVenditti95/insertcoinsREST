package it.academylab.insertcoinsREST.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="commento")
@AllArgsConstructor @NoArgsConstructor @Data
public class Commento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	@Column(name="testo")
	private String testo;
	
	@Column(name="data")
	@JsonFormat(pattern="dd.MM.yyyy HH:mm", timezone="Europe/Rome")
	private Date data;
	
	@Column(name="voto")
	private int voto;

	@ManyToOne
	@JoinColumn(name="id_gioco")
	@ToString.Exclude
	private Gioco gioco;
	
	@ManyToOne
	@JoinColumn(name="id_utente")
	@ToString.Exclude
	private Utente utente;
}
