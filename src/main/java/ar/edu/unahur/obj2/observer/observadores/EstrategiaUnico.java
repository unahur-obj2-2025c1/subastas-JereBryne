package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.Oferta;

public class EstrategiaUnico implements EstrategiaDeOferta {
	private boolean yaOferto = false;

	@Override
	public Oferta crearOferta(Subastador subastador, Oferta ultimaOferta) {
		if(!yaOferto) {
			yaOferto = true;
			return new Oferta(subastador,ultimaOferta.getValor()+10);
		}
		return ultimaOferta;
	}

}
