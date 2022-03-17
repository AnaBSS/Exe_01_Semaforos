package view;

import java.util.concurrent.Semaphore;
import controller.Transacao;

public class Principal6 {
	public static void main(String[] args) {
		int num_threads = 21;
		Semaphore fila_bd = new Semaphore(1);
		
		for(int i=1; i < num_threads; i++)
			new Transacao(i, fila_bd).start();
	}
}
