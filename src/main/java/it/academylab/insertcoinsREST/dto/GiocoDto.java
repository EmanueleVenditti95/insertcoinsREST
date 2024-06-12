package it.academylab.insertcoinsREST.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor @AllArgsConstructor @Data
public class GiocoDto {

    private long id;
    private String nome;
    private String descrizione;
    private String video;
    private String img;

    // private long idCategoria;
    // private CategoriaDto categoria;
}
