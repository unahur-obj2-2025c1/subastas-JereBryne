package ar.edu.unahur.obj2.observer.observables;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unahur.obj2.observer.Oferta;
import ar.edu.unahur.obj2.observer.excepciones.OfertaSubastadorException;
import ar.edu.unahur.obj2.observer.observadores.Observer;

public class ProductoSubastado implements Observable{
	public List<Oferta> ofertasRecibidas = new ArrayList<>();
	public Set<Observer> subastadores = new HashSet<>();

	@Override
	public void agregarObservador(Observer observador) { 
		subastadores.add(observador);
	}

	@Override
	public void sacarObservador(Observer observador) {
		subastadores.remove(observador);
	}
	
	@Override
	public void notificar() {
		subastadores.forEach(o -> o.actualizar(getUltimaOferta()));
	}

	public Oferta getUltimaOferta() {
		return ofertasRecibidas.get(ofertasRecibidas.size()-1);
	}

	public void agregarOferta(Oferta oferta) {
		if (!subastadores.contains(oferta.getSubastador())) {
			throw new OfertaSubastadorException(
					"El subastador "+ oferta.getSubastador().getUsername() + " no participa en la subasta");
		}
		ofertasRecibidas.add(oferta);
		notificar();
	}
	
	public List<Oferta> getOfertas() {
        return ofertasRecibidas;
    }

    public Set<Observer> getSubastadores() {
        return subastadores;
    }

    public void reiniciarSubasta() {
    	ofertasRecibidas.clear();
    	subastadores.clear();
    }
    
    public Integer cantidadDeOfertasRecibidas() {
    	return ofertasRecibidas.size();
    }

}
