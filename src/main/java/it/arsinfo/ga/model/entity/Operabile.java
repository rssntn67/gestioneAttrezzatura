package it.arsinfo.ga.model.entity;

public abstract class Operabile<T extends Modello> implements EntityBase {

	public abstract String getIdentificativo();
	public abstract void setIdentificativo(String identificativo);

	public abstract T getModello();
	public abstract void setModello(T modello);

}
