package br.com.onpressure.projeto.onpressure.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> sintoma_1 = new ArrayList<String>();
        sintoma_1.add("Em geral, a pressão arterial elevada não tem sintomas. " +
                "Ao longo do tempo, se não for tratada, poderá causar problemas de saúde, " +
                "como doenças cardíacas e acidente vascular cerebral.");

        List<String> sintoma_2 = new ArrayList<String>();
        sintoma_2.add("Dores locais: peito");
        sintoma_2.add("Tosse: com catarro");
        sintoma_2.add("No corpo: fadiga, incapacidade de praticar atividade física, perda de apetite ou tontura.");
        sintoma_2.add("No sistema respiratório: falta de ar ao deitar-se, falta de ar durante a atividade física, falta de ar durante a noite ou respiração rápida.");
        sintoma_2.add("No aparelho gastrointestinal: inchaço ou retenção de líquido");
        sintoma_2.add("Também é comum: ganho de peso, micção excessiva à noite, palpitações, pernas inchadas ou pés inchados.");

        List<String> sintoma_3 = new ArrayList<String>();
        sintoma_3.add("A doença arterial coronariana pode não apresentar sintomas, como também pode causar dores no peito e ataque cardíaco.");
        sintoma_3.add("Dores locais: peito");
        sintoma_3.add("Também é comum: ataque cardíaco, falta de ar ou suor.");

        List<String> sintoma_4 = new ArrayList<String>();
        sintoma_4.add("Os principais sintomas são a perda de consciência o desmaio.");
        sintoma_4.add("Dores locais: peito");
        sintoma_4.add("No corpo: desmaio ou baixos níveis de oxigênio no corpo.");
        sintoma_4.add("Também é comum: falta de ar");

        List<String> sintoma_5 = new ArrayList<String>();
        sintoma_5.add("Pode não haver sintomas. Quando ocorrem, os sintomas incluem uma vibração ou dor no peito, desmaios ou tontura.");
        sintoma_5.add("Dores locais: peito.");
        sintoma_5.add("No corpo: desmaio ou tontura.");
        sintoma_5.add("No coração: palpitações ou ritmo cardíaco lento.");
        sintoma_5.add("Também é comum: falta de ar.");

        List<String> sintoma_6 = new ArrayList<String>();
        sintoma_6.add("Os sintomas podem incluir dor na perna, especialmente ao caminhar.");
        sintoma_6.add("Dores locais: nádegas.");
        sintoma_6.add("Dores circunstanciais: na perna durante atividade física ou na perna, mas melhora durante o repouso.");
        sintoma_6.add("SintNa pele: afinamento da pele nas pernas, pele fria, perda de pelos nas pernas ou úlcerasomas.");

        List<String> sintoma_7 = new ArrayList<String>();
        sintoma_7.add("Os sintomas de acidente vascular cerebral incluem dificuldade para andar, falar e compreender, bem como paralisia ou dormência da face, do braço ou da perna.");
        sintoma_7.add("Nos músculos: dificuldade para caminhar, fraqueza de um lado do corpo, fraqueza muscular, incapacidade de coordenar movimentos musculares, instabilidade, músculos rígidos, paralisia com músculos fracos, problemas de coordenação, paralisia de um lado do corpo ou reflexos hiperativos.");
        sintoma_7.add("Na visão: perda temporária da visão em um olho, súbita perda da visão, visão dupla ou visão embaçada.");
        sintoma_7.add("Na fala: dificuldade de fala, fala arrastada ou perda da fala.");
        sintoma_7.add("No corpo: tontura ou vertigem.");
        sintoma_7.add("Sensorial: formigamento ou redução na sensação de tato.");
        sintoma_7.add("Na cognição: confusão mental ou incapacidade de falar ou entender o próprio idioma.");
        sintoma_7.add("No rosto: dormência ou fraqueza muscular.");
        sintoma_7.add("Também é comum: afasia de wernicke, dificuldade em engolir, dor de cabeça, fraqueza de um membro ou movimento rápido involuntário dos olhos.");


        expandableListDetail.put("Pressão alta", sintoma_1);
        expandableListDetail.put("Insuficiência cardíaca", sintoma_2);
        expandableListDetail.put("Doença arterial coronariana", sintoma_3);
        expandableListDetail.put("Parada cardíaca", sintoma_4);
        expandableListDetail.put("Arritmia cardíaca", sintoma_5);
        expandableListDetail.put("Doença arterial periférica", sintoma_6);
        expandableListDetail.put("Derrame cerebral", sintoma_7);
        return expandableListDetail;
    }
}
