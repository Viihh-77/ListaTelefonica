package org.example.model;

public class Contato {

    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String observacao;

    public Contato(int id, String nome, String telefone, String email, String observacao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.observacao = observacao;
    }

    public Contato(String nome, String telefone, String email, String observacao) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
