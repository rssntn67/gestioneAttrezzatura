package it.arsinfo.ga;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.arsinfo.ga.dao.AttrezzaturaDao;
import it.arsinfo.ga.dao.CantiereDao;
import it.arsinfo.ga.dao.ModelloAttrezzaturaDao;
import it.arsinfo.ga.dao.OperazioneAttrezzaturaDao;
import it.arsinfo.ga.model.data.Anno;
import it.arsinfo.ga.model.data.Fornitore;
import it.arsinfo.ga.model.data.MarcaAttrezzatura;
import it.arsinfo.ga.model.data.StatoCantiere;
import it.arsinfo.ga.model.data.StatoOperabile;
import it.arsinfo.ga.model.data.TipoAttrezzatura;
import it.arsinfo.ga.model.entity.Attrezzatura;
import it.arsinfo.ga.model.entity.Cantiere;
import it.arsinfo.ga.model.entity.ModelloAttrezzatura;
import it.arsinfo.ga.model.entity.OperazioneAttrezzatura;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GestioneAttrezzaturaApplicationTests {
    
	@Autowired
	private ModelloAttrezzaturaDao modelloAttrezzaturaDao;

	@Autowired
	private AttrezzaturaDao attrezzaturaDao;

	@Autowired
	private CantiereDao cantiereDao;
	
	@Autowired
	private OperazioneAttrezzaturaDao operazioneDao;
	
    private static final Logger log = LoggerFactory.getLogger(GestioneAttrezzaturaApplicationTests.class);

    @Before    
    public void setUp() {        
    }
    
    @After
    public void clearDown() {
    }
    
    @Test
    public void testCantiereCRUD() throws Exception {

    	assertEquals(0, cantiereDao.count());
    	Cantiere c = new Cantiere();
    	c.setIdentificativo("Autostrade-MI-NA-0006");
    	
    	cantiereDao.save(c);
    	
    	assertEquals(1, cantiereDao.count());

    	Cantiere fromDb = cantiereDao.findByIdentificativo("Autostrade-MI-NA-0006");
    	
    	assertNotNull(fromDb);
    	
    	assertEquals(StatoCantiere.InOpera, fromDb.getStato());
    	
    	fromDb.setStato(StatoCantiere.Terminato);
    	cantiereDao.save(fromDb);
    	assertEquals(1, cantiereDao.count());
    	
    	Cantiere fromDb2 = cantiereDao.findByStato(StatoCantiere.Terminato).iterator().next();
    	assertNotNull(fromDb2);
    	
    	cantiereDao.delete(fromDb2);
    	assertEquals(0, cantiereDao.count());
    	
    	
            	
    }

    @Test
    public void testModelloAttrezzaturaCRUD() throws Exception {
    	ModelloAttrezzatura mda1 = new ModelloAttrezzatura();
    	mda1.setNome("prova");
    	mda1.setAnnoProduzione(Anno.ANNOND);
    	mda1.setDescrizione("aZz");
    	mda1.setMarca(MarcaAttrezzatura.Echo);
    	mda1.setTipo(TipoAttrezzatura.Decespugliatori);
    	
    	modelloAttrezzaturaDao.save(mda1);
    	
    	assertEquals(1, modelloAttrezzaturaDao.count());
    	
    	ModelloAttrezzatura mdaDb = modelloAttrezzaturaDao.findByNomeAndFornitoreAndAnnoProduzione("prova",Fornitore.NonDefinito,Anno.ANNOND).iterator().next();
    	assertNotNull(mdaDb);
    	
    	modelloAttrezzaturaDao.delete(mdaDb);
       	assertEquals(0, modelloAttrezzaturaDao.count());
            	
    }
    
    
    @Test
    public void testAttrezzaturaCRUD() throws Exception {
    	ModelloAttrezzatura mda1 = new ModelloAttrezzatura();
    	mda1.setNome("prova");
    	mda1.setAnnoProduzione(Anno.ANNOND);
    	mda1.setDescrizione("aZz");
    	mda1.setMarca(MarcaAttrezzatura.Echo);
    	mda1.setTipo(TipoAttrezzatura.Decespugliatori);
    	
    	modelloAttrezzaturaDao.save(mda1);
    	
    	log.info("saved: {} ", mda1);
    	assertEquals(0, attrezzaturaDao.count());
    	assertEquals(1, modelloAttrezzaturaDao.count());
    	
    	Attrezzatura a =  new Attrezzatura();
    	a.setIdentificativo("xp37jkf");
    	a.setModello(mda1);
    	
    	attrezzaturaDao.save(a);
    	assertEquals(1, attrezzaturaDao.count());
    	assertEquals(1, modelloAttrezzaturaDao.count());
    	
    	Attrezzatura fromDb = attrezzaturaDao.findByIdentificativo("xp37jkf");
    	
    	assertNotNull(fromDb);
    	log.info("saved: {} ", fromDb);
    	
    	fromDb.setStato(StatoOperabile.InRiparazione);
    	
    	attrezzaturaDao.save(fromDb);

    	log.info("saved: {} ", fromDb);

    	assertEquals(StatoOperabile.InRiparazione, fromDb.getStato());
    	
    	attrezzaturaDao.delete(fromDb);
    	
    	assertEquals(0, attrezzaturaDao.count());
    	assertEquals(1, modelloAttrezzaturaDao.count());
    	

    	modelloAttrezzaturaDao.deleteAll();
    	assertEquals(0, attrezzaturaDao.count());
    	assertEquals(0, modelloAttrezzaturaDao.count());
    	    	            	
    }
    
    @Test
    public void testOperazioneCRUD() throws Exception {

    	Cantiere c = new Cantiere();
    	c.setIdentificativo("Autostrade-MI-NA-0006");
    	
    	cantiereDao.save(c);
    	
    	ModelloAttrezzatura mda1 = new ModelloAttrezzatura();
    	mda1.setNome("AAA1");
    	mda1.setAnnoProduzione(Anno.ANNOND);
    	mda1.setDescrizione("aZz");
    	mda1.setMarca(MarcaAttrezzatura.Echo);
    	mda1.setTipo(TipoAttrezzatura.Decespugliatori);
    	
    	modelloAttrezzaturaDao.save(mda1);
    	
    	Attrezzatura a =  new Attrezzatura();
    	a.setIdentificativo("xp37jkf");
    	a.setModello(mda1);
    	attrezzaturaDao.save(a);
    	
    	OperazioneAttrezzatura o = new OperazioneAttrezzatura();
    	o.setCantiere(c);
    	o.setOperabile(a);
    	operazioneDao.save(o);
    	
    	log.info("{}",o);
    	
    	operazioneDao.deleteAll();
    	attrezzaturaDao.deleteAll();
    	cantiereDao.deleteAll();
    	modelloAttrezzaturaDao.deleteAll();

    	
    	
    }
    

}
