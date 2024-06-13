package it.academylab.insertcoinsREST.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class GiocoDto {

    private long id;
    private String nome;
    private String descrizione;
    private String video;
    private String img;

    private CategoriaDto categoria;
}
