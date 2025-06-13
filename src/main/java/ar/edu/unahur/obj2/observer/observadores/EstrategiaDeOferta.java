package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.Oferta;

public interface EstrategiaDeOferta {
	Oferta crearOferta(Subastador subastador, Oferta ultimaOferta);
}
