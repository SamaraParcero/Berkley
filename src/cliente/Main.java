package cliente;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import interfaces.RelogioInterface;

public class Main {

	public static void main(String[] args) {
		  try {
	            RelogioInterface cliente = new RelogioCliente();
	            String nome = "rmi://localhost:1099/cliente1"; 

	            LocateRegistry.createRegistry(1099);
	            Naming.rebind(nome, cliente);

	            System.out.println("Cliente pronto. Esperando requisições do mestre.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


