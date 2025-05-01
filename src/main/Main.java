package main;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import clientes.Cliente1;
import clientes.Cliente2;
import mestre.Mestre;

public class Main {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); 

           
            Cliente1 cliente1 = new Cliente1();
            Cliente2 cliente2 = new Cliente2();
            Naming.rebind("rmi://localhost:1099/cliente1", cliente1);
            Naming.rebind("rmi://localhost:1099/cliente2", cliente2);

           
            Mestre mestre = new Mestre();
            Naming.rebind("rmi://localhost:1099/mestre", mestre);

           
            while (true) {
                Thread.sleep((long) (Math.random() * 10000 + 5000)); 
                System.out.println("\n[Sincronizacao iniciada]");
                mestre.sincronizar();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}