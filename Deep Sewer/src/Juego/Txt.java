package Juego;

import processing.core.PApplet;

public class Txt {
	
	public Logica log;
	public PApplet app;
	String txt[];
	
	
	public Txt(Logica log) {
		this.log = log;
		this.app = app;
		this.txt = app.loadStrings("Objetos");
		
	}
	
	
	
	
	private class Dato {
		
		String nombre;
		String pos;
		
		
		public Dato() {
			
		}
		
	}
	

}


