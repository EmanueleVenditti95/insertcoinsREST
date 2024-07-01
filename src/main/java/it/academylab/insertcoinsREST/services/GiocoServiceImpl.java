package it.academylab.insertcoinsREST.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.academylab.insertcoinsREST.dto.CategoriaDto;
import it.academylab.insertcoinsREST.dto.ConsoleDto;
import it.academylab.insertcoinsREST.dto.GiocoDto;
import it.academylab.insertcoinsREST.entities.Console;
import it.academylab.insertcoinsREST.entities.Gioco;
// import it.academylab.insertcoinsREST.repositories.CategoriaRepository;
import it.academylab.insertcoinsREST.repositories.GiocoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GiocoServiceImpl implements GiocoService {

    @Autowired
    private GiocoRepository repo;
    // private CategoriaRepository repoCat;

    public Map<String, Object> recuperaTuttiOrdByNome() {

        List<Gioco> giochi = repo.findAllByOrderByNomeAsc();

        List<GiocoDto> dto = new ArrayList<GiocoDto>();

        for (Gioco g : giochi) {

            CategoriaDto categoriaDto = new CategoriaDto(
                    g.getCategoria().getId(),
                    g.getCategoria().getNome());

            List<Console> consoles = g.getConsoles();
            List<ConsoleDto> consolesDto = new ArrayList<ConsoleDto>();
            for (Console c : consoles) {
                ConsoleDto consoleDto = new ConsoleDto(c.getId(), c.getNome(), c.getIcona());
                consolesDto.add(consoleDto);
            }

            dto.add(new GiocoDto(
                    g.getId(),
                    g.getNome(),
                    g.getDescrizione(),
                    g.getVideo(),
                    g.getImg(),
                    categoriaDto,
                    consolesDto));
        }

        Map<String, Object> giochiMap = new HashMap<>();
        giochiMap.put("giochi", dto);

        return giochiMap;
    }

    public Map<String, Object> recuperaTuttiDaNome(String nome) {

        List<Gioco> giochi = repo.findAllByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
        List<GiocoDto> dto = new ArrayList<GiocoDto>();

        for (Gioco g : giochi) {

            CategoriaDto categoriaDto = new CategoriaDto(
                    g.getCategoria().getId(),
                    g.getCategoria().getNome());

            List<Console> consoles = g.getConsoles();
            List<ConsoleDto> consolesDto = new ArrayList<ConsoleDto>();
            for (Console c : consoles) {
                ConsoleDto consoleDto = new ConsoleDto(c.getId(), c.getNome(), c.getIcona());
                consolesDto.add(consoleDto);
            }

            dto.add(new GiocoDto(
                    g.getId(),
                    g.getNome(),
                    g.getDescrizione(),
                    g.getVideo(),
                    g.getImg(),
                    categoriaDto,
                    consolesDto));
        }

        Map<String, Object> giochiMap = new HashMap<>();
        giochiMap.put("giochi", dto);

        return giochiMap;
    }

    public Map<String, Object> recuperaGioco(long id) {
        Gioco g = repo.findById(id);

        long categoriaId = 0;
        String categoriaNome = "";
        if (g.getCategoria() != null) {
            categoriaId = g.getCategoria().getId();
            categoriaNome = g.getCategoria().getNome();
        }
        CategoriaDto categoriaDto = new CategoriaDto(categoriaId, categoriaNome);

        List<Console> consoles = g.getConsoles();
        List<ConsoleDto> consolesDto = new ArrayList<ConsoleDto>();
        for (Console c : consoles) {
            ConsoleDto consoleDto = new ConsoleDto(c.getId(), c.getNome(), c.getIcona());
            consolesDto.add(consoleDto);
        }

        GiocoDto dto = new GiocoDto(
                g.getId(),
                g.getNome(),
                g.getDescrizione(),
                g.getVideo(),
                g.getImg(),
                categoriaDto,
                consolesDto);

        Map<String, Object> giocoMap = new HashMap<>();
        giocoMap.put("gioco", dto);
        return giocoMap;
    }

    public Map<String, Object> recuperaTuttiDaCategoriaId(long id) {

        List<Gioco> giochi = repo.findAllByCategoriaIdOrderByNomeAsc(id);
        List<GiocoDto> dto = new ArrayList<GiocoDto>();

        for (Gioco g : giochi) {

            CategoriaDto categoriaDto = new CategoriaDto(
                    g.getCategoria().getId(),
                    g.getCategoria().getNome());

            List<Console> consoles = g.getConsoles();
            List<ConsoleDto> consolesDto = new ArrayList<ConsoleDto>();
            for (Console c : consoles) {
                ConsoleDto consoleDto = new ConsoleDto(c.getId(), c.getNome(), c.getIcona());
                consolesDto.add(consoleDto);
            }

            dto.add(new GiocoDto(
                    g.getId(),
                    g.getNome(),
                    g.getDescrizione(),
                    g.getVideo(),
                    g.getImg(),
                    categoriaDto,
                    consolesDto
                    ));
        }

        Map<String, Object> giochiMap = new HashMap<>();
        giochiMap.put("giochi", dto);

        return giochiMap;
    }

    public Map<String, Object> recuperaTuttiDaConsoleId(long id) {

        List<Gioco> giochi = repo.findAllByConsolesIdOrderByNomeAsc(id);
        List<GiocoDto> dto = new ArrayList<GiocoDto>();

        for (Gioco g : giochi) {

            CategoriaDto categoriaDto = new CategoriaDto(
                    g.getCategoria().getId(),
                    g.getCategoria().getNome());

            List<Console> consoles = g.getConsoles();
            List<ConsoleDto> consolesDto = new ArrayList<ConsoleDto>();
            for (Console c : consoles) {
                ConsoleDto consoleDto = new ConsoleDto(c.getId(), c.getNome(), c.getIcona());
                consolesDto.add(consoleDto);
            }

            dto.add(new GiocoDto(
                    g.getId(),
                    g.getNome(),
                    g.getDescrizione(),
                    g.getVideo(),
                    g.getImg(),
                    categoriaDto,
                    consolesDto
                    ));
        }

        Map<String, Object> giochiMap = new HashMap<>();
        giochiMap.put("giochi", dto);

        return giochiMap;
    }

    @Override
    public Long salva(Gioco g) {

        try {
            Gioco giocoSalvato = repo.save(g);
            return giocoSalvato.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.debug("Errore durante il salvataggio dei dati");
            return null;
        }
    }

    public boolean elimina(long idGioco) {
        if (repo.existsById(idGioco)) {
            repo.deleteById(idGioco);
            return true;
        }
        return false;
    }
}
