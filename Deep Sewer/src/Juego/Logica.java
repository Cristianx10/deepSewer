package Juego;

import java.awt.Image;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Logica {

	private PApplet app;
	private Personaje p;
	private Camara camara;
	private PImage escena;

	private Obstaculo plataforma;
	
	public PImage escenaNb;

	public Logica(PApplet app) {
		this.app = app;
		

		p = new JugadorA(this, new PVector(200, 0));
		
		escenaNb = app.loadImage("Imagenes/Escenario/nb_1.png");
		escena = app.loadImage("Imagenes/Escenario/1.png");
		
		//escena = escenaNb;

		camara = new Camara(this);
		this.camara.setObjetivo(escena, p);
		
	
		
		plataforma = new Obstaculo(this, p);
		
	}

	public void pintar() {
		
		camara.inicio();
		app.imageMode(PConstants.CORNER);
		app.image(escena, 0, 0);
		p.pintar();
		camara.fin();
	}
	

	// Getter an setters----------------------------------------------------------

	public PApplet getPApplet() {
		return app;
	}

}
