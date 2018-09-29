package Cliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Mensaje.Mensaje;

public class Cliente {

	static String IP = "127.0.0.1";
	static int PUERTO = 5000;
	public static Receptor receptor;

	public Cliente() {
		
		try {
			Socket socket = new Socket(IP, PUERTO);
			
			receptor= new Receptor(socket);
			receptor.start();
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
