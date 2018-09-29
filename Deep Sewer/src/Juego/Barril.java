package Juego;

import processing.core.PApplet;
import processing.core.PVector;

public class Barril extends ObjetoBase implements Txt.Arrastrable {

	private Obstaculo obstaculo;

	private int reposo;

	private Animacion barril;

	public Barril(Logica log, PVector pos, String id) {
		super(log);
		this.id = id;
		
		this.pos = pos;
		this.vel = new PVector(5, 5);
		this.width = 100;
		this.height = 100;

		this.obstaculo = new Obstaculo(log, this);
		
		this.barril = new Animacion(app, pos, "Caballo", 6);
		
		start();
	}

	public void pintar() {
		barril.pintar();
		arrastrar();
	}

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

		 if(obstaculo.limLeft() || obstaculo.limRight()) {
			 pos.x += -vel.x;
			 vel.x *=-1;
		 }
		 pos.x += vel.x;

		// gravedad();

		enReposo();

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


	public void enReposo() {
		// if(!obstaculo.left()) {
		// vel.x=+1;
		//
		// }
		// if(!obstaculo.right()) {
		// vel.x=-1;
		//
		// }
		//
		// pos.x+=vel.x;
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

	@Override
	public String toString() {

		return id + "," + (int)pos.x + "," + (int)pos.y;

	}

}
