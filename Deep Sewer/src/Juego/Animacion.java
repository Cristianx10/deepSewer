package Juego;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Animacion extends Thread{
	
	private PApplet app;
	private PVector pos;
	private boolean vivo;
	private ArrayList<PImage> img;
	private int contAnim;
	
	public Animacion(PApplet app, PVector pos, String name, int numero) {
		this.app = app;
		
		vivo = true;
		img = new ArrayList<>();
		this.pos = pos;
		for(int i = 0; i < numero; i++) {
			PImage ima = app.loadImage("Animacion/" + name + "/" + (i+1) + ".PNG");
			img.add(ima);
		}
		
	}
	
	public void pintar() {
		PImage imagen = img.get(contAnim);
		app.image(imagen, pos.x, pos.y);
	}
	
	@Override
	public void run() {
		
		while(vivo) {
			try {
				
				if(contAnim + 1 > img.size() -1) {
					
					contAnim = 0;
					
				}else {
					
					contAnim++;
					
				}
				
				sleep(200);
				
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
				
			}
		}
		
	}
	
	
	
	

}
