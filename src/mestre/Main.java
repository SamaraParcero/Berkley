package mestre;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) {
		  try {
	            Mestre mestre = new Mestre();

	            mestre.adicionarCliente("rmi://26.161.56.125:1099/cliente1");
	            mestre.adicionarCliente("rmi://26.161.56.125:1099/cliente2");

	            Timer timer = new Timer();
	            timer.scheduleAtFixedRate(new TimerTask() {
	                @Override
	                public void run() {
	                    mestre.sincronizarRelogios();
	                }
	            }, 0, 60_000); // a cada 1 minuto

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}

