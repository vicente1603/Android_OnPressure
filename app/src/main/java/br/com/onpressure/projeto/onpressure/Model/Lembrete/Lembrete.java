package br.com.onpressure.projeto.onpressure.Model.Lembrete;

import java.io.Serializable;

public class Lembrete implements Serializable{

    private int id;
    private String medicamento;
    private String posologia;
    private String dataHora;

    public Lembrete (int id, String medicamento, String posologia, String dataHora){
        this.id = id;
        this.medicamento = medicamento;
        this.posologia = posologia;
        this.dataHora = dataHora;
    }

    public int getId(){return id;}
    public String getMedicamento(){return medicamento;}
    public String getPosologia(){return posologia;}
    public String getDataHora(){return dataHora;}
}
