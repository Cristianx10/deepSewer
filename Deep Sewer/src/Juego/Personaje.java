package Juego;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import Comunicar.Mensaje;
import Comunicar.Receptor;
import Comunicar.Receptor.Mensajero;;

public abstract class Personaje extends ObjetoBase implements Mensajero{

	protected Animacion[] personaje;
	
	protected Receptor receptor;

	protected int reposo;
	protected int espera, espera2;
	protected boolean quitarPuntos, sumarPuntos;
	protected int vista;
	protected boolean golpe;
	protected int contSalto; // variable contador para ??
	protected Obstaculo obstaculos;
	protected Escenario escena;
	protected boolean escalando;
	protected int vida;
	protected int puntuacion;
	protected Escalera esc;
	protected String name;

	public Personaje(Logica log, PVector pos) {
		super(log);
		this.escena = log.getEscenario();
		vida = 100;
		puntuacion = 0;

		this.vel = new PVector(5, 5);
		this.width = 150;
		this.height = 144;
		this.pos = pos;
		this.name = " ";

		personaje = new Animacion[4];

		obstaculos = new Obstaculo(log, this);
		obstaculos.setNegativo(txt.obtenerImg("nb_escenario").get(0));
	}

	public abstract void pintar();

	public void mover(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}

	public abstract void run();

	public void movimiento() {

		if (obstaculos.right() && controlMovi[0] && !obstaculos.limRight()) {
			pos.x += vel.x;

			personaje[0].parar();
			personaje[1].parar();
			personaje[2].parar();
			personaje[3].play();
		}

		if (obstaculos.left() && controlMovi[1] && !obstaculos.limLeft()) {
			pos.x -= vel.x;
			personaje[0].parar();
			personaje[1].parar();
			personaje[2].play();
			personaje[3].parar();
		}

		if (escalando) {
			controlMovi[4] = false;
			personaje[0].parar();
			personaje[1].play();
			personaje[2].parar();
			personaje[3].parar();
		}
		if (controlMovi[4]) {

		}

		saltar();

		escalar();

		if (obstaculos.down() && !escalando) {
			gravedad();
		}
		
		if(controlMovi[0] || controlMovi[1] || controlMovi[2] || controlMovi[3] || controlMovi[4]) {
			
		}else {
			personaje[0].play();
			personaje[1].parar();
			personaje[2].parar();
			personaje[3].parar();
		}
		
		if(log.getEscenario().nivelAgua < (pos.y-height*2)) {
			vivo = false;
		}
		if(puntuacion < 0) {
			//vivo = false;
		}
		
	///	System.out.println("Puntuacion" + " " + puntuacion);

	}

	

	public boolean isSobre(float x, float y) {
		boolean sobre = false;
		if (PApplet.dist(pos.x, pos.y, x, y) < width) {
			sobre = true;
		}
		return sobre;
	}

	public void saltar() {
		if (controlMovi[4]) {

			if (contSalto < 20 && obstaculos.up()) {
				contSalto++;
				if (obstaculos.up()) {
					pos.y -= vel.y + 5;
				}
			} else if (!obstaculos.down()) {
				contSalto = 0;
				controlMovi[4] = false;
			}

		}
	}

	public void escalar() {
		if (escena != null && escena.getEscalera() != null) {
			if (escalando == false) {
				for (int i = 0; i < escena.getEscalera().size(); i++) {
					Escalera c = escena.getEscalera().get(i);

					if (isSobreEscalera(c) ) {
						esc = c;

						escalando = true;
					}

				}
			}

			if (esc != null) {
				if (isSobreEscalera(esc)) {
					if (controlMovi[2]) {

						pos.y -= vel.y;

					} else if ((controlMovi[3] && obstaculos.down()) || (controlMovi[3] && pos.y < esc.pos.y)) {
						pos.y += vel.y;
					}
				} else {
					esc = null;
					escalando = false;
				}
			}
		}
	}

	public void validar() {
		if (escena != null) {
			for (Object o : escena.getObjetos()) {
				if (o instanceof Barril) {
					Barril b = (Barril) o;
					if (pos.dist(b.pos) < ((width / 2) + (b.width / 2))) {
						quitarPuntos = true;
						b.matar();
					}

					else if (pos.dist(b.pos) < ((width / 2) + (b.width / 2) + 100)) {
						sumarPuntos = true;
					}
				}
			}

		}

		sumarPuntos();
		quitandoPuntos();
	}

	public void sumarPuntos() {
		if (sumarPuntos) {
			if (espera2 == 0) {
				espera2 = 1;
				puntuacion += 100;
			}
			if (espera2 >= 1 && espera2 < 50) {
				espera2++;
			} else {
				espera2 = 0;
				sumarPuntos = false;
			}
		}
	}

	public void quitandoPuntos() {
		if (quitarPuntos) {
			if (espera == 0) {
				espera = 1;
				puntuacion -= 150;
			}
			if (espera >= 1 && espera < 20) {
				espera++;
			} else {
				espera = 0;
				quitarPuntos = false;
			}
		}
	}

	public boolean isSobreEscalera(Escalera e) {
		boolean isSobre = false;
		if (pos.x > e.pos.x - (e.width / 2) && pos.x < e.pos.x + (e.width / 2)
				&& pos.y + (height / 2) + 10 > e.pos.y - (e.height / 2) && pos.y < e.pos.y + (e.height / 2)) {
			isSobre = true;

		}

		return isSobre;
	}

	public boolean[] getMovimiento() {
		return controlMovi;
	}

	public int getPuntuacion() {
		return puntuacion;

	}
	
	public String getNombre() {
		return name;
	}
	
	
	//Comunicacion--------------------------------
	
	public void startComunicacion(Receptor receptor) {
		this.receptor = receptor;
		receptor.cambiarMensajero(this);
	}
	
	boolean accion;
	public void Comunicacion(Mensaje m) {
		
			name = m.NAME;
			System.out.println(name);

			controlMovi[0] = m.RIGHT;
	
	
			controlMovi[1] = m.LEFT;
	
			controlMovi[4] = (m.ATACK || m.UP);
			
			controlMovi[2] = (m.ATACK || m.UP);
			
		
			controlMovi[3] = m.DOWN;
			
			if (accion == false) {
				accion = m.START;
				if (accion) {

					if (log.pantalla < 2) {
						
						log.pantalla++;
					}
				}
			}
			
			System.out.println(log.pantalla);

			accion = m.START;
	}
	

}