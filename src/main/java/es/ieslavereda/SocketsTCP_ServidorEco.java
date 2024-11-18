package es.ieslavereda;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketsTCP_ServidorEco {


    public static void main(String[] args) {

        int numPuerto = 50000;

        try (ServerSocket socketServidor = new ServerSocket(numPuerto)) {

            System.out.printf("Creado socket de servidor en puerto %d. Esperando conexiones de clientes.\n", numPuerto);



            while (true) {    // Acepta una conexión de cliente tras otra

                Socket socketComunicacion = socketServidor.accept();

                Thread t = new Thread(new MyThread(socketComunicacion));
                t.start();

            }

        } catch (IOException ex) {

            System.out.println("Excepción de E/S");

            ex.printStackTrace();

            System.exit(1);

        }

    }



}