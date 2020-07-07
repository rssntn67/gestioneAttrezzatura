package it.arsinfo.ga.model.entity;

public abstract class Modello implements EntityBase {

	public abstract String getNome();
	public abstract String getDescrizione();
	
	public abstract void setNome(String nome);
	public abstract void setDescrizione(String descrizione);
	
}
