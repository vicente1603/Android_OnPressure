package br.com.onpressure.projeto.onpressure.Fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.onpressure.projeto.onpressure.R;

public class Alimentos_Consumir_Frag extends Fragment {

    private String[] lstAlimentos;
    private ListView listViewConsumir;
    private EditText eText;
    private ArrayList<String> pesquisa = new ArrayList<String>();

    public Alimentos_Consumir_Frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alimentos_consumir, container, false);

        listViewConsumir = view.findViewById(R.id.listViewConsumir);
        eText = view.findViewById(R.id.eText);


        lstAlimentos = new String[]{"Leite Desnatado (200ml) - 102 mg",
                "Iogurte Frutas (200ml) - 76 mg",
                "Queijo branco (fatia média) - 9,3 mg",
                "Ricota (fatia média) - 84,9 mg",
                "Torrada fibras (4uni) - 69,6 mg",
                "Biscoitos maisena/Maria (7 uni) - 105,6 mg",
                "Azeite Oliva (1 col) - 0,6 mg",
                "Margarina sem sal (1 col)  - 7,8 mg",
                "Arroz Branco (4 col) - 1,06 mg",
                "Arroz Integral (6 col) - 1,12 mg",
                "Batata inglesa cozida - 4,04 mg",
                "Batata doce (1 col) - 4,5 mg",
                "Feijão (1 col) - 0,96 mg",
                "Lentilha (2 col) - 0,48 mg",
                "Ervilha seca (2 col) - 3,6 mg",
                "Filé frango grelhado (1 uni) - 50 mg",
                "Carne moída magra (5 col) - 52 mg",
                "Espetinho de carne assada (2 uni) - 75,44 mg",
                "Acelga (9 col) - 0,9 mg",
                "Couve (1 col) - 8,4 mg",
                "Alface (11 folhas) - 12 mg",
                "Cenoura crua (1 col) - 1,2 mg",
                "Cebola - 0,0 mg",
                "Pepino cru - 2,6 mg",
                "Pimentão (8 fatias) - 1,1 2mg",
                "Abóbora (2 col.sopa) - 0,7 mg",
                "Berinjela cozida (2 col) - 0,6 mg",
                "Abobrinha (3 col) - 0,8 mg",
                "Brócolis cozido (4 col) - 1,2 mg",
                "Broto feijão (1 col) - 1,6 mg",
                "Chuchu cozido (2 col) - 1,14 mg",
                "Couve flor (3 ramos) - 1,38 mg",
                "Ameixa (2 uni) - 0,0 mg",
                "Banana prata - 0,75 mg",
                "Manga - 1,1 mg",
                "Abacaxi (1 fatia) - 1,45 mg",
                "Melancia (2 fatias)  - 2,2 mg",
                "Pêra - 1,2 mg",
                "Maçã - 1 mg",
                "Laranja - 1,53 mg",
                "Manteiga com sal (1 col sopa) - 124 mg",
                "Catchup (1 col) - 150 mg",
                "Mostarda (1 col) - 190 mg",
                "Sardinha (meia lata) - 201 mg",
                "Mussarela (2 fatias) - 164 mg"};

        ArrayAdapter<String> ListViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                lstAlimentos
        );

        listViewConsumir.setAdapter(ListViewAdapter);

        // Pesquisar();

        eText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }

            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                Pesquisar();

                listViewConsumir.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, pesquisa));
            }

            public void Pesquisar() {
                int textlength = eText.getText().length();
                pesquisa.clear();

                for (int i = 0; i < lstAlimentos.length; i++) {
                    if (textlength <= lstAlimentos[i].length()) {
                        if (eText.getText().toString().equalsIgnoreCase((String) lstAlimentos[i].subSequence(0, textlength))) {
                            pesquisa.add(lstAlimentos[i]);
                        }
                    }
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}