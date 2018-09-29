package Juego;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Camara extends Thread{

	private PApplet app;
	private Logica log;
	private PImage base;
	private PVector pos;
	private Personaje p;
	private boolean activo;

	// private float pos.x, pos.y;

	// private float veloc;

	public Camara(Logica log) {
		this.log = log;
		this.app = log.getPApplet();
		this.pos = new PVector(0, 0);
		activo = true;
		start();
	}

	public void inicio() {
		app.pushMatrix();
		app.translate(this.pos.x, this.pos.y);
	}

	public void fin() {
		app.popMatrix();
	}

	public void run() {
		while(activo) {
			try {
				movientoCamara();
				
				sleep(20);
			}catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
	public void movientoCamara() {
		moviCamDown();
		moviCamLeft();
		moviCamUp();
		moviCamRight();
	}

	// Mueve la camara hacia arriba si no para el limete de 0 en la posicion y.
	public void moviCamUp() {
		if (base != null && p != null && p.seMovio[2]) {
			if (pos.y <= -p.vel.y && !(p.pos.y - PApplet.abs(pos.y) > app.height / 2)) {
				pos.y += p.vel.y;
			}
		}
	}

	// Mueve la camara del personaje hacia abajo si Y no pasa el tamano del lienzo
	// total
	public void moviCamDown() {
		if (base != null && p != null && p.seMovio[3]) {
			if (p.pos.y - PApplet.abs(pos.y) > app.height / 2 + p.vel.y && (pos.y > -(base.height - app.height))) {
				// -------------------------------------------resta el tamano de la pantalla con
				// el del lienzo total para que detenga el moviemiento de la camara.
				pos.y -= p.vel.y;
			}
		}
	}

	// Mueve la camara en la posicion x del personaje cuando ocurre movimiento.
	public void moviCamLeft() {
		if (base != null && p != null && p.seMovio[1]) {
			if (pos.x <= -p.vel.x && !(p.pos.x - PApplet.abs(pos.x) >= app.width / 2)) {
				pos.x += p.vel.x;
			}
		}
	}

	// Mueve la camara en la posicion x del personaje cuando ocurre movimiento.
	public void moviCamRight() {
		if (base != null && p != null && p.seMovio[0]) {
			if (pos.x - PApplet.abs(pos.x) >= app.width / 2 && (pos.x > -(base.width - app.width))) {
				pos.x -= p.vel.x;
			}
		}
	}
	
	public void setObjetivo(PImage img, Personaje p) {
		this.p = p;
		this.base = img;
	}

	public void setPersonaje(Personaje p) {
		this.p = p;
	}
	
	public void setImageBase(PImage img) {
		this.base = img;
	}
	
	public PVector getPos() {
		return pos;
	}

}
