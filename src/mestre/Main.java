package mestre;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) {
		  try {
	            Mestre mestre = new Mestre();

	            mestre.adicionarCliente("rmi://localhost:1099/cliente1");
	            mestre.adicionarCliente("rmi://localhost:1100/cliente2");

	            Timer timer = new Timer();
	            timer.scheduleAtFixedRate(new TimerTask() {
	                @Override
	                public void run() {
	                    mestre.sincronizarRelogios();
	                }
	            }, 0, 15_000); 

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}

