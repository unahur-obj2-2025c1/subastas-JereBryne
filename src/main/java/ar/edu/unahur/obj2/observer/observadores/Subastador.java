package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.Oferta;

public class Subastador implements Observer {

	private String username;
	private Oferta ultimaOferta;
	private EstrategiaDeOferta estrategia;
	
	public Subastador(String username,EstrategiaDeOferta estrategia) {
		this.username = username;
		this.estrategia = estrategia;
		actualizar(new Oferta(this,0));
	}
	
	@Override
	public void actualizar(Oferta oferta) {
		this.ultimaOferta = oferta;
	}
	
	public Oferta ofertar() {
		return estrategia.crearOferta(this, ultimaOferta);
	}

	public Oferta getUltimaOferta() {
		return ultimaOferta;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void reiniciarSubastador() {
		actualizar(new Oferta(this,0));
	}

}
 