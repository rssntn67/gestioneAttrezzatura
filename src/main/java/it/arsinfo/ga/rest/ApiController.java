package it.arsinfo.ga.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.arsinfo.ga.model.dto.CantiereDto;
import it.arsinfo.ga.model.dto.OperabileDto;
import it.arsinfo.ga.model.dto.OperazioneDto;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.model.entity.ModelloConsumabile;
import it.arsinfo.ga.model.entity.ModelloPersonale;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;
import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.model.entity.Personale;
import it.arsinfo.ga.service.AttrezzaturaService;
import it.arsinfo.ga.service.CantiereService;
import it.arsinfo.ga.service.ConsumabileService;
import it.arsinfo.ga.service.OperazioneService;
import it.arsinfo.ga.service.PersonaleService;

@RestController
@RequestMapping("/api")
public class ApiController {
 	
	@Autowired 
	private AttrezzaturaService attrezzaturaService;
	@Autowired 
	private ConsumabileService consumabileService;
	@Autowired 
	private PersonaleService personaleService;
	@Autowired 
	private CantiereService cantiereService;
	@Autowired 
	private OperazioneService<ModelloAttrezzatura, Attrezzatura, OperazioneAttrezzatura> opattrezzaturaService;
	@Autowired 
	private OperazioneService<ModelloConsumabile, Consumabile, OperazioneConsumabile>  opconsumabileService;
	@Autowired 
	private OperazioneService<ModelloPersonale,Personale,OperazionePersonale> oppersonaleService;

	@CrossOrigin(origins = "http://localhost:8100")
	@GetMapping("/attrezzature") 
	public List<OperabileDto> findAttrezzature() {
		List<OperabileDto> list = new ArrayList<>();
		for (Attrezzatura operabile: attrezzaturaService.findAll()) {
			OperabileDto dto = new OperabileDto();
			dto.setIdentificativo(operabile.getIdentificativo());
			dto.setStato(operabile.getStato());
			dto.setModello(operabile.getModello().getHeader());
			list.add(dto);
		}
		return list;
	}

	@GetMapping("/consumabili") 
	public List<OperabileDto> findConsumabile() {
		List<OperabileDto> list = new ArrayList<>();
		for (Consumabile operabile :consumabileService.findAll()) {
			OperabileDto dto = new OperabileDto();
			dto.setIdentificativo(operabile.getIdentificativo());
			dto.setStato(operabile.getStato());
			dto.setModello(operabile.getModello().getHeader());
			list.add(dto);			
		}
		return list;
	}

	@GetMapping("/personale") 
	public List<OperabileDto> findPersonale() {
		List<OperabileDto> list = new ArrayList<>();
		for (Personale operabile :personaleService.findAll()) {
			OperabileDto dto = new OperabileDto();
			dto.setIdentificativo(operabile.getIdentificativo());
			dto.setStato(operabile.getStato());
			dto.setModello(operabile.getModello().getHeader());
			list.add(dto);			
		}
		return list;
	}

	@GetMapping("/cantieri") 
	public List<CantiereDto> findCantieri() {
		List<CantiereDto> list = new ArrayList<>();
		for (Cantiere cantiere: cantiereService.findAll() ) {
			CantiereDto dto = new CantiereDto();
			dto.setIdentificativo(cantiere.getIdentificativo());
			dto.setStato(cantiere.getStato());
			dto.setTipo(cantiere.getTipo());
			dto.setSitoIn(cantiere.getSitoIn());
		}
		return list;
	}

	@PostMapping("/operazione/attrezzatura") 
	public boolean addOperazioneAttrezzatura(@RequestBody OperazioneDto tree) {
		if (!check(tree))
			return false;
		Cantiere cantiere = cantiereService.findByIdentificativo(tree.getCantiereId());
		if (cantiere == null) 
			return false;
		Attrezzatura operabile = attrezzaturaService.findByIdentificativo(tree.getOperabileId());
		if (operabile == null) 
			return false;

		OperazioneAttrezzatura operazione = new OperazioneAttrezzatura();
		operazione.setCantiere(cantiere);
		operazione.setOperabile(operabile);
		operazione.setTipoOperazione(tree.getTipo());
		try {
			opattrezzaturaService.esegui(operazione);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private boolean check(OperazioneDto tree) {
		if (tree.getCantiereId() == null || tree.getOperabileId() == null || tree.getTipo() == null)
			return false;
		return true;
	}

	@PostMapping("/operazione/consumabile") 
	public boolean addOperazioneConsumabile(@RequestBody OperazioneDto tree) {
		if (!check(tree))
			return false;
		Cantiere cantiere = cantiereService.findByIdentificativo(tree.getCantiereId());
		if (cantiere == null) 
			return false;
		Consumabile operabile = consumabileService.findByIdentificativo(tree.getOperabileId());
		if (operabile == null) 
			return false;

		OperazioneConsumabile operazione = new OperazioneConsumabile();
		operazione.setCantiere(cantiere);
		operazione.setOperabile(operabile);
		operazione.setTipoOperazione(tree.getTipo());
		try {
			opconsumabileService.esegui(operazione);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@PostMapping("/operazione/personale") 
	public boolean addOperazionePersonale(@RequestBody OperazioneDto tree) {
		if (!check(tree))
			return false;
		Cantiere cantiere = cantiereService.findByIdentificativo(tree.getCantiereId());
		if (cantiere == null) 
			return false;
		Personale operabile = personaleService.findByIdentificativo(tree.getOperabileId());
		if (operabile == null) 
			return false;

		OperazionePersonale operazione = new OperazionePersonale();
		operazione.setCantiere(cantiere);
		operazione.setOperabile(operabile);
		operazione.setTipoOperazione(tree.getTipo());

		try {
			oppersonaleService.esegui(operazione);
		} catch (Exception e) {
			return false;
		}
		return true;
	}


}