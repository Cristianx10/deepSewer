package Juego;

import processing.core.PApplet;
import processing.core.PVector;

public class Barril extends Thread implements Objeto{

	private Logica log;
	private PApplet app;
	private Camara camara;
	
	private int width, height;
	private int reposo;
	private boolean vivo;
	private PVector pos, vel;
	private Animacion barril;

	public Barril(Logica log, PVector pos) {
		this.log = log;
		this.app = log.getPApplet();
		this.camara = log.getCamara();
		this.pos = pos;
		this.vel = new PVector(5, 5);
		
		this.barril = new Animacion(app, pos, "Caballo", 6);

	}


	public void pintar() {
		barril.pintar();
		if(isSobreMouse()) {
			
		}
	
		arrastre();
		
	}


	public void run() {
		while(vivo) {
			try {
				movimiento();
				acciones();
			sleep(20);
			}catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
	}


	public void movimiento() {
		
//		if(pos.x<190 || pos.x>240) {
//			vel.x *=-1;
//			
//		}
		
		//gravedad();
		
		enReposo();
		
		
	}
	
	public void acciones() {
		//Acciones hacia los peronajes--------------------------------------------------------------------------
		
//		for(Personaje p : log.getPlayers()) {
//			//Chouqe con los personajes--------------------------------------
//			if(pos.dist(p.pos) < (width/2 + p.width/2) && !p.golpeado()) {
//				p.golpe = true;
//			}
//			
//		}
		gravedad();
	}
	
	public void gravedad() {
		
	}
	
	public void enReposo() {
//		if(!obstaculo.left()) {
//			vel.x=+1;
//			
//		}
//		if(!obstaculo.right()) {
//			vel.x=-1;
//		
//		}
//		
//		pos.x+=vel.x;
	}


	@Override
	public boolean isSobreMouse() {
		boolean sobre = false;
		if (PApplet.dist(pos.x, pos.y, camara.mouseX(), camara.mouseY()) < width) {
			sobre = true;
		}
		return sobre;
	}
	
	public void arrastre() {
		this.pos.x = camara.mouseX();
		this.pos.y = camara.mouseY();
	}
	
	public String toString(){
		
		return null;
	}
	


}
