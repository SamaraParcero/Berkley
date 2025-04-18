package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RelogioInterface extends Remote {
    int getHoraAtual() throws RemoteException;
    void ajustarHora(int minutos) throws RemoteException;

}
