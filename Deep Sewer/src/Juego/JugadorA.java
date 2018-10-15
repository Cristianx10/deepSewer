package Juego;

import Comunicar.Mensaje;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class JugadorA extends Personaje {

	/*
	 * Constructor del JugadorA que solo recibe una variable Logica y en el que se
	 * incializacian los booleans ,se cargan las imagenes y la posicion del
	 * personajes
	 */

	public JugadorA(Logica log, PVector pos) {
		super(log, pos);

		contSalto = 0;
		id = "UsuarioA";

		personaje[0] = new Animacion(log, pos, "Personaje/1");
		personaje[1] = new Animacion(log, pos, "Personaje/2");
		personaje[2] = new Animacion(log, pos, "Personaje/3");
		personaje[3] = new Animacion(log, pos, "Personaje/4");

		this.width = personaje[0].width;
		this.height = personaje[0].height;

		personaje[0].play();

		start();
	}

	// Metodo para pintar el personaje y recorrer el metodo de movimiento todo el
	// tiempo
	public void pintar() {
		for (Animacion p : personaje) {
			if (p.isPlaying()) {
				p.pintar();
			}
		}
		
		if(PApplet.dist(log.getEscenario().puerta.pos.x, log.getEscenario().puerta.pos.y, pos.x, pos.y) < 30) {
			log.setPantalla(3);
			System.out.println(PApplet.dist(1100, 124, 1100, 124));
		}

	}

	/*
	 * Metodo para cuando el personaje cuando se opriman las teclas el se mueva para
	 * la posicion respectivas teclas y la accion de saltar
	 */


	// Metodo para asignar las teclas a los movimientos del personaje en un swicth
	public void keyPressed() {
		if (app.key == app.CODED) {

			switch (app.keyCode) {

			case PConstants.RIGHT:
				controlMovi[0] = true;
				break;

			case PConstants.LEFT:
				controlMovi[1] = true;
				break;

			case PConstants.UP:
				controlMovi[4] = true;
				controlMovi[2] = true;
				break;
			case PConstants.DOWN:
				controlMovi[3] = true;
				break;

			}
		}
	}

	// Metodo para saber si el personaje no se esta moviendo con ninguna de las
	// teclas
	public void keyReleased() {

		if (app.key == app.CODED) {

			switch (app.keyCode) {

			case PConstants.RIGHT:
				controlMovi[0] = false;
				break;

			case PConstants.LEFT:
				controlMovi[1] = false;
				break;

			case PConstants.UP:
				controlMovi[2] = false;
				break;
			case PConstants.DOWN:
				controlMovi[3] = true;
				break;

			}
		}

		controlMovi[0] = false;
		controlMovi[1] = false;
		controlMovi[2] = false;
		controlMovi[3] = false;

		
	}

	@Override
	public void run() {

		while (vivo) {
			try {

				movimiento();
				validar();
			

//				if (app.keyPressed) {
//					keyPressed();
//				} else {
//					keyReleased();
//				}

				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void respuesta(Mensaje m) {
		Comunicacion(m);
	}

}