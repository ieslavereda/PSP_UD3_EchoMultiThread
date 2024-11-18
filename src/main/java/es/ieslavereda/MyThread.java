package es.ieslavereda;

import java.io.*;
import java.net.Socket;

public class MyThread implements Runnable {

    private final static String COD_TEXTO = "UTF-8";

    private Socket socketComunicacion;

    public MyThread(Socket socketComunicacion) {
        this.socketComunicacion=socketComunicacion;
    }

    @Override
    public void run() {

        System.out.printf("Cliente conectado desde %s:%d.\n", socketComunicacion.getInetAddress().getHostAddress(),                          socketComunicacion.getPort());

        try (InputStream isDeCliente = socketComunicacion.getInputStream();
             OutputStream osACliente = socketComunicacion.getOutputStream();
             InputStreamReader isrDeCliente = new InputStreamReader(isDeCliente, COD_TEXTO);
             BufferedReader brDeCliente = new BufferedReader(isrDeCliente);
             OutputStreamWriter oswACliente = new OutputStreamWriter(osACliente, COD_TEXTO);
             BufferedWriter bwACliente = new BufferedWriter(oswACliente)) {
            String lineaRecibida;
            while ((lineaRecibida = brDeCliente.readLine()) != null && lineaRecibida.length() > 0) {
                System.out.println("#" + lineaRecibida + "#");
                bwACliente.write("#" + lineaRecibida + "#");
                bwACliente.newLine();
                bwACliente.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
