package Juego;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Barril extends ObjetoBase implements Txt.Arrastrable {

	private Obstaculo obstaculo;

	private int reposo;
	private Escalera es;

	private Animacion img;

	private ArrayList<Escalera> escaleras;

	public Barril(Logica log, PVector pos) {
		super(log);
		this.id = "barril";

		this.pos = pos;
		this.vel = new PVector(5, 5);
		this.obstaculo = new Obstaculo(log, this);

		this.obstaculo.setNegativo(txt.obtenerImg("nb_escenario").get(0));

		this.img = new Animacion(log, pos, "Barril");

		this.width = img.width;
		this.height = img.height;
		img.play();

		escaleras = new ArrayList<>();

		start();
	}

	public void pintar() {
		img.pintar();
		arrastrar();

	}

	public void run() {
		while (vivo) {
			try {
				getEscalera();
				movimiento();
				
				if(obstaculo.limDown()) {
					vivo = false;
				}

				sleep(20);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

	}

	boolean cargaEscaleras = false;
	


	public void getEscalera() {
		if (log.getEscenario() != null && !cargaEscaleras) {
			for (int i = 0; i < log.getEscenario().getEscalera().size(); i++) {
				Escalera o = log.getEscenario().getEscalera().get(i);
				
					Escalera c = (Escalera) o;
					escaleras.add(c);
				
			}
		}
	}

	boolean bajando = false;

	public void movimiento() {

		if (es == null) {
			for (Escalera e : escaleras) {
				if (isSobreEscalera(e)) {
					bajando = true;
					es = e;
				} 
			}
		}
		
		if(es != null) {
			if (isSobreEscalera(es)) {
				bajando = true;
			} else {
				es = null;
				bajando = false;

			}
		}

		if (!bajando) {
			if (obstaculo.limLeft() || obstaculo.limRight()) {
				pos.x += -vel.x;
				vel.x *= -1;
			}
			pos.x += vel.x;
		} else {
			pos.y += vel.y;
		}

		if (obstaculo.down()) {
			gravedad();
		}

	}

	public boolean isSobreEscalera(Escalera e) {
		boolean isSobre = false;
		if (pos.x - width / 2 > e.pos.x - (e.width / 2) && pos.x + width / 2 < e.pos.x + (e.width / 2)
				&& pos.y + height > e.pos.y - (e.height / 2) && pos.y + height < e.pos.y + (e.height / 2)) {
			isSobre = true;
		}
		return isSobre;
	}

	@Override
	public boolean isSobreMouse() {
		boolean sobre = false;
		if (PApplet.dist(pos.x, pos.y, camara.mouseX(), camara.mouseY()) < width) {
			sobre = true;
		}
		return sobre;
	}

	@Override
	public void arrastrar() {
		if (isSobreMouse() && app.mousePressed) {
			this.pos.x = camara.mouseX();
			this.pos.y = camara.mouseY();
		}
	}

}
