package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    Socket miosocket;
    Scanner input;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti(String nomeServer, int portaServer) {
        System.out.println("è partito...");
        try {

            input = new Scanner(System.in);
            miosocket = new Socket(nomeServer, portaServer);

            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("errore");
            System.exit(1);
        }
        return miosocket;
    }
    public void comunica() {
        try {

            System.out.println("scrivi ciò che vuoi comunicare:" + '\n');
            stringaUtente = input.next();
            System.out.println("invio stringa ...");
            outVersoServer.writeBytes(stringaUtente + '\n');
            
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("risposta dal server " + '\n' + stringaRicevutaDalServer);
            System.out.println("chiudo connessione");
            miosocket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore");
            System.exit(1);
        }
    } 
}