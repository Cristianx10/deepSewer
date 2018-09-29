package Juego;

import processing.core.PApplet;
import processing.core.PVector;

public class Logica {

	private PApplet app;
	private Personaje p;
	private Camara c;
	private PImage escena;

	public Logica(PApplet app) {
		this.app = app;
		
		p= new JugadorA(this, new PVector(0,0));
		c = new Camara(this, p);
	}

	public void pintar() {

		p.pintar();
	}

	// Getter an setters----------------------------------------------------------

	public PApplet getPApplet() {
		return app;
	}

}
