package br.com.onpressure.projeto.onpressure.Model.Usuario;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String email;
    private String nome;
    private String dataNascimento;
    private String ocupacao;
    private String tipoSanguineo;
    private String grauHipertensao;
    private String sexo;
    private String telefone;


    public Usuario(int id, String email, String nome, String dataNascimento, String ocupacao, String tipoSanguineo, String grauHipertensao, String sexo, String telefone){
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.ocupacao = ocupacao;
        this.tipoSanguineo = tipoSanguineo;
        this.grauHipertensao = grauHipertensao;
        this.sexo = sexo;
        this.telefone = telefone;

    }

    public int getId(){ return this.id; }
    public String getEmail(){ return this.email; }
    public String getNome(){ return this.nome; }
    public String getDataNascimento(){ return this.dataNascimento; }
    public String getOcupacao(){ return this.ocupacao; }
    public String getTipoSanguineo(){ return this.tipoSanguineo; }
    public String getGrauHipertensao(){ return this.grauHipertensao; }
    public String getTelefone(){ return this.telefone; }
    public String getSexo(){ return this.sexo; }

    @Override
    public boolean equals(Object o){
        return this.id == ((Usuario)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}