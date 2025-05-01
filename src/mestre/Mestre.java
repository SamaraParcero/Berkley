package mestre;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import interfaces.RelogioInterface;

public class Mestre extends UnicastRemoteObject implements RelogioInterface {
   
	private static final long serialVersionUID = 1L;
	private long horario;

	private long offsetMillis;
	private long startTime;

	public Mestre() throws RemoteException {
	    super();
	    this.startTime = System.currentTimeMillis();
	    this.offsetMillis = 0;
	}

    public void sincronizar() {
        try {
            RelogioInterface c1 = (RelogioInterface) Naming.lookup("rmi://localhost:1099/cliente1");
            RelogioInterface c2 = (RelogioInterface) Naming.lookup("rmi://localhost:1099/cliente2");

            long h1 = c1.getHorario();
            long h2 = c2.getHorario();

            System.out.println("Mestre: horario atual: " + formatarHorario(horario));

            long soma = h1 + h2 + horario;
            long media = soma / 3;

            long difMestre = media - horario;
            long difC1 = media - h1;
            long difC2 = media - h2;

            System.out.println("Ajustando horarios...");
            System.out.println("Mestre: horario anterior: " + formatarHorario(horario));
            horario += difMestre;
            System.out.println("Mestre: horario ajustado: " + formatarHorario(horario));

            c1.ajustarHorario(difC1);
            c2.ajustarHorario(difC2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getHorario() throws RemoteException {
        return System.currentTimeMillis() - startTime + offsetMillis + startTime;
    }

    @Override
    public void ajustarHorario(long diferenca) throws RemoteException {
        offsetMillis += diferenca;
    }
    
    private String formatarHorario(long millis) {
        return Instant.ofEpochMilli(millis)
                      .atZone(ZoneId.systemDefault())
                      .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
