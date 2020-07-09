package it.arsinfo.ga.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.arsinfo.ga.model.data.TipoOperazione;
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
 
	private class OperazioneTree {
		private String cantiereId;
		private String operabileId;
		private TipoOperazione tipo;
		
		public String getCantiereId() {
			return cantiereId;
		}
		public String getOperabileId() {
			return operabileId;
		}
		public TipoOperazione getTipo() {
			return tipo;
		}		
	}
	
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

	@GetMapping("/attrezzature") 
	public List<Attrezzatura> findAttrezzature() {
		return attrezzaturaService.findAll();
	}

	@GetMapping("/consumabili") 
	public List<Consumabile> findConsumabile() {
		return consumabileService.findAll();
	}

	@GetMapping("/personale") 
	public List<Personale> findPersonale() {
		return personaleService.findAll();
	}

	@GetMapping("/cantieri") 
	public List<Cantiere> findCantieri() {
		return cantiereService.findAll();
	}

	@PostMapping("/operazione/attrezzatura") 
	public boolean addOperazioneAttrezzatura(@RequestBody OperazioneTree tree) {
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
	
	private boolean check(OperazioneTree tree) {
		if (tree.getCantiereId() == null || tree.getOperabileId() == null || tree.getTipo() == null)
			return false;
		return true;
	}

	@PostMapping("/operazione/consumabile") 
	public boolean addOperazioneConsumabile(@RequestBody OperazioneTree tree) {
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
	public boolean addOperazionePersonale(@RequestBody OperazioneTree tree) {
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