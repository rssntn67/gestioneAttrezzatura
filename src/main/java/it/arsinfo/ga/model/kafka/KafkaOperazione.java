package it.arsinfo.ga.model.kafka;

import java.math.BigDecimal;
import java.util.Date;

import it.arsinfo.ga.model.data.TipoOperabile;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.Consumabile;
import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneConsumabile;
import it.arsinfo.ga.model.entity.OperazionePersonale;
import it.arsinfo.ga.model.entity.Personale;

public class KafkaOperazione {
    
    private final String tipoOperabile;

    //Modello
    private final String marca;
    private final String tipo;
    private final String descrizione;
    private final String fornitore;
    private final String annoProduzione;
    private final BigDecimal costo;
 
    //operabile
    private final String operabile;

    //Operatore
    private final String operatore;
    
    //Cantiere
    private final String cantiere;
    private final String tipoCantiere;

    //Operazione
    private final String tipoOperazione;
    private final Date dataOperazione;
    private final Integer numero;
    public Integer getNumero() {
        return numero;
    }

    private final String key;

    public KafkaOperazione(OperazioneAttrezzatura operazione, Attrezzatura operabile, Cantiere cantiere, Operatore operatore) {
        this.key=Long.toString(operazione.getId());
        this.dataOperazione=operazione.getDataOperazione();
        this.tipoOperazione=operazione.getTipoOperazione().name();
        this.numero=1;
        
        this.tipoCantiere=cantiere.getTipo().name();
        this.cantiere=cantiere.getIdentificativo();
        
        this.operatore=operatore.getIdentificativo();
        
        this.operabile=operabile.getIdentificativo();
        
        this.costo=operabile.getModello().getCosto();
        this.annoProduzione=operabile.getModello().getAnnoProduzione().getAnnoAsString();
        this.fornitore=operabile.getModello().getFornitore().name();
        this.descrizione=operabile.getModello().getDescrizione();
        this.tipo=operabile.getModello().getTipo().name();
        this.marca=operabile.getModello().getMarca().name();
        
        this.tipoOperabile=TipoOperabile.Attrezzatura.name();
    }

    public KafkaOperazione(OperazioneConsumabile operazione, Consumabile operabile, Cantiere cantiere, Operatore operatore) {
        this.key=Long.toString(operazione.getId());
        this.dataOperazione=operazione.getDataOperazione();
        this.tipoOperazione=operazione.getTipoOperazione().name();
        this.numero=operazione.getNumero();
        
        this.tipoCantiere=cantiere.getTipo().name();
        this.cantiere=cantiere.getIdentificativo();
        
        this.operatore=operatore.getIdentificativo();
        
        this.operabile=operabile.getIdentificativo();
        
        this.costo=operabile.getModello().getCosto();
        this.annoProduzione=operabile.getModello().getAnnoProduzione().getAnnoAsString();
        this.fornitore=operabile.getModello().getFornitore().name();
        this.descrizione=operabile.getModello().getDescrizione();
        this.tipo=operabile.getModello().getTipo().name();
        this.marca=operabile.getModello().getMarca().name();
        
        this.tipoOperabile=TipoOperabile.Consumabile.name();
    }
    
    public KafkaOperazione(OperazionePersonale operazione, Personale operabile, Cantiere cantiere, Operatore operatore) {
        this.key=Long.toString(operazione.getId());
        this.dataOperazione=operazione.getDataOperazione();
        this.tipoOperazione=operazione.getTipoOperazione().name();
        this.numero=operazione.getNumero();
        
        this.tipoCantiere=cantiere.getTipo().name();
        this.cantiere=cantiere.getIdentificativo();
        
        this.operatore=operatore.getIdentificativo();
        
        this.operabile=operabile.getIdentificativo();
        
        this.costo=operabile.getModello().getCosto();
        this.annoProduzione=operabile.getModello().getAnnoProduzione().getAnnoAsString();
        this.fornitore=operabile.getModello().getFornitore().name();
        this.descrizione=operabile.getModello().getDescrizione();
        this.tipo=operabile.getModello().getTipo().name();
        this.marca="NA";
        
        this.tipoOperabile=TipoOperabile.Personale.name();
    }


    public String getTipoOperabile() {
        return tipoOperabile;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getFornitore() {
        return fornitore;
    }

    public String getAnnoProduzione() {
        return annoProduzione;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public String getOperabile() {
        return operabile;
    }

    public String getOperatore() {
        return operatore;
    }

    public String getCantiere() {
        return cantiere;
    }

    public String getTipoCantiere() {
        return tipoCantiere;
    }

    public String getTipoOperazione() {
        return tipoOperazione;
    }

    public Date getDataOperazione() {
        return dataOperazione;
    }

    public String getKey() {
        return key;
    }    
}
