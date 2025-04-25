package cliente2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

import interfaces.RelogioInterface;

public class RelogioCliente extends UnicastRemoteObject implements RelogioInterface {

    private int horaEmMinutos;

    public RelogioCliente() throws RemoteException {
        LocalTime agora = LocalTime.now();
        this.horaEmMinutos = agora.getHour() * 3600 + agora.getMinute() * 60 + agora.getSecond();
        System.out.println("Hora inicial do cliente: " + formatarHora(horaEmMinutos));
    }

    @Override
    public int getHoraAtual() throws RemoteException {
        return horaEmMinutos;
    }

    @Override
    public void ajustarHora(int minutos) throws RemoteException {
        this.horaEmMinutos += minutos;
        System.out.println("Hora ajustada Cliente: " + formatarHora(horaEmMinutos));
    }

    private String formatarHora(int segundos) {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs = segundos % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segs);
    }

}
