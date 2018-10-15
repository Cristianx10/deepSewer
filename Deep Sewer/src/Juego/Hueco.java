package Juego;

import java.util.Iterator;

import Juego.Txt.Arrastrable;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Hueco extends ObjetoBase implements Arrastrable{
	

	PImage img;
	
	public Hueco(Logica log, PVector pos) {
		super(log);
		id = "Hueco";
		this.pos = pos;
		this.vel = new PVector(5, 5);
		this.width = 100;
		this.height = 100;
		img = txt.obtenerImg("hueco").get(0);
	}
	boolean detener = false;
	public void pintar() {
		if(log.start && detener == false) {
			start();
			detener = true;
		}
		
		app.imageMode(PConstants.CENTER);
		app.image(img, pos.x, pos.y);
	}

	@Override
	public void run() {
		
		while(vivo) {
			
			log.getEscenario().getObjetos().add(new Barril(log, new PVector(pos.x, pos.y)));
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

	@Override
	public void arrastrar() {
		if (isSobreMouse() && app.mousePressed) {
			this.pos.x = camara.mouseX();
			this.pos.y = camara.mouseY();
		}		
	}

	@Override
	public boolean isSobreMouse() {
		boolean sobre = false;
		if (PApplet.dist(pos.x, pos.y, camara.mouseX(), camara.mouseY()) < width) {
			sobre = true;
		}
		return sobre;
	}

}
