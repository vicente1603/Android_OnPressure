package br.com.onpressure.projeto.onpressure.Model.IMC;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

import br.com.onpressure.projeto.onpressure.Componentes.DbGateway;

public class IMCDAO {

    private final String TABLE_IMC = "IMC";
    private DbGateway gw;

    public IMCDAO(Context ctx) { gw = DbGateway.getInstance(ctx);}

    public boolean salvar(float peso, float altura, float resultadoImc, String infoImc, String data){
        ContentValues cv = new ContentValues();
        cv.put("Peso", peso);
        cv.put("Altura", altura);
        cv.put("ResultadoImc", resultadoImc);
        cv.put("InfoImc", infoImc);
        cv.put("Data", data);

        return gw.getDatabase().insert(TABLE_IMC, null, cv) > 0;
    }

    public List<IMC> retornarTodos(){
        List<IMC> imcs = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM IMC ORDER BY ID DESC", null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            float peso = cursor.getFloat(cursor.getColumnIndex("Peso"));
            float altura = cursor.getFloat(cursor.getColumnIndex("Altura"));
            float resultadoImc = cursor.getFloat(cursor.getColumnIndex("ResultadoImc"));
            String infoImc = cursor.getString(cursor.getColumnIndex("InfoImc"));
            String data = cursor.getString(cursor.getColumnIndex("Data"));

            imcs.add(new IMC(id, peso, altura, resultadoImc, infoImc, data));
        }

        cursor.close();
        return imcs;
    }

    public boolean excluir(int id){
        return gw.getDatabase().delete(TABLE_IMC, "ID=?", new String[]{ id + "" }) > 0;
    }

}
