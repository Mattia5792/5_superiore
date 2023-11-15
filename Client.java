import java.util.*;

import java.net.*;

import java.io.*;

public class Client {
    public static void main(String[] args) {
        Scanner input;
        String indirizzo;
        int timeout = 10000;
        try {
            input = new Scanner(System.in); // Crea l'oggetto scanner
            System.out.print("Inserisci l'indirizzo del server: ");
            indirizzo = input.nextLine(); // Legge l'indirizzo del server inserito in input

            Socket socket = new Socket(indirizzo, 80); // Crea il socket
            socket.setSoTimeout(timeout); // Imposta il timeout
            System.out.println("Connesso al server " + indirizzo + " sulla porta " + socket.getPort()); // Stampa porta
                                                                                                        // e indirizzo a
                                                                                                        // cui è
                                                                                                        // collegato

            // input e output stream
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // invio richiesta
            out.writeBytes("GET / HTTP/1.0\r\nHost: " + indirizzo + "\r\n\r\n");
            out.flush();

            // lettura risposta
            String ricevuto;
            while ((ricevuto = in.readLine()) != null) {
                System.out.println(ricevuto);
            }
            in.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}