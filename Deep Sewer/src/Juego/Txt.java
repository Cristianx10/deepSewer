package Juego;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Txt {

	public Logica log;
	public PApplet app;
	String txt[];
	ArrayList<Dato> objetosReft;

	public Txt(Logica log) {
		this.log = log;
		this.app = app;
		this.txt = app.loadStrings("Objetos");

		objetosReft = new ArrayList<Dato>();

		for (int i = 0; i < txt.length; i++) {
			String[] linea = app.splitTokens(txt[i], ",");
			if (linea.length == 3) {
				String name = linea[0];
				int x = Integer.parseInt(linea[1]);
				int y = Integer.parseInt(linea[2]);

				objetosReft.add(new Dato(name, new PVector(x, y)));
			}

		}

	}

	public void crearObjetos() {
		
		for (Dato dato : objetosReft) {
			switch (dato.nombre) {
			case "":

				break;
			}
		}
		
	}

	public void actualizar() {

		ArrayList<Objeto> obj = new ArrayList<Objeto>();
		// Añadir los ArrayList necesario para el funcionamiento

		// -----------------------------------------------------

		String[] data = new String[obj.size()];

		for (int i = 0; i < data.length; i++) {
			data[i] = data.toString();
		}

		app.saveStrings("objetos.txt", data);

	}

	private class Dato {

		String nombre;
		PVector pos;

		public Dato(String name, PVector pos) {
			this.nombre = name;
			this.pos = pos;
		}

	}

}
