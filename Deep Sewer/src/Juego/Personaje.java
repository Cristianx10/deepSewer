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
	protected boolean[] controlMovi;
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
		this.width = 191;
		this.height = 144;
		this.pos = pos;
		this.seMovio = new boolean[4];
		
		personaje = new Animacion[4];
		seMovio[3] = true;
		
		
		this.controlMovi = new boolean[4];
		controlMovi[0] = true;
		controlMovi[1] = true;
		controlMovi[2] = true;
		controlMovi[3] = true;
		
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
	
		if(seMovio[3]) {
			pos.y += 4;
		}
			
	
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