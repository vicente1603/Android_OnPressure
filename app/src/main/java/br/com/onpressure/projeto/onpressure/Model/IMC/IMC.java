package br.com.onpressure.projeto.onpressure.Model.IMC;

import java.io.Serializable;

public class IMC implements Serializable {

    private int id;
    private float peso;
    private float altura;
    private float resultadoImc;
    private String infoImc;
    private String data;

    public IMC (int id, float peso, float altura, float resultadoImc, String infoImc, String data){
        this.id = id;
        this.peso = peso;
        this.altura = altura;
        this.resultadoImc = resultadoImc;
        this.infoImc = infoImc;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public float getAltura() {
        return altura;
    }

    public float getPeso() {
        return peso;
    }

    public float getResultadoImc() {
        return resultadoImc;
    }

    public String getInfoImc() {
        return infoImc;
    }

    public String getData() {
        return data;
    }
}
