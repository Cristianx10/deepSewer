package Juego;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Juego.Txt.Arrastrable;
import Comunicar.Servidor;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Logica {

	private PApplet app;
	private Txt txt;
	private Servidor sevidor;
	private Camara camara;
	private Escenario escena;
	public int pantalla;
	public boolean start;
	String ipView;

	// Comunicacion---------------
	private Servidor SERVER;

	private PImage inicio, instrucciones, gano1, gano2;

	public Logica(PApplet app) {
		this.app = app;
		this.txt = new Txt(this);
		txt.CargaImg();

		escena = new Escenario(this);

		this.camara = new Camara(this);
		this.camara.setImageBase(txt.obtenerImg("escenario").get(0));

		escena.crearJugador(78, 2312, 1);
		escena.crearJugador(1000, 2312, 2);

		txt.crearObjetos();

		inicio = txt.obtenerImg("inicio").get(0);
		instrucciones = txt.obtenerImg("instrucciones").get(0);
		gano1 = txt.obtenerImg("gano1").get(0);
		gano2 = txt.obtenerImg("gano2").get(0);

		pantalla = 0;

		try {
			InetAddress ip = InetAddress.getLocalHost();
			ipView = ip.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SERVER = new Servidor(5000, this);
		SERVER.start();
	}

	public void pintatImagen(PImage img) {
		app.imageMode(PConstants.CORNER);
		app.image(img, 0, 0);
	}

	public void pintar() {

		switch (pantalla) {
		case 0:

			pintatImagen(inicio);

			app.fill(255);
			app.textSize(20);
			app.text("Conectate a la siguiente ip:", 20, 30);
			app.textSize(40);
			app.text(ipView, 20, 70);

			break;

		case 1:

			pintatImagen(instrucciones);

			break;

		case 2:

			start = true;
			camara.inicio();

			escena.pintar();
			escena.agua();

			camara.fin();

			escena.interfaz();

			break;

		case 3:
			
			
			pintatImagen(gano1);
			
		
			app.fill(255);
			app.textSize(20);
			app.textSize(40);
			app.text(escena.getJugadores().get(0).getNombre(), 790, 560);
			app.text(escena.getJugadores().get(0).getPuntuacion(), 790, 610);
			
		
			break;

		case 4:
			pintatImagen(gano2);
			
			app.fill(255);
			app.textSize(20);
			app.textSize(40);
			app.text(escena.getJugadores().get(1).getNombre(), 790, 560);
			app.text(escena.getJugadores().get(1).getPuntuacion(), 790, 610);
			break;

		case 5:
			app.background(0);
			app.textSize(80);
			app.fill(255);
			app.textAlign(PConstants.CENTER, PConstants.CENTER);
			app.text("Fin del juego", app.width / 2, app.height / 2);
			break;

		}

	}

	public void keyPressed() {

	}

	// Getter an setters----------------------------------------------------------

	public PApplet getPApplet() {
		return this.app;
	}

	public Txt getTxt() {
		return txt;
	}

	public Escenario getEscenario() {
		return escena;
	}

	public Camara getCamara() {
		return camara;
	}

	public void setPantalla(int i) {
		pantalla = i;
	}

}
