package Juego;

import processing.core.PApplet;
import processing.core.PVector;

public class Barril extends Thread{

	private Logica log;
	private PApplet app;
	
	private int reposo;
	private boolean vivo;
	private PVector pos;

	public Barril(Logica log, PVector pos) {
		this.pos = pos;

	}


	public void pintar() {
		
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
		
		if(siguiendo) {
			
		}else {
			enReposo();
		}
		
		
	}
	
	public void acciones() {
		//Acciones hacia los peronajes--------------------------------------------------------------------------
		siguiendo = false;
		
		for(Personaje p : log.getPlayers()) {
			//Chouqe con los personajes--------------------------------------
			if(pos.dist(p.pos) < (width/2 + p.width/2) && !p.golpeado()) {
				p.golpe = true;
			}
			
		}
		gravedad();
	}
	
	public gravedad() {
		
	}
	
	public void enReposo() {
		if(!obstaculo.left()) {
			vel.x=+1;
			vista = 0;
		}
		if(!obstaculo.right()) {
			vel.x=-1;
			vista =1;
		}
		
		pos.x+=vel.x;
	}
	
	public void perseguir(Personaje p) {
		int dis  = (int) (p.pos.x - this.pos.x);
		if(dis >= 0) {
			
			if(obstaculo.right()) {
				pos.x += 2;
			}
			else {
				saltar();
				
				if(!obstaculo.down()) {
					seMovio[2] = true;
				}
			}
			
		}else if(dis <= 0 ) {
			
			if(obstaculo.left()) {
				pos.x -= 2;
			}
			else {
				saltar();
				if(!obstaculo.down()) {
					seMovio[2] = true;
				}
			}
				
		}		
	}


}
