package socketsMultithreading;

import gui.TelaReserva;
import java.io.InputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Demonstrando a troca de mensagens entre processos atrav�s de sockets 
 * multithreading
 * Classe que implementa uma Thread para tratamento de um Cliente
 */
public class TrataCliente extends Thread{
	private static long NEXT_ID = 1;
	private Socket servsocket;
	private InputStream cliente;
        private ArrayList<Semaphore> lista;
	private ServidorMultithreading servidor;
	private long id;
        private ArrayList<String> contID;

	public TrataCliente(InputStream cliente, 
			ServidorMultithreading servidor, Socket servSocket, ArrayList<Semaphore> lista) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.id = NEXT_ID;
		NEXT_ID++;
                this.servsocket = servSocket;
                this.lista = lista;
                contID = new ArrayList<>();
                for(int i = 0; i<=id; i++){
                    contID.add("0");
                }
	}
        
	@Override
	public void run() {
            String aux;
		//lendo as informacoes que o cliente enviar                
            try {
                Scanner s = new Scanner(this.cliente);
                OutputStream output = servsocket.getOutputStream();
                PrintStream out = new PrintStream(output);
                while(s.hasNextLine()) {
			//imprimindo as mensagens do cliente
                        aux = s.nextLine();
			System.out.println("Cliente " + this.id + ": Tentando acessar avião: " + aux);
                    try {
                        if(contID.get((int) this.id).equals("1")){
                            out.println("1");
                            lista.get(Integer.parseInt(aux)).release();
                            contID.set((int) this.id, "0");
                        }else{
                            lista.get(Integer.parseInt(aux)).acquire();
                            contID.set((int) this.id, Integer.toString(Integer.parseInt(contID.get((int) this.id)) + 1));
                            out.println("1");
                        }
                        
                        
                        
//                        if(aux.equals("1")){
//                            out.println("sim");
//                        }else{
//                            out.println("nao");
//                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
		}
		s.close();
            } catch (IOException ex) {
                Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
	}
}
