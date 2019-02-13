package br.com.onpressure.projeto.onpressure.Fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.onpressure.projeto.onpressure.R;

public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        String[] menuItens =   {"Alimento 1",
                                "Alimento 2",
                                "Alimento 3",
                                "Alimento 4",
                                "Alimento 5",
                                "Alimento 6",
                                "Alimento 7",
                                "Alimento 8",
                                "Alimento 9"};

        ListView listView = view.findViewById(R.id.listViewEvitar);

        ArrayAdapter<String> ListViewAdapter = new ArrayAdapter<String>(
            getActivity(),
            android.R.layout.simple_list_item_1,
            menuItens
        );

        listView.setAdapter(ListViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}