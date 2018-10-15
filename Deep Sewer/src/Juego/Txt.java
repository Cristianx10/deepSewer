package Juego;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Txt {

	public Logica log;
	public static PApplet app;
	String txt[];
	ArrayList<Dato> objetosReft;
	private ArrayList<ImgOrden> img;

	public Txt(Logica log) {
		this.log = log;
		this.app = log.getPApplet();
		this.txt = app.loadStrings("objetos.txt");
		
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
			case "barril":
				log.getEscenario().getObjetos().add(new Barril(log, dato.pos));
				break;

			case "Escaleras":
				log.getEscenario().getEscalera().add(new Escalera(log, dato.pos, dato.nombre));
				break;
		
				
			case "Hueco":
				log.getEscenario().getObjetos().add(new Hueco(log, dato.pos));
				break;
			}
		}

	}

	public void CargaImg() {
		// Inicializador de las clases de lista
		this.img = new ArrayList<ImgOrden>();

		// 0----Resevado para la clase animacion
		img.add(ImgOrden.loadImgAnimation("Caballo", 7));
		img.add(ImgOrden.loadImgAnimation("Barril", 8));
		img.add(ImgOrden.loadImgAnimation("Personaje/1", 1));
		img.add(ImgOrden.loadImgAnimation("Personaje/2", 1));
		img.add(ImgOrden.loadImgAnimation("Personaje/3", 5));
		img.add(ImgOrden.loadImgAnimation("Personaje/4", 5));
		img.add(ImgOrden.loadImgAnimation("Personaje2/1", 1));
		img.add(ImgOrden.loadImgAnimation("Personaje2/2", 1));
		img.add(ImgOrden.loadImgAnimation("Personaje2/3", 4));
		img.add(ImgOrden.loadImgAnimation("Personaje2/4", 4));
		
		img.add(ImgOrden.loadImg("nb_escenario", "Imagenes/Escenario/nb_1.png"));
		img.add(ImgOrden.loadImg("escenario", "Imagenes/Escenario/1.png"));
		img.add(ImgOrden.loadImg("escalera_1", "Imagenes/Objetos/escalera_1.png"));
		img.add(ImgOrden.loadImg("tiempo", "Imagenes/Objetos/tiempo.png"));
		img.add(ImgOrden.loadImg("puntos1", "Imagenes/Objetos/puntos1.png"));
		img.add(ImgOrden.loadImg("puntos2", "Imagenes/Objetos/puntos2.png"));
		img.add(ImgOrden.loadImg("hueco", "Imagenes/Objetos/hueco.png"));
		
		
		img.add(ImgOrden.loadImg("gano1", "Imagenes/Escenario/gano1.png"));
		img.add(ImgOrden.loadImg("gano2", "Imagenes/Escenario/gano2.png"));
		img.add(ImgOrden.loadImg("inicio", "Imagenes/Escenario/inicio.png"));
		img.add(ImgOrden.loadImg("instrucciones", "Imagenes/Escenario/instrucciones.png"));
		
		
		img.add(ImgOrden.loadImg("puerta", "Imagenes/Escenario/puerta.png"));
		
	
		
	}
	
	public ArrayList<PImage> obtenerImg(String id) {
		
		ArrayList<PImage> fin = new ArrayList<>();
		for(ImgOrden i : img) {
			if(i.id.equals(id)) {
				if(i.img != null) {
					fin.addAll(i.getImg());
				}else {
					fin.add(i.ima);
				}
			}
		}
		if(fin.isEmpty()) {
			System.out.println("No se encontraron archivos");
		}
		return fin;
	}
	


	public void actualizar() {

		ArrayList<Arrastrable> obj = new ArrayList<Arrastrable>();
		// Añadir los ArrayList necesario para el funcionamiento

		obj = log.getEscenario().getObjetos();
		obj.addAll(log.getEscenario().getEscalera());
		// -----------------------------------------------------

		String[] data = new String[obj.size()];

		for (int i = 0; i < data.length; i++) {
			data[i] = obj.get(i).toString();
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

	public interface Arrastrable {

		public void pintar();

		public String toString();

		public void arrastrar();

		public boolean isSobreMouse();
		
		public boolean getVida();

	}
	
	public static class ImgOrden {
		private String id;
		private ArrayList<PImage> img;
		private PImage ima;
		private int width, height;
		
		public ImgOrden(String id, ArrayList<PImage> img){
			this.id = id;
			this.img = img;
			
			if (img.size() > 0) {
				width = img.get(0).width;
				height = img.get(0).height;
			}
		}
		
		public ImgOrden(String id, PImage ima){
			this.id = id;
			this.ima = ima;

				
				width = ima.width;
				height = ima.height;
				
			
		}
		
		static ImgOrden loadImg(String id, String ima) {
			ImgOrden im = null;
			PImage imagen = app.loadImage(ima);
			im = new ImgOrden(id, imagen);
			return im;
		}
		
		
		static ImgOrden loadImgAnimation(String tipo, int num) {
			ImgOrden im = null;
			ArrayList<PImage> ima = new ArrayList<PImage>();
			for (int i = 0; i < num; i++) {
				PImage img = app.loadImage("Animacion/" + tipo + "/" + (i + 1) + ".png");
				ima.add(img);
			}
			im = new ImgOrden(tipo, ima);
			return im;
		}
		
		
		public String getId() {
			return this.id;
		}
		
		public ArrayList<PImage> getImg(){
			return this.img;
		}
	
	}

}
