package Juego;

import processing.core.PApplet;
import processing.core.PImage;

public class Obstaculo extends Thread {

	private Logica log;
	private PApplet app;
	private Personaje p;
	private PImage plataforma;
	private int colorBase;
	private boolean vivo = true;

	public Obstaculo(Logica log, Personaje p) {
		this.log = log;
		this.app = log.getPApplet();
		this.p = p;
		this.colorBase = app.color(0);
		this.plataforma = log.escenaNb;
		start();
	}

	public void limitesBasic() {

		if ((p.pos.x + p.width / 2) + p.vel.x > plataforma.width) {
			p.controlMovi[0] = false;
		} else {
			p.controlMovi[0] = true;
		}

		if ((p.pos.x - p.width / 2) - p.vel.x < 0) {
			p.controlMovi[1] = false;
		} else {
			p.controlMovi[1] = true;
		}

		if ((p.pos.y + p.height / 2) + p.vel.y > plataforma.height) {

		} else {
			// p.controlMovi[2] = true;
		}

		if ((p.pos.y - p.height / 2) - p.vel.y < 0) {

		} else {
			// p.controlMovi[3] = true;
		}

	}

	public boolean up() {
		PImage img = plataforma;
		int dis = 5;
		boolean sobre = false;

		if (img.get((int) p.pos.x, (int) (p.pos.y - dis)) == colorBase ||

				img.get((int) (p.pos.x - (p.width / 2) + dis), (int) (p.pos.y - (p.height / 2))) == colorBase ||

				img.get((int) (p.pos.x + (p.width / 2) - dis), (int) (p.pos.y - (p.height / 2))) == colorBase

		) {
			sobre = false;
		} else {
			sobre = true;
		}

		return sobre;
	}

	public boolean down() {
		PImage img = plataforma;
		int dis = 5;
		boolean sobre = false;

		if (img.get((int) p.pos.x, (int) (p.pos.y + (p.height / 2))) == colorBase ||

				img.get((int) (p.pos.x - (p.width / 2) + dis), (int) (p.pos.y + (p.height / 2))) == colorBase ||

				img.get((int) (p.pos.x + (p.width / 2) - dis), (int) (p.pos.y + (p.height / 2))) == colorBase) {
			sobre = false;
		} else {
			sobre = true;
		}

		return sobre;
	}

	public boolean left() {
		PImage img = plataforma;
		int dis = 5;
		boolean sobre = false;

		if (img.get((int) (p.pos.x - (p.width / 2)), (int) (p.pos.y)) == colorBase ||

				img.get((int) (p.pos.x - (p.width / 2)), (int) (p.pos.y - (p.height / 2) + dis)) == colorBase ||

				img.get((int) (p.pos.x - (p.width / 2)), (int) (p.pos.y + (p.height / 2) - dis)) == colorBase) {
			sobre = false;

		} else {
			sobre = true;

		}

		return sobre;
	}

	public boolean right() {
		PImage img = plataforma;
		int dis = 5;
		boolean sobre = false;

		if (img.get((int) (p.pos.x + (p.width / 2)), (int) (p.pos.y)) == colorBase ||

				img.get((int) (p.pos.x + (p.width / 2)), (int) (p.pos.y - (p.height / 2) + dis)) == colorBase ||

				img.get((int) (p.pos.x + (p.width / 2)), (int) (p.pos.y + (p.height / 2) - dis)) == colorBase

		) {

			sobre = false;
		} else {
			sobre = true;
		}

		return sobre;
	}

	@Override
	public void run() {
		while (vivo) {
			try {

				limitesBasic();

				if (!down()) {
					p.seMovio[3] = false;
				} else {
					p.seMovio[3] = true;
				}

				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
