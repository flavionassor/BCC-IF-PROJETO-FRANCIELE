package socketsMultithreading;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import gui.TelaCliente;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/*
 * Demonstrando a comunica��o entre processos atrav�s de sockets multithreading
 * Classe Servidor, que aguarda conex�o de clientes. Quando uma conex�o � 
 * estabelecida, o cliente � encaminhado e tratado por uma thread (socket)
 */
public class ServidorMultithreading {
	private int porta;
	private ArrayList<PrintStream> clientes;
	public ArrayList<Semaphore> lista; 
        public ArrayList<String> id;
	public ServidorMultithreading(int porta) {
                this.id = new ArrayList<>();
                lista = new ArrayList<>();
		this.porta = porta;
		this.clientes = new ArrayList<PrintStream>();
                for(int i=0; i < 6 ;i++){         
                    Semaphore s = new Semaphore(1);
                    this.lista.add(s);
                }
	} 
	
	public void executa() throws IOException, InterruptedException {
		ServerSocket servidor = new ServerSocket(this.porta);
		System.out.println("Servidor aguardando requisi��es "+
				"na porta 8090...");
		
		while(true) {
			//esperando por uma conexao
			Socket cliente = servidor.accept();
			/*
			 * O pr�ximo comando s� ser� executado quando uma conex�o 
			 * for estabelecida
			 */
			System.out.println("Nova conex�o com o cliente " +
				cliente.getInetAddress().getHostAddress());
			
			//adicionando saida do cliente a lista de clientes
                        
			PrintStream ps = new PrintStream(
					cliente.getOutputStream());
			this.clientes.add(ps);
                        TelaCliente tcliente = new TelaCliente();
			
			//iniciando uma thread para o tratamento do Cliente
			TrataCliente tc = new TrataCliente(
					cliente.getInputStream(), this, cliente, lista);
			
                        tc.start();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		try {
			/*
			 * Criando uma instancia do Servidor e o executando
			 */
			new ServidorMultithreading(8090).executa();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
