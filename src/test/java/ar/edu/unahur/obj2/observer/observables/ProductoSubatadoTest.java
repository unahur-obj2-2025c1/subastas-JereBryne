package ar.edu.unahur.obj2.observer.observables;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.excepciones.OfertaSubastadorException;
import ar.edu.unahur.obj2.observer.observadores.*;

class ProductoSubatadoTest {
	
	ProductoSubastado productoSubastado= new ProductoSubastado(); 
	ProductoSubastado productoSubastadoSegundo= new ProductoSubastado(); 
	EstrategiaArriesgado estrategiaArriesgado = new EstrategiaArriesgado();
	Subastador s1 = new Subastador("gonzager",estrategiaArriesgado);
	Subastador s2 = new Subastador("diazdan",estrategiaArriesgado);
	Subastador s3 = new Subastador("martomau",estrategiaArriesgado);
	Subastador unico = new Subastador("unico",new EstrategiaUnico());
	Subastador limitado = new Subastador("limitado", new EstrategiaConLimite(20));
	
	
	@BeforeEach
	void setUp() {
		productoSubastado.reiniciarSubasta();
		s1.reiniciarSubastador();
		s2.reiniciarSubastador();
		s3.reiniciarSubastador();
		unico.reiniciarSubastador();
		limitado.reiniciarSubastador();
		
		productoSubastado.agregarObservador(s1);
		productoSubastado.agregarObservador(s3);
		productoSubastadoSegundo.agregarObservador(s2);
		productoSubastadoSegundo.agregarObservador(limitado);
		productoSubastadoSegundo.sacarObservador(s2);
		productoSubastadoSegundo.agregarObservador(unico);
		productoSubastadoSegundo.agregarObservador(s2);
	}
	
	void escenario1() {
		productoSubastado.agregarOferta(s3.ofertar());
		productoSubastado.agregarOferta(s1.ofertar());
		productoSubastado.agregarOferta(s3.ofertar());
	}
	
	void escenario2() {
		productoSubastadoSegundo.agregarOferta(limitado.ofertar());
		productoSubastadoSegundo.agregarOferta(s2.ofertar());
		productoSubastadoSegundo.agregarOferta(limitado.ofertar());
	}
	
	void escenario3() {
		productoSubastadoSegundo.agregarOferta(unico.ofertar());
		productoSubastadoSegundo.agregarOferta(s2.ofertar());
		productoSubastadoSegundo.agregarOferta(unico.ofertar()); 
	}
	
	@Test
	void loSubastadoresRecibenCorrectamenteLaUltimaOfertaRealizada() {
		escenario1();
		assertEquals(s3,s1.getUltimaOferta().getSubastador());
		assertEquals(s3,s3.getUltimaOferta().getSubastador());
	}
	
	@Test
	void laUltimaOfertaPerteneceArtomau(){
		escenario1();
		assertEquals("martomau",productoSubastado.getUltimaOferta().getSubastador().getUsername());
	}
	
	@Test
	void elValorDeLaUltimaOfertaEsDe30Pesutis() {
		escenario1();
		assertEquals(30,productoSubastado.getUltimaOferta().getValor());
	}
	
	@Test
	void laCantidadDeOfertasRecibidasEsDe3() {
		escenario1();
		assertEquals(3,productoSubastado.cantidadDeOfertasRecibidas());
	}
	
	@Test
	void noSEPuedeAgregarUnaOfertaDeDiazdanYaQueNoEsParticipante() {
		escenario1();
		assertThrows(OfertaSubastadorException.class, () -> productoSubastado.agregarOferta(s2.ofertar()),
                "El subastador martomau no participa en la subasta");
	}
	
	@Test
	void elLimitadoNoOfertaCuandoElValorSuperaSuUmbralDe20() {
		escenario2();
		assertEquals("diazdan",productoSubastadoSegundo.getUltimaOferta().getSubastador().getUsername());
		assertEquals(20,productoSubastadoSegundo.getUltimaOferta().getValor());
	}
	
	@Test
	void elUnicoHaceSolamenteUnaOferta() {
		escenario3();
		assertEquals("diazdan",productoSubastadoSegundo.getUltimaOferta().getSubastador().getUsername());
	}
	
}
