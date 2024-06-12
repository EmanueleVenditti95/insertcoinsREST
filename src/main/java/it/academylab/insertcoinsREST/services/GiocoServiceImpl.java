package it.academylab.insertcoinsREST.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.academylab.insertcoinsREST.dto.CategoriaDto;
import it.academylab.insertcoinsREST.dto.GiocoDto;
import it.academylab.insertcoinsREST.entities.Categoria;
import it.academylab.insertcoinsREST.entities.Gioco;
import it.academylab.insertcoinsREST.repositories.GiocoRepository;

@Service
public class GiocoServiceImpl implements GiocoService{

    @Autowired
    private GiocoRepository repo;

    public Map<String, Object> recuperaTuttiDaNome() {

        List<Gioco> giochi = repo.findAllByOrderByNomeAsc();

        List<GiocoDto> dto = new ArrayList<GiocoDto>();

        for(Gioco g : giochi) {
            Categoria categoria = g.getCategoria();
            long categoriaId = 0;
            String categoriaNome = "";
            if (categoria != null) {
                categoriaId = categoria.getId();
                categoriaNome = categoria.getNome();
                }
            CategoriaDto categoriaDto = new CategoriaDto(categoriaId,categoriaNome);
                
            dto.add(new GiocoDto(
                g.getId(),
                g.getNome(),
                g.getDescrizione(),
                g.getVideo(),
                g.getImg(),
                categoriaDto
            ));
       }

        Map<String, Object> giochiMap = new HashMap<>();
        giochiMap.put("giochi", dto);

		Map<String, Object> mappa = new HashMap<>();
		mappa.put("_embedded", giochiMap);

        return mappa;
    }

    public Map<String, Object> recuperaGioco(long id) {
        Gioco g = repo.findById(id);
        Categoria categoria = g.getCategoria();
        long categoriaId = 0;
        String categoriaNome = "";
            if (categoria != null) {
                categoriaId = categoria.getId();
                categoriaNome = categoria.getNome();
                }
        CategoriaDto categoriaDto = new CategoriaDto(categoriaId,categoriaNome);
        GiocoDto dto = new GiocoDto(
        g.getId(),
        g.getNome(),
        g.getDescrizione(),
        g.getVideo(),
        g.getImg(),
        categoriaDto);

        Map<String, Object> gMap = new HashMap<>();
        gMap.put("gioco", dto);
        return gMap;
    }

    @Override
    public Long salva(Gioco g) {

        try {
            Gioco giocoSalvato = repo.save(g);
            return giocoSalvato.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
