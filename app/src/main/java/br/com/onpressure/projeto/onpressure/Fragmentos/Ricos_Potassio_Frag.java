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

public class Ricos_Potassio_Frag extends Fragment {

    private String[] lstAlimentos;
    private ListView listViewRicosPotassio;
    private EditText eText;
    private ArrayList<String> pesquisa = new ArrayList<String>();

    public Ricos_Potassio_Frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ricos__potassio_, container, false);

        listViewRicosPotassio = view.findViewById(R.id.listViewRicosPotassio);
        eText = view.findViewById(R.id.eText);



        lstAlimentos = new String[] {"Damasco",
                "Abacate",
                "Melão",
                "Leite desnatado",
                "Iogurte desnatado",
                "Folhas verdes",
                "Peixes(Linguado e Atum)",
                "Feijão",
                "Melaço",
                "Cogumelos",
                "Laranja",
                "Ervilha",
                "Batata",
                "Ameixa",
                "Espinafre",
                "Tomate",
                "Uva passa"};

        ArrayAdapter<String> ListViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                lstAlimentos
        );

        listViewRicosPotassio.setAdapter(ListViewAdapter);

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

                listViewRicosPotassio.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, pesquisa));
            }

            public void Pesquisar() {
                int textlength = eText.getText().length();
                pesquisa.clear();

                for (int i = 0; i < lstAlimentos.length; i++ ) {
                    if (textlength <= lstAlimentos[i].length()) {
                        if (eText.getText().toString().equalsIgnoreCase((String)lstAlimentos[i].subSequence(0, textlength))) {
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