package Juego;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Escalera extends ObjetoBase implements Txt.Arrastrable {

	PImage img;

	public Escalera(Logica log, PVector pos, String id) {
		super(log);
		this.id = id;
		this.pos = pos;
		
		
		img = txt.obtenerImg("escalera_1").get(0);
		
		this.width = img.width;
		this.height = img.height;
		
		if(pos.x == 0) {
			pos.x = app.random(width, app.width - width);
		}
	}

	public void pintar() {
		app.imageMode(PConstants.CENTER);
		app.image(img, pos.x, pos.y);
		arrastrar();
	}

	@Override
	public void run() {
		while (vivo) {
			try {
				movimiento();
				acciones();
				gravedad();
				
				
				sleep(20);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

	}
	
	public void movimiento() {

	

	}

	public void acciones() {
		// Acciones hacia los
		// peronajes--------------------------------------------------------------------------

		// for(Personaje p : log.getPlayers()) {
		// //Chouqe con los personajes--------------------------------------
		// if(pos.dist(p.pos) < (width/2 + p.width/2) && !p.golpeado()) {
		// p.golpe = true;
		// }
		//
		// }
		gravedad();
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
