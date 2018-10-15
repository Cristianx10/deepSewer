package Juego;

import java.awt.HeadlessException;
import java.util.ArrayList;

import Juego.Txt.Arrastrable;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Escenario {

	private PApplet app;
	private Logica log;
	private PImage escena;
	private Txt txt;
	private PImage tiempo;
	private PImage pun1, pun2;
	private boolean comenzo = false;
	
	public int nivelAgua;

	public PImage escenaNb;

	private ArrayList<Arrastrable> objetos;
	private ArrayList<Escalera> escaleras;
	private Barril b;
	private Obstaculo plataforma;
	private ArrayList<Personaje> jugadores;
	String time;
	private boolean startTime;
	public int minutos, maxTime;
	public Objetivo puerta;

	public Escenario(Logica log) {
		this.log = log;
		this.app = log.getPApplet();
		this.txt = log.getTxt();

		objetos = new ArrayList<Arrastrable>();
		escaleras = new ArrayList<Escalera>();
		
		escenaNb = txt.obtenerImg("nb_escenario").get(0);
		escena = txt.obtenerImg("escenario").get(0);

		// escena = escenaNb;
		
		

		jugadores = new ArrayList<Personaje>();
		tiempo = txt.obtenerImg("tiempo").get(0);

		pun1 = txt.obtenerImg("puntos1").get(0);
		pun2 = txt.obtenerImg("puntos2").get(0);
		
		puerta = new Objetivo(log, new PVector(1041, 181));
		
		
		
		nivelAgua = escena.height + 100;
		
	}

	public void iniciarTimepo(int mine, int seco) {
		startTime = true;

		new Thread(new Runnable() {
			int min = mine;
			int sec = seco;

			@Override
			public void run() {
				minutos = (min * 60) + sec;
				maxTime = minutos + 0;
				while (startTime) {
					try {

						sec--;
						minutos--;

						if (sec < 0) {
							min--;
							sec = 59;
						}
					
						if (minutos <= 0) {

							startTime = false;
						}

						String minTime = "" + min;
						String secTime = "" + sec;

						if (minTime.length() == 1) {
							minTime = "0" + min;
						}
						if (secTime.length() == 1) {
							secTime = "0" + sec;
						}

						time = minTime + ":" + secTime;

						Thread.sleep(1000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	public void agua() {
		float ti = app.map(minutos, maxTime, 0, 0, escena.height - 200);
		app.fill(0, 0, 255, 50);
		app.rect(0, escena.height, app.width, -ti);
		
		nivelAgua = (int) (escena.height - ti);
		
	}

	public void interfaz() {
		app.imageMode(PConstants.CENTER);
		app.image(tiempo, app.width / 2, 22);
		app.fill(0);
		app.textAlign(PConstants.CENTER, PConstants.CENTER);
		app.textSize(20);
		app.text(time, app.width / 2 - 166, 22);
		app.fill(0, 0, 255);
		// 15
		// 326
		float ti = app.map(minutos, 0, maxTime, 15, 326);
		app.rect(app.width / 2 - 131, 14, ti, 18);

		if (jugadores.size() >= 1) {
			app.imageMode(PConstants.CORNER);
			app.image(pun1, 0, 0);
			app.image(pun2, app.width - pun2.width, 0);
			app.textSize(35);
			app.textAlign(PConstants.CORNER, PConstants.CENTER);
			app.fill(0);
			app.text(jugadores.get(0).getPuntuacion(), 120, 32);
			app.text(jugadores.get(1).getPuntuacion(), app.width - 260, 32);

		}

	}

	boolean detener = false;
	public void pintar() {
		
		if(log.start && detener == false) {
			iniciarTimepo(1, 60);
			detener = true;
			
		}
		

		app.imageMode(PConstants.CORNER);
		app.image(escena, 0, 0);

		for (int i = 0; i < objetos.size(); i++) {
			objetos.get(i).pintar();
		}
		
		for (int i = 0; i < objetos.size(); i++) {
			Object o = objetos.get(i);
			
			if(o instanceof ObjetoBase) {
				ObjetoBase b = (ObjetoBase) o;
				if(!b.getVida()){
					objetos.remove(b);
				}
			}
		}
		
		for (Escalera escalera : escaleras) {
			escalera.pintar();
		}
		
		puerta.pintar();

		for (Personaje p : jugadores) {
			p.pintar();
		}
		

		if (jugadores.size() >= 2) {
			if (jugadores.get(0).pos.y < jugadores.get(1).pos.y) {
				log.getCamara().setPersonaje(jugadores.get(0));
			} else {
				log.getCamara().setPersonaje(jugadores.get(1));
			}
		}
		
		validar();
	}

	public void crearJugador(int x, int y, int tipo) {
		Personaje p = null;
		if(tipo == 1) {
		 p = new JugadorA(log, new PVector(x, y));
		}else {
			 p = new JugadorB(log, new PVector(x, y));
		}
		jugadores.add(p);
		log.getCamara().setPersonaje(p);
	}

	// Getter y setters---------------------------------------

	public ArrayList<Txt.Arrastrable> getObjetos() {
		return objetos;
	}
	
	public ArrayList<Escalera> getEscalera() {
		return escaleras;
	}

	public ArrayList<Personaje> getJugadores() {
		return jugadores;
	}
	
	public void eliminar() {
		for (int i = 0; i < objetos.size(); i++) {
			if(objetos.get(i) instanceof Barril) {
				Barril b = (Barril) objetos.get(i);
				objetos.remove(b);
				System.out.println("Eelimino");
			}
		}
	}
	
	public void validar() {
		if(log.getEscenario().jugadores.get(0).getVida() == false && log.getEscenario().jugadores.get(1).getVida() == false) {
			log.setPantalla(5);
		}
	}

}
