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
		
		
		

	}

	// Metodo para pintar el personaje y recorrer el metodo de movimiento todo el
	// tiempo
	public void pintar() {
		

	}

	/*
	 * Metodo para cuando el personaje cuando se opriman las teclas el se mueva para
	 * la posicion respectivas teclas y la accion de saltar
	 */
	public void movimiento() {
		vista = 0;

		gravedad();

//
//		if (seMovio[0] && obstaculo.right()) {
//			pos.x += vel.x;
//			vista = 2;
//		}
//
//		if (seMovio[1] && obstaculo.left()) {
//			pos.x -= vel.x;
//			vista = 3;
//		}

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

//			case PConstants.UP:
//				if (!obstaculo.down()) {
//					seMovio[2] = true;
//				}
//				break;

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
				// seMovio[2] = false;
				break;

			}
		}
	}

	@Override
	public void run() {

		while (isVivo) {
			try {

				movimiento();

				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}