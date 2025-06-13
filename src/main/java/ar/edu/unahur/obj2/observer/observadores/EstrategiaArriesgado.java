package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.Oferta;

public class EstrategiaArriesgado implements EstrategiaDeOferta {

	@Override
	public Oferta crearOferta(Subastador subastador, Oferta ultimaOferta) {
		return new Oferta(subastador,ultimaOferta.getValor()+10);
	}

}
