package Juego;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Animacion extends Thread {

	private PApplet app;
	private PVector pos;
	private boolean vivo;
	private boolean movimiento;
	private ArrayList<PImage> img;
	private int contAnim;

	public Animacion(PApplet app, PVector pos, String name, int numero) {
		this.app = app;

		vivo = true;
		img = new ArrayList<>();
		this.pos = pos;
		for (int i = 0; i < numero; i++) {
			PImage ima = app.loadImage("Animacion/" + name + "/" + (i + 1) + ".PNG");
			img.add(ima);
		}

	}

	public void pintar() {
		PImage imagen = img.get(contAnim);
		app.imageMode(PConstants.CENTER);
		app.image(imagen, pos.x, pos.y);
	}

	@Override
	public void run() {

		while (this.vivo) {
			try {

				if (this.movimiento) {
					if (contAnim + 1 > img.size() - 1) {

						contAnim = 0;

					} else {

						contAnim++;

					}
				}

				sleep(200);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}
		}

	}

	public void play() {
		
		this.movimiento = true;

	}

	public void parar() {
		
		this.movimiento = false;
	}
	
	public boolean isPlaying() {
		
		return movimiento;
		
	}

}
