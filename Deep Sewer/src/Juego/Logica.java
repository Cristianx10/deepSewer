package Juego;

import java.awt.Image;

import javax.net.ssl.SSLException;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Logica {

	private PApplet app;
	private Personaje p;
	private Camara camara;
	private PImage escena;
	
	private Barril b;
	private Obstaculo plataforma;
	
	public PImage escenaNb;

	public Logica(PApplet app) {
		this.app = app;
		camara = new Camara(this);
		
		b = new Barril(this, new PVector(400, 0));
		p = new JugadorA(this, new PVector(200, 0));
		
		escenaNb = app.loadImage("Imagenes/Escenario/nb_1.png");
		escena = app.loadImage("Imagenes/Escenario/1.png");
		
		//escena = escenaNb;

		
		this.camara.setObjetivo(escena, p);
		
		plataforma = new Obstaculo(this, p);
	}

	public void pintar() {
		
		camara.inicio();
		app.imageMode(PConstants.CORNER);
		app.image(escena, 0, 0);
		p.pintar();
		
		
		b.pintar();
		camara.fin();
	}
	

	// Getter an setters----------------------------------------------------------

	public PApplet getPApplet() {
		return app;
	}
	
	public Camara getCamara() {
		return camara;
	}
	
	

}
