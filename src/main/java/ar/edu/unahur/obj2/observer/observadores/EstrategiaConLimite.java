package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.Oferta;

public class EstrategiaConLimite implements EstrategiaDeOferta {
	
	private Integer umbral;
	
	public EstrategiaConLimite(Integer umbral) {
		this.umbral = umbral;
	}

	@Override
	public Oferta crearOferta(Subastador subastador, Oferta ultimaOferta){
		if (ultimaOferta.getValor()<umbral) {
			return new Oferta(subastador,ultimaOferta.getValor()+10);
		} 
			return ultimaOferta;
	}

}
