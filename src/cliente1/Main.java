package cliente1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.RelogioInterface;

public class Main {
    public static void main(String[] args) {
        try {
            RelogioInterface cliente = new RelogioCliente();
            Registry registry = LocateRegistry.createRegistry(1101);
            registry.rebind("cliente1", cliente);
            System.out.println("Cliente pronto.");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
