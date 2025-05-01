package clientes;

import java.rmi.RemoteException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import interfaces.RelogioInterface;

public class Cliente1 extends UnicastRemoteObject implements RelogioInterface {
  
	private static final long serialVersionUID = 1L;
	private long horario; 

	private long offsetMillis;
	private long startTime;

	public Cliente1() throws RemoteException {
	    super();
	    this.startTime = System.currentTimeMillis();
	    this.offsetMillis = new Random().nextInt(10000) - 5000;
	}

	@Override
	public long getHorario() throws RemoteException {
	    return System.currentTimeMillis() - startTime + offsetMillis + startTime;
	}

	@Override
	public void ajustarHorario(long diferenca) throws RemoteException {
	    long atual = getHorario();
	    System.out.println("Cliente1: horario anterior: " + formatarHorario(atual));
	    offsetMillis += diferenca;
	    System.out.println("Cliente1: horario ajustado: " + formatarHorario(getHorario()));
	}
    
    private String formatarHorario(long millis) {
        return Instant.ofEpochMilli(millis)
                      .atZone(ZoneId.systemDefault())
                      .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
