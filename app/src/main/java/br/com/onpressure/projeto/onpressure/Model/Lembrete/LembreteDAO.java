package br.com.onpressure.projeto.onpressure.Model.Lembrete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.onpressure.projeto.onpressure.Componentes.DbGateway;
import br.com.onpressure.projeto.onpressure.Model.IMC.IMC;

public class LembreteDAO {

    private final String TABLE_LEMBRETE = "Lembretes";
    private DbGateway gw;

    public LembreteDAO(Context ctx) { gw = DbGateway.getInstance(ctx);}

    public boolean salvar(String medicamento, String posologia, String dataHora){
        ContentValues cv = new ContentValues();
        cv.put("Medicamento", medicamento);
        cv.put("Posologia", posologia);
        cv.put("DataHora", dataHora);

        return gw.getDatabase().insert(TABLE_LEMBRETE, null, cv) > 0;
    }

    public List<Lembrete> retornarTodos(){
        List<Lembrete> lembretes = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Lembretes ORDER BY ID DESC", null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String medicamento = cursor.getString(cursor.getColumnIndex("Medicamento"));
            String posologia = cursor.getString(cursor.getColumnIndex("Posologia"));
            String dataHora = cursor.getString(cursor.getColumnIndex("DataHora"));

            lembretes.add(new Lembrete(id, medicamento, posologia, dataHora));
        }

        cursor.close();
        return lembretes;
    }

    public boolean excluir(int id){
        return gw.getDatabase().delete(TABLE_LEMBRETE, "ID=?", new String[]{ id + "" }) > 0;
    }
}
