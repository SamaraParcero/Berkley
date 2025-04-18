package mestre;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Mestre {
	
	  private List<RelogioInterface> clientes = new ArrayList<>();

	    public void adicionarCliente(String endereco) throws Exception {
	        RelogioInterface cliente = (RelogioInterface) Naming.lookup(endereco);
	        clientes.add(cliente);
	        System.out.println("Cliente adicionado: " + endereco);
	    }

	    public void sincronizarRelogios() {
	        try {
	            int soma = 0;
	            int mestreHora = (int) (Math.random() * 1440); // Simula a hora do mestre
	            System.out.println("Hora do mestre: " + formatarHora(mestreHora));

	            List<Integer> diferencas = new ArrayList<>();
	            for (RelogioInterface cliente : clientes) {
	                int horaCliente = cliente.getHoraAtual();
	                int diferenca = horaCliente - mestreHora;
	                diferencas.add(diferenca);
	                soma += diferenca;
	                System.out.println("Hora cliente: " + formatarHora(horaCliente) + " | Diferença: " + diferenca);
	            }

	            int media = soma / (clientes.size() + 1); // +1 conta o mestre
	            System.out.println("Ajuste calculado (média): " + media);

	            // Ajusta clientes
	            for (RelogioInterface cliente : clientes) {
	                cliente.ajustarHora(-media);
	            }

	            System.out.println("Todos os relógios ajustados com sucesso.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private String formatarHora(int minutos) {
	        return String.format("%02d:%02d", minutos / 60, minutos % 60);
	    }

}
