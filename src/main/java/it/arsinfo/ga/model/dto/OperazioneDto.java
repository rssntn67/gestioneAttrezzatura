package it.arsinfo.ga.model.dto;

import it.arsinfo.ga.model.data.TipoOperazione;

public class OperazioneDto {
		private String cantiereId;
		private String operabileId;
		private TipoOperazione tipo;
		
		public String getCantiereId() {
			return cantiereId;
		}
		public String getOperabileId() {
			return operabileId;
		}
		public TipoOperazione getTipo() {
			return tipo;
		}
}
