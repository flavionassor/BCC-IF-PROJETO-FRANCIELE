package socketsMultithreading;

import java.io.InputStream;
import java.util.Scanner;

/*
 * Demonstrando a troca de mensagens entre processos atrav�s de sockets 
 * multithreading
 * Classe que implementa uma Thread para tratamento de um Cliente
 */
public class TrataCliente extends Thread{
	private static long NEXT_ID = 1;
	
	private InputStream cliente;
	private ServidorMultithreading servidor;
	private long id;

	public TrataCliente(InputStream cliente, 
			ServidorMultithreading servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.id = NEXT_ID;
		NEXT_ID++;
	}

	@Override
	public void run() {
		//lendo as informacoes que o cliente enviar
		Scanner s = new Scanner(this.cliente);
		while(s.hasNextLine()) {
			//imprimindo as mensagens do cliente
			System.out.println("Cliente " + this.id + ": " + s.nextLine());
		}
		s.close();
	}
}