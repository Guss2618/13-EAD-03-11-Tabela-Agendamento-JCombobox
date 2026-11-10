package View;

import Model.Agendamento;
import Model.Cliente;
import Model.Servico;
import Model.Usuario;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) {
        String nome = "Rodrigo";
        System.out.println(nome);

        Servico servico = new Servico(1, "barba", 30);
        System.out.println(servico.getDescricao());
        System.out.println(servico.getValor());

        Cliente cliente = new Cliente(1, "Jorge", 'M', "95445866", "Rua tesla 95");
        System.out.println(cliente.getNome());

        Usuario usuario = new Usuario(1, "barbeiro", "senha");
        System.out.println(usuario.getNome());
        System.out.println(usuario);

        // Pessoa pessoa = new Pessoa(1, "Joao"); // ERRO: Pessoa é abstract e não pode ser instanciada

        // Coloque um breakpoint nesta linha para inspecionar a data convertida
        Agendamento agendamento = new Agendamento(1, cliente, servico, 30, "25/03/2024 11:20");
        System.out.println(agendamento.getCliente().getNome());
        System.out.println("Data convertida: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(agendamento.getData()));

        Cliente completo = new Cliente(2, "Alan Figueiredo", 'M', "30/01/1995", "30212126", "alan@email.com", "5142487", "Rua jaboti 35", "93300012");
        System.out.println("Nome herdado: " + completo.getNome());
        System.out.println("Nascimento: " + new SimpleDateFormat("dd/MM/yyyy").format(completo.getDataNascimento()));
    }
}