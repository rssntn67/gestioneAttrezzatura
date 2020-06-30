package it.arsinfo.ga.dao;

import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.entity.Cantiere;

public interface GestioneAttrezzaturaService {

	void carico(Cantiere cantiere, Attrezzatura attrezzatura);
	void scarico(Attrezzatura attrezzatura);
	void scarico(Cantiere cantiere);
	void smarrimento(Attrezzatura attrezzatura);
	void furto(Attrezzatura attrezzatura);
	void rottura(Attrezzatura attrezzatura);
	
}
