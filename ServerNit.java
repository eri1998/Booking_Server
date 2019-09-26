package com.comtrade.nit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.comtrade.konstante.Konstante;

public class ServerNit extends Thread {

	public void run() {
		pokreniServer();
	}

	private void pokreniServer() {

		try {
			ServerSocket serverSocket = new ServerSocket(Konstante.PORT.getPort());
			while (true) {
				Socket socket = serverSocket.accept();
				KlijentNit klijentNit = new KlijentNit();
				klijentNit.setSocket(socket);
				klijentNit.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
