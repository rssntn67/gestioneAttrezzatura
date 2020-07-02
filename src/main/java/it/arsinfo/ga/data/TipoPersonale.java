package it.arsinfo.ga.data;

import java.math.BigDecimal;

public enum TipoPersonale {
	Autisti(new BigDecimal("100.40")),
	Operai(new BigDecimal("50.35")),
	Altri(new BigDecimal("40.00"));
	
	public final BigDecimal costo;
	
	private TipoPersonale(BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getCosto() {
		return costo;
	}
	
	
}
