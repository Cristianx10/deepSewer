package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Cliente.Receptor;

public class Servidor {
	
	static String IP;
	static int PUERTO = 5000;
	static ArrayList<Receptor> receptores= new ArrayList<>();

	public static void main(String[] args) {

		try {
			ServerSocket servers = new ServerSocket(PUERTO);
			System.out.println("Enviado solisitud");
			Socket socket = servers.accept();
			System.out.println("Servidor enlazado");
			
			Receptor r = new Receptor(socket);
			r.start();
			
			receptores.add(r);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
