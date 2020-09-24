package it.arsinfo.ga.model.entity;

import java.util.Date;

import it.arsinfo.ga.model.data.TipoOperazione;

public interface Operazione<T extends Operabile<?>> extends EntityBase {

	T getOperabile();
	void setOperabile(T operabile);
	Cantiere getCantiere();
	void setCantiere(Cantiere cantiere);
	TipoOperazione getTipoOperazione();
	void setTipoOperazione(TipoOperazione tipoOperazione);
	Date getDataOperazione();
	void setDataOperazione(Date date);

}
