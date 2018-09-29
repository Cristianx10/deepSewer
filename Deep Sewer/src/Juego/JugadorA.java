package Juego;

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
		start();

		personaje[0] = new Animacion(app, pos, "Caballo", 8);
		personaje[1] = new Animacion(app, pos, "Caballo", 8);
		personaje[2] = new Animacion(app, pos, "Caballo", 8);
		personaje[3] = new Animacion(app, pos, "Caballo", 8);

		for (Animacion p : personaje) {
			p.play();
		}

	}

	// Metodo para pintar el personaje y recorrer el metodo de movimiento todo el
	// tiempo
	public void pintar() {
		for (Animacion p : personaje) {
			if (p.isPlaying()) {
				p.pintar();
			}
		}
	}

	/*
	 * Metodo para cuando el personaje cuando se opriman las teclas el se mueva para
	 * la posicion respectivas teclas y la accion de saltar
	 */
	public void movimiento() {

		gravedad();

		if (seMovio[0] && controlMovi[0]) {
			pos.x += vel.x;
		}

		if (seMovio[1] && controlMovi[1]) {
			pos.x -= vel.x;

		}

		saltar();

	}

	// Metodo para asignar las teclas a los movimientos del personaje en un swicth
	public void keyPressed() {

		if (app.key == app.CODED) {

			switch (app.keyCode) {

			case PConstants.RIGHT:

				seMovio[0] = true;

				break;

			case PConstants.LEFT:
				seMovio[1] = true;
				break;

			case PConstants.UP:

				seMovio[2] = true;

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
				seMovio[0] = false;
				break;

			case PConstants.LEFT:
				seMovio[1] = false;
				break;

			case PConstants.UP:
				seMovio[2] = false;
				break;

			}
		}

		seMovio[0] = false;
		seMovio[1] = false;

	}

	@Override
	public void run() {

		while (isVivo) {
			try {

				movimiento();

				if (app.keyPressed) {
					keyPressed();
				} else {
					keyReleased();
				}

				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}