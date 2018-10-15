package Comunicar;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receptor extends Thread {
	
	Socket socket;
	Mensajero mensajero;
	
	public Receptor(Socket socket){
		this.socket = socket;
		
	}
	
	@Override
	public void run() {
		
		while(true){
		
			recibir();
			
		}
	}
	
	public void recibir(){
		try {
			
			InputStream entrada = socket.getInputStream();
		
			ObjectInputStream objetoIn = new ObjectInputStream(entrada);
		
			
			Mensaje mensaje = (Mensaje) objetoIn.readObject();
			
			
			mensajero.respuesta(mensaje);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public interface Mensajero {
		
		public void respuesta(Mensaje m); // Su respuesta
	}
	
	public void cambiarMensajero(Mensajero mensajero){
		
		this.mensajero = mensajero;
		
	}
	

}
