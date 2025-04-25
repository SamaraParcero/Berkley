package mestre;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import interfaces.RelogioInterface;

public class Mestre {

    private List<RelogioInterface> clientes = new ArrayList<>();

    public void adicionarCliente(String host, int porta, String nome) {
        try {
            System.out.println("Tentando conectar ao cliente " + nome + " na porta " + porta);
            Registry registry = LocateRegistry.getRegistry(host, porta);
            RelogioInterface cliente = (RelogioInterface) registry.lookup(nome);
            clientes.add(cliente);
            System.out.println("Cliente adicionado: " + nome + " na porta " + porta);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar cliente " + nome + ": " + e.getMessage());
        }
    }

    public void sincronizarRelogios() {
        try {
            int mestreHora = LocalTime.now().getHour() * 3600 +
                             LocalTime.now().getMinute() * 60 +
                             LocalTime.now().getSecond();

            System.out.println("Hora do mestre: " + formatarHora(mestreHora));

            int soma = 0;
            for (RelogioInterface cliente : clientes) {
                int horaCliente = cliente.getHoraAtual();
                int diferenca = horaCliente - mestreHora;
                soma += diferenca;
                System.out.println("Hora cliente: " + formatarHora(horaCliente) + " | Diferença: " + diferenca);
            }

            int media = soma / (clientes.size() + 1);
            System.out.println("Ajuste calculado (média): " + media);

            for (RelogioInterface cliente : clientes) {
                cliente.ajustarHora(-media);
            }

            System.out.println("Nova hora estimada do mestre: " + formatarHora(mestreHora - media));
            System.out.println("Todos os relógios ajustados com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatarHora(int segundos) {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs = segundos % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segs);
    }
}
