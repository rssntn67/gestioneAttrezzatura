package it.arsinfo.ga.entity;

import java.util.Date;

import it.arsinfo.ga.data.TipoOperazione;

public abstract class Operazione<K extends Modello,T extends Operabile<K>> implements EntityBase {

	public abstract T getOperabile();
	public abstract void setOperabile(T operabile);
	public abstract Cantiere getCantiere();
	public abstract void setCantiere(Cantiere cantiere);
	public abstract TipoOperazione getTipoOperazione();
	public abstract void setTipoOperazione(TipoOperazione tipoOperazione);
	public abstract Date getDataOperazione();
	public abstract void setDataOperazione(Date date);

}
