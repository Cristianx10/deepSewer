package Juego;

import Juego.Txt.Arrastrable;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Objetivo extends ObjetoBase implements Arrastrable{

	PImage puerta;
	public Objetivo(Logica log, PVector pos) {
		super(log);
		this.pos = pos;
		puerta = txt.obtenerImg("puerta").get(0);
	}
	
	
	public void pintatImagen(PImage img) {
		app.imageMode(PConstants.CORNER);
		app.image(img, pos.x, pos.y);
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void pintar() {
		pintatImagen(puerta);
	}


	@Override
	public boolean isSobreMouse() {
		boolean sobre = false;
		if (PApplet.dist(pos.x, pos.y, camara.mouseX(), camara.mouseY()) < width) {
			sobre = true;
		}
		return sobre;
	}

	@Override
	public void arrastrar() {
		if (isSobreMouse() && app.mousePressed) {
			this.pos.x = camara.mouseX();
			this.pos.y = camara.mouseY();
		}
	}

	
}
