package controller;

import java.util.concurrent.Semaphore;

public class Transacao extends Thread {
	private int id;
	private Semaphore semaforo;

	public Transacao(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	public void run() {
		switch (id % 3) {
		case 1:
			// ((Math.random() * 801) + 200)
			executarTransacao(1000, 200, 801);break;
		case 2:
			// ((Math.random() * 1001) + 500)
			executarTransacao(1500, 500, 1001);break;
		case 0:
			// ((Math.random() * 1001) + 1000)
			executarTransacao(1500, 1000, 1001);break;
		}
	}
	private void executarTransacao(int temp_bd, int temp_min, int temp_max) {
		for (int i = 0; i < 2; i++) {
			// Tempo de Transação. 1000 milissegundos = 1 segundo
			int tempo_calculo = (int) ((Math.random() * temp_max) + temp_min);
			try {
				// Cálculos
				System.out.println("Thread "+id+ ": calculando...");
				sleep(tempo_calculo);
				// Transação para BD
				semaforo.acquire();
				System.out.println("Thread "+id+": Transação BD");
				sleep(temp_bd);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
	}
}
