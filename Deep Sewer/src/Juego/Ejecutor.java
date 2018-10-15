package Juego;

import Comunicar.Servidor;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Ejecutor extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Juego.Ejecutor");
	}

	Logica log;

	public void settings() {

		size(1200, 700);

	}

	public void setup() {
		log = new Logica(this);

	}

	public void draw() {
		background(255);
		log.pintar();
	}

	public void keyPressed() {

		if(letra('p')) {
			log.getTxt().actualizar();
		}

	}

	public boolean letra(char l) {
		char m = ' ';
		char n = ' ';

		if (Character.isUpperCase(l)) {
			m = l;
			n = Character.toLowerCase(l);
		} else {
			m = Character.toUpperCase(l);
			n = l;
		}

		boolean pulsado = (key == m || key == n);

		return pulsado;
	}

}
