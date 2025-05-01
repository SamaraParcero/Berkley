package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RelogioInterface extends Remote {
    long getHorario() throws RemoteException;
    void ajustarHorario(long diferenca) throws RemoteException;
}