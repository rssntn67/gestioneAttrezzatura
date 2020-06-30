package it.arsinfo.ga;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

import it.arsinfo.ga.dao.repository.AttrezzaturaDao;
import it.arsinfo.ga.dao.repository.ModelloAttrezzaturaDao;
import it.arsinfo.ga.data.Anno;
import it.arsinfo.ga.data.MarcaModello;
import it.arsinfo.ga.data.StatoAttrezzatura;
import it.arsinfo.ga.data.TipoModello;
import it.arsinfo.ga.entity.Attrezzatura;
import it.arsinfo.ga.entity.ModelloAttrezzatura;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GestioneAttrezzaturaApplicationTests {
    
	@Autowired
	private ModelloAttrezzaturaDao modelloAttrezzaturaDao;

	@Autowired
	private AttrezzaturaDao attrezzaturaDao;

    private static final Logger log = LoggerFactory.getLogger(GestioneAttrezzaturaApplicationTests.class);

    @Before    
    public void setUp() {        
    }
    
    @After
    public void clearDown() {
    }
    
    @Test
    public void testModelloAttrezzaturaCRUD() throws Exception {
    	ModelloAttrezzatura mda1 = new ModelloAttrezzatura();
    	mda1.setNome("prova");
    	mda1.setAnnoProduzione(Anno.ANNOND);
    	mda1.setDescrizione("aZz");
    	mda1.setMarcaModello(MarcaModello.Echo);
    	mda1.setTipoModello(TipoModello.Decespugliatori);
    	
    	modelloAttrezzaturaDao.save(mda1);
    	
    	assertEquals(1, modelloAttrezzaturaDao.count());
    	
    	ModelloAttrezzatura mdaDb = modelloAttrezzaturaDao.findByNome("prova");
    	assertNotNull(mdaDb);
    	
    	ModelloAttrezzatura mda2 = new ModelloAttrezzatura();
    	mda2.setNome("prova");
    	try {
    		modelloAttrezzaturaDao.save(mda2);
    		assertTrue(false);
    	} catch (Exception e) {
    		log.info(e.getMessage());
    		assertTrue(true);
		}
    	mda2.setAnnoProduzione(Anno.ANNOND);
    	try {
    		modelloAttrezzaturaDao.save(mda2);
    		assertTrue(false);
    	} catch (Exception e) {
    		log.info(e.getMessage());
    		assertTrue(true);
		}

    	modelloAttrezzaturaDao.delete(mdaDb);
       	assertEquals(0, modelloAttrezzaturaDao.count());
            	
    }
    
    
    @Test
    public void testAttrezzaturaCRUD() throws Exception {
    	ModelloAttrezzatura mda1 = new ModelloAttrezzatura();
    	mda1.setNome("prova");
    	mda1.setAnnoProduzione(Anno.ANNOND);
    	mda1.setDescrizione("aZz");
    	mda1.setMarcaModello(MarcaModello.Echo);
    	mda1.setTipoModello(TipoModello.Decespugliatori);
    	
    	modelloAttrezzaturaDao.save(mda1);
    	
    	log.info("saved: {} ", mda1);
    	assertEquals(0, attrezzaturaDao.count());
    	assertEquals(1, modelloAttrezzaturaDao.count());
    	
    	Attrezzatura a =  new Attrezzatura();
    	a.setIdentificativo("xp37jkf");
    	a.setModelloAttrezzatura(mda1);
    	
    	attrezzaturaDao.save(a);
    	assertEquals(1, attrezzaturaDao.count());
    	assertEquals(1, modelloAttrezzaturaDao.count());
    	
    	Attrezzatura fromDb = attrezzaturaDao.findByIdentificativo("xp37jkf");
    	
    	assertNotNull(fromDb);
    	log.info("saved: {} ", fromDb);
    	
    	fromDb.setStatoAttrezzatura(StatoAttrezzatura.InRiparazione);
    	
    	attrezzaturaDao.save(fromDb);

    	log.info("saved: {} ", fromDb);

    	assertEquals(StatoAttrezzatura.InRiparazione, fromDb.getStatoAttrezzatura());
    	
    	attrezzaturaDao.delete(fromDb);
    	
    	assertEquals(0, attrezzaturaDao.count());
    	assertEquals(1, modelloAttrezzaturaDao.count());
    	

    	modelloAttrezzaturaDao.deleteAll();
    	assertEquals(0, attrezzaturaDao.count());
    	assertEquals(0, modelloAttrezzaturaDao.count());
    	
    	            	
    }

}
