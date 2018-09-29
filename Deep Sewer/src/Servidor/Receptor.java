package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Mensaje.Mensaje;

public class Receptor extends Thread {

	private Socket socket;
	

	public Receptor(Socket s) {
		this.socket = s;
	}

	public void run() {

		while(true) {
			
			try {
				
				while(true) {
					
					ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
					
					Object respuesta = entrada.readObject();
					
					if(respuesta instanceof Mensaje) {
						Mensaje m = (Mensaje) respuesta; 
						System.out.println(m.name);
					}
					
					
					
				}
				
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}
	
	public void enviar(Mensaje m) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				ObjectOutputStream salida;
				
				try {
					//Crea el paquete en el que se envia el objeto
					salida = new ObjectOutputStream(socket.getOutputStream());
					
					//Envia un archivo o una clase o un objeto
					salida.writeObject(m);
					
					//Limpia salida de datos
					salida.flush();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		}).start();
		
		
		
	}

}
