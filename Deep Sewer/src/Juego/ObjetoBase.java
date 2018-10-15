package Juego;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class ObjetoBase extends Thread {

	protected PApplet app;
	protected Logica log;
	protected Txt txt;
	protected Camara camara;
	protected PVector pos;
	protected PVector vel;
	protected int width, height;
	protected boolean vivo;
	protected String id;
	protected boolean[] controlMovi;

	public ObjetoBase(Logica log) {
		this.log = log;
		this.app = log.getPApplet();
		this.txt = log.getTxt();
		
		this.camara = log.getCamara();
		this.vivo = true;
		this.vel = PVector.random2D();
		this.vel.mult(5);
		this.controlMovi = new boolean[6];
		controlMovi[0] = false;
		controlMovi[1] = false;
		controlMovi[2] = false;
		controlMovi[3] = false;
		controlMovi[4] = false;
		controlMovi[5] = false;
	}

	public void gravedad() {
			pos.y += 4;
	}

	public abstract void run();
	
	public void matar() {
		vivo = false;
	}
	
	public boolean getVida() {
		return vivo;
	}

	public String toString() {
		return id + "," + (int) pos.x + "," + (int) pos.y;
	}
	
	

}
