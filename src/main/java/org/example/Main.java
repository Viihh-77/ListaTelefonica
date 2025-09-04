package org.example;

import org.example.dao.ContatoDAO;
import org.example.model.Contato;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        inicio();
    }

    public static void inicio() {
        boolean sair = false;

        System.out.println(" ");
        System.out.println("|                       ---LISTA TELEFONICA---                     |");
        System.out.println("| 1. Cadastrar contato: Nome, Telefone, Email, Observação.         |");
        System.out.println("| 2. Listar todos os contatos cadastrados.                         |");
        System.out.println("| 3. Buscar contato por nome.                                      |");
        System.out.println("| 4. Atualizar dados de um contato (telefone, email, observação).  |");
        System.out.println("| 5. Remover contato.                                              |");
        System.out.println("| 6. Sair do sistema.                                              |");
        System.out.println(" ");
        int opcao = SC.nextInt();
        SC.nextLine();

        switch (opcao) {

            case 1 -> {
                cadastrarContato();
                break;
            }

            case 2 -> {
                listarContatos();
                break;
            }

            case 3 -> {
                buscarContatoPorNome();
                break;
            }

            case 4 -> {
                atualizarContato();
                break;
            }

            case 5 -> {
                removerContato();
                break;
            }

            case 6 -> {
                sair = true;
                break;
            }
        }

        if (!sair) {
            inicio();
        }

    }

        public static void cadastrarContato() {
            System.out.println("-- CADASTRO DE CONTATO --");
            inserirDados(1,0);
    }

    public static void listarContatos() {
        System.out.println("  ---CONTATOS---   ");
        var dao = new ContatoDAO();

        try {
            List<Contato> contatos = dao.listarContatos();
            List<Integer> idContatos = exibirContatos(contatos);

        } catch (SQLException e) {
            System.out.println("Erro do cenexão com o banco de dados");
            e.printStackTrace();
        }
    }

    public static void buscarContatoPorNome() {
        System.out.println(" -- BUSCAR CONTATO POR NOME -- ");
        System.out.println("-- Digite o nome do contato que deseja busca: ");
        String nome = SC.nextLine();

        var dao = new ContatoDAO();

        try {
            List<Contato> contatos = dao.buscarContatoPorNome(nome);
            List<Integer> idContatos = exibirContatos(contatos);

        } catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados");
            e.printStackTrace();
        }
    }

    public static void atualizarContato(){
        System.out.println("-- ATUALIZAR CONTATO --");
        List<Integer> idContatos = new ArrayList<>();
        List<Contato> contatos = new ArrayList<>();
        var dao = new ContatoDAO();
        try{
            contatos = dao.listarContatos();
            idContatos = exibirContatos(contatos);
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("ID do contato para edição:");
        int id = SC.nextInt();
        SC.nextLine();

        if(idContatos.contains(id)){
            inserirDados(2, id);
        } else {
            System.out.println("Opção inválida!");
            atualizarContato();
        }
    }

    public static void removerContato() {
        System.out.println("  -- REMOVER CONTATO --  ");
        List<Integer> idContatos = new ArrayList<>();
        List<Contato> contatos = new ArrayList<>();
        var dao = new ContatoDAO();

        try {
            contatos = dao.listarContatos();
            idContatos = exibirContatos(contatos);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("ID do contato para remoção: ");
        int id = SC.nextInt();
        SC.nextLine();

        try {
            dao.removerContato(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> exibirContatos(List<Contato> contatos) {
        List<Integer> idContatos = new ArrayList<>();

        for (Contato contato : contatos) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + contato.getId());
            System.out.println("NOME: " + contato.getNome());
            System.out.println("TELEFONE: " + contato.getTelefone());
            System.out.println("EMAIL: " + contato.getEmail());
            System.out.println("OBSERVAÇÃO: " + contato.getObservacao());
            System.out.println("-----------------------------------");

            idContatos.add(contato.getId());
        }
        return idContatos;
    }

    public static void inserirDados(int opcao, int id) {
        var dao = new ContatoDAO();
        System.out.println("Digite o nome do contato: ");
        String nome = SC.nextLine();

        System.out.println("Digite o telefone do contato: ");
        String telefone = SC.nextLine();

        System.out.println("Digite o email do contato: ");
        String email = SC.nextLine();

        System.out.println("Digite a observação sobre o contato: ");
        String observacao = SC.nextLine();

        switch (opcao) {
            case 1 -> {
                try {
                    var contato = new Contato(nome,telefone,email,observacao);
                    dao.inserirContato(contato);
                    System.out.println("Contato inserido com sucesso!");
                } catch (SQLException e) {
                    System.out.println("Erro no bando de dados!");
                    e.printStackTrace();
                }
            }

            case 2 -> {
                try {
                    var contato = new Contato(id, nome, telefone, email, observacao);
                    dao.atualizarContato(contato);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}