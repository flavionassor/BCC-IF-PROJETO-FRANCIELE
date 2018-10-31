package socketsMultithreading;

import gui.TelaCliente;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * Demonstrando a comunica��o entre processos atraves de sockets multithreadings
 * Classe Cliente, que se conecta a um Servidor e envia mensagens a ele
 */
public class Cliente {
	private String host;
	private int porta;
	
	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}

	public void executa() throws UnknownHostException, IOException {
		/*
		 * Tentando se conectar ao servidor, que est� rodando
		 * no localhost, porta 8090
		 */
		Socket cliente = new Socket(this.host, this.porta);
		System.out.println("Cliente conectado!");		
		/*scanner para leitura da mensagem digitada
			pelo usuario*/
		Scanner teclado = new Scanner(System.in);
		//buffer de saida
		PrintStream saida = new PrintStream(
				cliente.getOutputStream());
		TelaCliente tc = new TelaCliente();
                tc.setVisible(true);
		while(teclado.hasNextLine()) {
			                 
			saida.println(teclado.nextLine());
		}
		
		//fechando as conexoes
		saida.close();
		teclado.close();
		cliente.close();
	}
	
	public static void main(String[] args) {
		try {
			/*
			 * Criando uma instancia de Cliente e tentando executa-lo
			 */
			new Cliente("127.0.0.1", 8090).executa();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
