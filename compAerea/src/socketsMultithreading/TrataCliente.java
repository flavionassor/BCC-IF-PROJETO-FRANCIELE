package socketsMultithreading;

import gui.TelaReserva;
import java.io.InputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
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
	private ServidorMultithreading servidor;
	private long id;

	public TrataCliente(InputStream cliente, 
			ServidorMultithreading servidor, Socket servSocket) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.id = NEXT_ID;
		NEXT_ID++;
                this.servsocket = servSocket;
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
                        if(aux.equals("1")){
                            out.println("cliente 1!");
                        }else{
                            out.println("nao");
                        }
		}
		s.close();
            } catch (IOException ex) {
                Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
                
		
	}
}
