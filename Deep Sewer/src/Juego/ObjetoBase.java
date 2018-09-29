package Juego;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class ObjetoBase extends Thread{
	
	protected PApplet app;
	protected Logica log;
	protected Camara camara;
	protected PVector pos;
	protected PVector vel;
	protected int width, height;
	protected boolean vivo;
	protected String id;
	protected boolean[] controlMovi;
	protected boolean[] seMovio; // variable para saber si el personaje se movio
	
	
	public ObjetoBase(Logica log) {
		this.log = log;
		this.app = log.getPApplet();
		this.camara = log.getCamara();
		this.vivo = true;
		this.controlMovi = new boolean[4];
		this.seMovio = new boolean[4];
	}
	
	public void gravedad() {
		
		if(seMovio[3]) {
			pos.y += 4;
		}
	
	}
	
	public abstract void run();
	
	public String toString() {
		return id + ", " + (int)pos.x + ", " + (int)pos.y;
	}

}
