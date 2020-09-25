package it.arsinfo.ga.ui.operatore;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;

import it.arsinfo.ga.model.entity.Operatore;
import it.arsinfo.ga.ui.vaadin.entity.Add;

public class OperatoreAdd extends Add<Operatore> {

	public static String toHex(UUID uuid) {
        ByteBuffer bytes = ByteBuffer.wrap(new byte[16]);
        bytes.putLong(uuid.getMostSignificantBits());
        bytes.putLong(uuid.getLeastSignificantBits());
        return new String(Hex.encodeHex(bytes.array()));
    }
    public OperatoreAdd(String caption) {
        super(caption);
    }
    
    @Override
    public Operatore generate() {
    	Operatore cantiere = new Operatore();
    	cantiere.setIdentificativo("");
    	cantiere.setApikey(toHex(UUID.randomUUID()));
        return cantiere;
    }

}
