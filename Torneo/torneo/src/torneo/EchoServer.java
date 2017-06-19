/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 *
 * @author giadamarconi
 */
public class EchoServer 
{
    private boolean connessione = false;
    private Socket socket;
    private int port;
    private ServerSocket serverSocket;
    private List<String> parametri = new ArrayList<>(); //Contiene i parametri passati dalla comunicazione attuale
    private String messaggio;
    PrintWriter out;
    
    
    public EchoServer(int port) 
    {
        this.port = port; 
    }
    
       public boolean startServer()  
       {
           try
           {
                serverSocket = new ServerSocket(port); // apro una porta TCP
                System.out.println("Socket aperto sulla porta: " + port); //resto in attesa di una connessione
                socket = serverSocket.accept(); 
                System.out.println("Connessione client accettata.");
                return this.connessione = true;
           } 
           catch(IOException e)
           {
               System.out.println("Non riesco a connettermi al socket.");
               return this.connessione = false;
           }    
       }
            
        public void chiudiSocket(Socket socket, Scanner in, PrintWriter out) 
        {
            try {
            System.out.println("I socket stanno per chiudersi.."); 
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
            this.connessione = false;
            }
            catch(Exception e)
            {
                System.out.println("Errore nella chiusura dei socket e degli stream.");
            }
        }
        
        public void chiudiSocket(){
            try {
                serverSocket.close();
                this.connessione = false;
            }
            catch(Exception e)
            {
                System.out.println("Errore nella chiusura dei socket e degli stream.");
            }
        }
        
        public void splitLine(String line)
        {
            String[] tmp = null; //Separare ogni argomento con uno spazio!!!!!!!
            tmp = line.split(" "); //Passo da un array normale ad una collection per comodità personale
            this.parametri.clear();
            for(int i=0; i<tmp.length; i++)
            {
                this.parametri.add(tmp[i]);
                System.out.println(this.parametri.get(i));
            }
        }
        
        public boolean controllaSocket() throws IOException
        {
            Scanner in = new Scanner(socket.getInputStream()); // apro gli stream di input e output per leggere
            out = new PrintWriter(socket.getOutputStream());
            while (true) 
            {      
                String line = in.nextLine(); //Dati che vengono forniti
                
                if (line.equals("Chiudi")) // se scrivono chiudi, avvio la procedura di chiusura della connessione
                {
                    chiudiSocket(socket, in, out);
                    return this.connessione;
                } 
                else 
                {
                    out.println("Ho ricevuto: " + line); //ricevo correttamente, mando allo splitting
                    splitLine(line);
                    out.flush();
                    return this.connessione = true;
                }
            }
        }

        public List<String> getParametri() 
        {
            return parametri;
        } 

        public void setMessaggio(String messaggio) 
        {
            this.messaggio = messaggio;
        }
        
        public void mandaMessaggio() throws IOException
        {
            out = new PrintWriter(socket.getOutputStream());    
            out.println(this.messaggio);
            out.flush();
        }
   
}