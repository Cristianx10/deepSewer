package Juego;

import Cliente.Cliente;
import Mensaje.Mensaje;
import Servidor.Servidor;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Ejecutor extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Juego.Ejecutor");
	}

	Logica log;
	
	Animacion a;

	Cliente c;

	public void settings() {

		size(1200, 700);

	}

	public void setup() {
		log = new Logica(this);
		//c = new Cliente();
		

		
		a = new Animacion(this, new PVector(width/2, height/2), "caballo", 8);
		a.start();
	}

	public void draw() {
		background(255);
		a.pintar();
	}

	public void keyPressed() {

		switch (key) {

		case 'a':

			Mensaje m = new Mensaje();
			m.name = "Ir a la izquierda";
			c.receptor.enviar(m);

			break;
		case 's':

			Mensaje m1 = new Mensaje();
			m1.name = "Ir hacia abajo";
			c.receptor.enviar(m1);

			break;
		case 'd':

			Mensaje m2 = new Mensaje();
			m2.name = "Ir a la derecha";
			c.receptor.enviar(m2);

			break;
		case 'w':

			Mensaje m3 = new Mensaje();
			m3.name = "Saltar";
			c.receptor.enviar(m3);

			break;

		}

	}

}
