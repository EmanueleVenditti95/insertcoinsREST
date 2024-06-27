package it.academylab.insertcoinsREST.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.academylab.insertcoinsREST.dto.ConsoleDto;
import it.academylab.insertcoinsREST.entities.Console;
import it.academylab.insertcoinsREST.repositories.ConsoleRepository;


@Service
public class ConsoleServiceImpl implements ConsoleService{

    @Autowired
    private ConsoleRepository repo;

    @Override
    public Map<String, Object> recuperaTuttiOrdByNome() {
       
        List<Console> consoles = repo.findAllByOrderByNomeAsc();
        List<ConsoleDto> dto = new ArrayList<ConsoleDto>();

        for(Console c : consoles) {
            dto.add(new ConsoleDto(c.getId(),c.getNome(),c.getIcona()));
        }

        Map<String,Object> consolesMap = new HashMap<>();
        consolesMap.put("consoles", dto);
        return consolesMap;
    }

    @Override
    public Map<String, Object> recuperaConsoleDaId(long id) {
        Console c = repo.findById(id);
        ConsoleDto dto = new ConsoleDto(c.getId(),c.getNome(),c.getIcona());
        Map<String, Object> consoleMap = new HashMap<>();
        consoleMap.put("console", dto);
        return consoleMap;
    }

}
