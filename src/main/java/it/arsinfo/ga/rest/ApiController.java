package it.arsinfo.ga.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.arsinfo.ga.model.data.StatoOperatore;
import it.arsinfo.ga.model.data.TipoOperazione;
import it.arsinfo.ga.model.dto.CantiereDto;
import it.arsinfo.ga.model.dto.OperabileDto;
import it.arsinfo.ga.model.dto.OperazioneDto;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;
import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.AttrezzaturaService;
import it.arsinfo.ga.service.CantiereService;
import it.arsinfo.ga.service.ConsumabileService;
import it.arsinfo.ga.service.OperatoreService;
import it.arsinfo.ga.service.OperazioneService;
import it.arsinfo.ga.service.PersonaleService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private AttrezzaturaService attrezzaturaService;
    @Autowired
    private ConsumabileService consumabileService;
    @Autowired
    private PersonaleService personaleService;
    @Autowired
    private CantiereService cantiereService;
    @Autowired
    private OperatoreService operatoreService;
    @Autowired
    private OperazioneService<Attrezzatura, OperazioneAttrezzatura> operazioneAttrezzaturaService;
    @Autowired
    private OperazioneService<Consumabile, OperazioneConsumabile> operazioneConsumabileService;
    @Autowired
    private OperazioneService<Personale, OperazionePersonale> operazionePersonaleService;

    @GetMapping("/attrezzature")
    public List<OperabileDto> findAttrezzature(
            @RequestParam(value = "apikey", required = true) String apikey) {
        List<OperabileDto> list = new ArrayList<>();
        if (!check(apikey)) {
            return list;
        }
        for (Attrezzatura operabile : attrezzaturaService.findAll()) {
            OperabileDto dto = new OperabileDto();
            dto.setIdentificativo(operabile.getIdentificativo());
            dto.setStato(operabile.getStato());
            dto.setModello(operabile.getModello().getHeader());
            list.add(dto);
        }
        return list;
    }

    @GetMapping("/attrezzature/{identificativo}")
    public OperabileDto getAttrezzatura(@PathVariable String identificativo,
            @RequestParam(value = "apikey", required = true) String apikey) {
        Attrezzatura operabile = attrezzaturaService.findByIdentificativo(identificativo);
        if (operabile == null) {
            throw new RuntimeException("Could not find attrezzatura with "
                    + identificativo);
        }
        OperabileDto dto = new OperabileDto();
        dto.setIdentificativo(operabile.getIdentificativo());
        dto.setStato(operabile.getStato());
        dto.setModello(operabile.getModello().getHeader());
        return dto;
    }

    @GetMapping("/consumabili")
    public List<OperabileDto> findConsumabile(
            @RequestParam(value = "apikey", required = true) String apikey) {
        List<OperabileDto> list = new ArrayList<>();
        if (!check(apikey)) {
            return list;
        }
        for (Consumabile operabile : consumabileService.findAll()) {
            OperabileDto dto = new OperabileDto();
            dto.setIdentificativo(operabile.getIdentificativo());
            dto.setStato(operabile.getStato());
            dto.setModello(operabile.getModello().getHeader());
            list.add(dto);
        }
        return list;
    }

    @GetMapping("/personale")
    public List<OperabileDto> findPersonale(
            @RequestParam(value = "apikey", required = true) String apikey) {
        List<OperabileDto> list = new ArrayList<>();
        if (!check(apikey)) {
            return list;
        }
        for (Personale operabile : personaleService.findAll()) {
            OperabileDto dto = new OperabileDto();
            dto.setIdentificativo(operabile.getIdentificativo());
            dto.setStato(operabile.getStato());
            dto.setModello(operabile.getModello().getHeader());
            list.add(dto);
        }
        return list;
    }

    @GetMapping("/cantieri")
    public List<CantiereDto> findCantieri(
            @RequestParam(value = "apikey", required = true) String apikey) {
        List<CantiereDto> list = new ArrayList<>();
        if (!check(apikey)) {
            return list;
        }
        for (Cantiere cantiere : cantiereService.findAll()) {
            CantiereDto dto = new CantiereDto();
            dto.setIdentificativo(cantiere.getIdentificativo());
            dto.setStato(cantiere.getStato());
            dto.setTipo(cantiere.getTipo());
            dto.setSitoIn(cantiere.getSitoIn());
            list.add(dto);
        }
        return list;
    }

    @PostMapping("/operazione/attrezzatura")
    public boolean addOperazioneAttrezzatura(
            @RequestBody OperazioneDto tree) {
        Operatore operatore = check(tree);
        if (operatore == null) {
            return false;
        }
        Attrezzatura operabile = attrezzaturaService.findByIdentificativo(tree.getOperabileId());
        if (operabile == null) {
            log.warn("add: Not Processing Dto: Operabile does not exist for: {}, Tipo: {}, ApiKey: {}, Cantiere: {}",
                     tree.getOperabileId(), tree.getTipo(), tree.getApikey(),
                     tree.getCantiereId());
            return false;
        }
        if (tree.getCantiereId() == null
                && tree.getTipo() == TipoOperazione.Carico) {
            log.warn("add: Not Processing Dto: Cantiere Null and Tipo Carico: Operabile: {}, Tipo: {} ApiKey: {}",
                     tree.getOperabileId(), tree.getTipo(), tree.getApikey());
            return false;
        }
        Cantiere cantiere = null;
        if (tree.getCantiereId() != null) {
            cantiere = cantiereService.findByIdentificativo(tree.getCantiereId());
        } else {
            OperazioneAttrezzatura latest = operazioneAttrezzaturaService.getLastOperation(operabile);
            if (latest == null) {
                log.warn("add: Not Processing Dto: Cantiere Null not found last operation: Operabile: {}, Tipo: {} ApiKey: {}",
                         tree.getOperabileId(), tree.getTipo(),
                         tree.getApikey());
                return false;
            }
            cantiere = cantiereService.findById(latest.getCantiere().getId());
        }
        if (cantiere == null) {
            log.warn("add: Not Processing Dto: Cantiere does not exists: Operabile: {}, Tipo: {} ApiKey: {}",
                     tree.getOperabileId(), tree.getTipo(), tree.getApikey());
            return false;
        }

        OperazioneAttrezzatura operazione = new OperazioneAttrezzatura();
        operazione.setCantiere(cantiere);
        operazione.setOperabile(operabile);
        operazione.setOperatore(operatore);
        operazione.setTipoOperazione(tree.getTipo());
        try {
            operazioneAttrezzaturaService.esegui(operazione);
        } catch (Exception e) {
            log.error("esegui: {}", e.getMessage());
            return false;
        }
        return true;
    }

    private Operatore check(OperazioneDto tree) {
        if (tree.getOperabileId() == null || tree.getTipo() == null
                || tree.getApikey() == null) {
            log.warn("add: Not Processing Dto: Operabile: {}, Tipo: {}, ApiKey: {}, Cantiere: {} ",
                     tree.getOperabileId(), tree.getTipo(), tree.getApikey(),
                     tree.getCantiereId());
            return null;
        }
        Operatore operatore = operatoreService.findByApikey(tree.getApikey());
        if (operatore == null)
            log.warn("add: Not Processing Dto: Api Key does not exists: Operabile: {}, Tipo: {} ApiKey: {}",
                     tree.getOperabileId(), tree.getTipo(), tree.getApikey());
        return operatore;
    }

    private boolean check(String apikey) {
        if (apikey == null) {
            log.warn("get: Not Processing ApiKey: null");
            return false;
        }
        Operatore operatore = operatoreService.findByApikey(apikey);
        if (operatore == null) {
            log.warn("get: Not Processing Api Key does not exists: ApiKey: {}",
                     apikey);
            return false;
        }
        if (operatore.getStato() != StatoOperatore.Attivo) {
            log.warn("get: Not Processing StatoOperatore {} not valid: ApiKey: {}",
                     operatore.getStato(), apikey);
            return false;
        }
        return true;
    }

    @PostMapping("/operazione/consumabile")
    public boolean addOperazioneConsumabile(@RequestBody OperazioneDto tree) {
        Operatore operatore = check(tree);
        if (operatore == null)
            return false;
        Consumabile operabile = consumabileService.findByIdentificativo(tree.getOperabileId());
        if (operabile == null) {
            return false;
        }
        if (tree.getCantiereId() == null
                && tree.getTipo() == TipoOperazione.Carico) {
            return false;
        }
        Cantiere cantiere = null;
        if (tree.getCantiereId() != null) {
            cantiere = cantiereService.findByIdentificativo(tree.getCantiereId());
        } else {
            OperazioneConsumabile latest = operazioneConsumabileService.getLastOperation(operabile);
            if (latest == null) {
                return false;
            }
            cantiere = cantiereService.findById(latest.getCantiere().getId());
        }
        if (cantiere == null) {
            return false;
        }

        OperazioneConsumabile operazione = new OperazioneConsumabile();
        operazione.setCantiere(cantiere);
        operazione.setOperabile(operabile);
        operazione.setTipoOperazione(tree.getTipo());
        operazione.setNumero(tree.getNumero());
        try {
            operazioneConsumabileService.esegui(operazione);
        } catch (Exception e) {
            log.error("esegui: {}", e.getMessage());
            return false;
        }
        return true;
    }

    @PostMapping("/operazione/personale")
    public boolean addOperazionePersonale(@RequestBody OperazioneDto tree) {
        Operatore operatore = check(tree);
        if (operatore == null)
            return false;
        Personale operabile = personaleService.findByIdentificativo(tree.getOperabileId());
        if (operabile == null) {
            return false;
        }
        if (tree.getCantiereId() == null
                && tree.getTipo() == TipoOperazione.Carico) {
            return false;
        }
        Cantiere cantiere = null;
        if (tree.getCantiereId() != null) {
            cantiere = cantiereService.findByIdentificativo(tree.getCantiereId());
        } else {
            OperazionePersonale latest = operazionePersonaleService.getLastOperation(operabile);
            if (latest == null) {
                return false;
            }
            cantiere = cantiereService.findById(latest.getCantiere().getId());
        }
        if (cantiere == null) {
            return false;
        }

        OperazionePersonale operazione = new OperazionePersonale();
        operazione.setCantiere(cantiere);
        operazione.setOperabile(operabile);
        operazione.setTipoOperazione(tree.getTipo());
        operazione.setNumero(tree.getNumero());

        try {
            operazionePersonaleService.esegui(operazione);
        } catch (Exception e) {
            log.error("esegui: {}", e.getMessage());
            return false;
        }
        return true;
    }

}