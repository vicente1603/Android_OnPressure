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

public class Alimentos_Evitar_Frag extends Fragment {

    private String[] lstAlimentos;
    private ListView listViewEvitar;
    private EditText eText;
    private ArrayList<String> pesquisa = new ArrayList<String>();

    public Alimentos_Evitar_Frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alimentos_evitar, container, false);

        listViewEvitar = view.findViewById(R.id.listViewEvitar);
        eText = view.findViewById(R.id.eText);


        lstAlimentos = new String[]{

                "Leite Semidesnatado(200ml) - 118 mg",
                "Bebida a base soja(200ml) - 110 mg",
                "Iogurte desnatado(200ml) - 120 mg",
                "Requeijão(Colher sopa) - 167,4 mg",
                "Queijo cottage(colher sopa) - 121,8 mg",
                "Cream Cheese(sopa) - 88,8 mg",
                "Pão francês - 324 mg",
                "Pão Forma Light (2 fatias) - 253 mg",
                "Pão sírio - 268 mg",
                "Pão Hambúrguer - 239,5 mg",
                "Biscoito água e sal (6 uni) - 341,6 mg",
                "Maionese Light (1 col) - 94,44 mg",
                "Milho enlatado (7 col) - 369,2 mg",
                "Purê batata (2 col) - 223,6 mg",
                "Grão Bico (1 col) - 87,48 mg",
                "Ovo (2 uni) - 131,4 mg",
                "Chester ou peito peru (10 fatias) - 96 mg",
                "Coxa / sobrecoxa frango - 95 mg",
                "Bife grelha / assado - 81 mg",
                "Bife fígado boi - 82 mg",
                "Filé peixe assado - 82 mg",
                "Escarola (10 folhas) - 38,25 mg",
                "Rúcula (15 ramos) - 24,3 mg",
                "Agrião (22 ramos) - 18,48 mg",
                "Repolho (6 col) - 20,16 mg",
                "Mostarda (6 folhas) - 15 mg",
                "Beterraba crua (2 col) - 4,2 mg",
                "Rabanete (3 uni) - 9,9 mg",
                "Tomate (4 fatias) - 4 mg",
                "Beterraba cozida (3 fatias) - 9,89 mg",
                "Cenoura cozida (1 col) - 2,8 mg",
                "Espinafre cozido (2 col) - 31,49 mg",
                "Melão (2 fatias) - 22 mg",
                "Uva - 1 mg",
                "Morango (10 uni) - 3,25 mg",
                "Abacate (2 col) - 3,15 mg",
                "Mamão papaia - 3,6 mg",
                "Mexerica - 3,2 mg",
                "Goiaba - 2,76 mg",
                "Margarina com sal (1 col.Sopa) - 151 mg",
                "Sopa em pacote (1 xic.Chá) - 710 mg",
                "Azeitona (5 unidades) - 505 mg",
                "Vegetais conserva (Meia xic) - 340 mg",
                "Sopa molho soja (1 col) - 780 mg",
                "Molho inglês (1 col) - 300 mg",
                "Queijo parmesão ralado (1 col) - 255 mg",
                "Hambúrguer boi - 416 mg",
                "Sopa molho tomate (2 col) - 260 mg",
                "Salsicha - 546 mg",
                "Queijo prato (2 fatias) - 190 mg",
                "Presunto (2 fatias) - 390 mg",
                "Mortadela (2 fatias) - 450 mg",
                "Almondega (3 uni) - 598 mg",
                "Bacon (100g) - 1630 mg",
                "Nuggets (5 uni) - 844 mg",
                "Cubo caldo - 2100 mg",
                "Calabresa (50g) - 598 mg",
        };

        ArrayAdapter<String> ListViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                lstAlimentos
        );

        listViewEvitar.setAdapter(ListViewAdapter);

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

                listViewEvitar.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, pesquisa));
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