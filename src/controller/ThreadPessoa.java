package controller;

import java.util.concurrent.Semaphore;

public class ThreadPessoa extends Thread {

	private Semaphore semaforo;
	private int pessoa;

	public ThreadPessoa(Semaphore semaforo, int pessoa) {
		this.semaforo = semaforo;
		this.pessoa = pessoa;
	}

	@Override
	public void run() {
		caminhar();
		try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			abrirPorta();
			semaforo.release();
		}
		sair();
	}

	private void caminhar() {
		int distancia = 0;
		while (distancia < 200) {
			int caminhada = (int) ((Math.random() * 7) + 4);
			try {
				sleep(1000);
				distancia += caminhada;
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			System.out.println("Pessoa #" + pessoa + " andou " + distancia + "m.");
		}
		System.out.println("Pessoa #" + pessoa + " está esperando para sair.");
	}

	private void abrirPorta() {
		System.out.println("Pessoa #" + pessoa + " abriu a porta.");
	}

	private void sair() {
		System.out.println("Pessoa #" + pessoa + " saiu.");
	}
}
