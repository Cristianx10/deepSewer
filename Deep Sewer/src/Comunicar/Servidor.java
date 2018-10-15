package Comunicar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Juego.Logica;


public class Servidor extends Thread {

	ServerSocket server;
	int puerto;
	ArrayList<Receptor> receptores;
	Logica log;
	
	public Servidor(int puerto, Logica log) {
		this.puerto = puerto;
		receptores = new ArrayList<>();
		this.log = log;

	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(puerto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			System.out.println("esperando jugadores");
			try {
				Socket socket = server.accept();
				System.out.println("Acepto");

				Receptor receptor = new Receptor(socket);
				
				receptor.start();
			

				receptores.add(receptor);
				
				if (receptores.size() == 1) {

					log.getEscenario().getJugadores().get(0).startComunicacion(receptor);
					
				}else if(receptores.size() == 2) {
					log.getEscenario().getJugadores().get(1).startComunicacion(receptor);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public ArrayList<Receptor> getReceptor() {
		return receptores;
	}

}
