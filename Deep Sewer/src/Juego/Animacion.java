
package Juego;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Animacion extends Thread {

	private Logica log;
	private PApplet app;
	private Txt txt;
	private PVector pos;
	private boolean vivo;
	private boolean movimiento;
	private ArrayList<PImage> img;
	private int contAnim;
	public int width, height;

	public Animacion(Logica log, PVector pos, String name) {
		this.log = log;
		this.app = log.getPApplet();
		this.txt = log.getTxt();

		vivo = true;
		img = new ArrayList<>();
		this.pos = pos;
		img = txt.obtenerImg(name);

		width = img.get(0).width;
		height = img.get(0).height;

		start();

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
