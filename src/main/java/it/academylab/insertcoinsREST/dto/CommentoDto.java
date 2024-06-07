package it.academylab.insertcoinsREST.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class CommentoDto {
	
	private long id;
	private String testo;
	
	@JsonFormat(pattern="dd.MM.yyyy HH:mm", timezone="Europe/Rome")
	private Date data;

	private int voto;
	private String autore;

	private long giocoId;

}
