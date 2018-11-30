package br.com.onpressure.projeto.onpressure.Model.PressaoArterial;

import java.io.Serializable;

public class PressaoArterial implements Serializable {

    private int id;
    private float pressaoSistolica;
    private float pressaoDiastolica;
    private float frequenciaCardiaca;
    private String data;

    public PressaoArterial(int id, float pressaoDiastolica, float pressaoSistolica, float frequenciaCardiaca, String data){
        this.id = id;
        this.pressaoDiastolica = pressaoDiastolica;
        this.pressaoSistolica = pressaoSistolica;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.data = data;
    }

    public int getId() {
        return this.id;
    }

    public float getPressaoSistolica() {
        return this.pressaoSistolica;
    }

    public float getPressaoDiastolica() {
        return this.pressaoDiastolica;
    }

    public float getFrequenciaCardiaca() {
        return this.frequenciaCardiaca;
    }

    public String getData() {
        return this.data;
    }
}
