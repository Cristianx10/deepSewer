package Juego;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public abstract class Personaje extends Thread {

	protected PApplet app;
	protected Logica log;
	protected PVector pos;
	protected PVector vel;
	protected int width, height;
	protected Animacion[] personaje;
	protected boolean isVivo;

	protected String id;
	protected boolean[] seMovio; // variable para saber si el personaje se movio
	protected int reposo;
	protected boolean golpe;
	protected int contSalto; // variable contador para ??

	public Personaje(Logica log, PVector pos) {
		this.log = log;
		this.app = log.getPApplet();
		this.pos = new PVector(0, 0);
		this.vel = new PVector(5, 5);
		this.isVivo = true;
		this.width = 45;
		this.height = 45;
		this.pos = pos;
		this.seMovio = new boolean[4];
		
		
	}

	public abstract void pintar();
	

	public void mover(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}

	public abstract void run();

	public abstract void movimiento();

	public boolean isSobre(float x, float y) {
		boolean sobre = false;
		if (PApplet.dist(pos.x, pos.y, x, y) < width) {
			sobre = true;
		}
		return sobre;
	}

	
	public void gravedad() {
	
			pos.y += 4;
	
	}
	

	public void saltar() {
		if (seMovio[2]) {

			if (contSalto < 20) {
				contSalto++;
				pos.y -= vel.y+5;
			} else {
				contSalto = 0;
				seMovio[2] = false;
			}

		} else{
			contSalto = 0;
			seMovio[2] = false;
		}
	}

	public void recoger() {

	}

	
	public boolean[] getSeMovio() {
		return seMovio;
	}
	
	public String toString() {
		return id + ", " + pos.x + ", " + pos.y;
	}
	

}