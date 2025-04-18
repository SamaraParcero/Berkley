package cliente2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

import interfaces.RelogioInterface;

public class RelogioCliente extends UnicastRemoteObject implements RelogioInterface {

    private int horaEmMinutos;

    public RelogioCliente() throws RemoteException {
        LocalTime agora = LocalTime.now();
        this.horaEmMinutos = agora.getHour() * 60 + agora.getMinute();
        System.out.println("Hora inicial do cliente: " + formatarHora(horaEmMinutos));
    }

    @Override
    public int getHoraAtual() throws RemoteException {
        return horaEmMinutos;
    }

    @Override
    public void ajustarHora(int minutos) throws RemoteException {
        this.horaEmMinutos += minutos;
        System.out.println("Hora ajustada: " + formatarHora(horaEmMinutos));
    }

    private String formatarHora(int minutos) {
        return String.format("%02d:%02d", minutos / 60, minutos % 60);
    }

}
