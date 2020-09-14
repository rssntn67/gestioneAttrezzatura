package it.arsinfo.ga.model.entity;

public interface Operabile<T extends Modello> extends EntityBase {

	String getIdentificativo();
	void setIdentificativo(String identificativo);

	T getModello();
	void setModello(T modello);

}
