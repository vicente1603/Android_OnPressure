package br.com.onpressure.projeto.onpressure.Model.PressaoArterial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.onpressure.projeto.onpressure.Componentes.DbGateway;
import br.com.onpressure.projeto.onpressure.Model.Usuario.UsuarioDAO;

public class PressaoArterialDAO {

    private final String TABLE_PRESSAO_ARTERIAL = "PressoesArterial";
    private DbGateway gw;

    public PressaoArterialDAO(Context ctx) {
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvar(float pressaoDiastolica, float pressaoSistolica, float frequenciaCardiaca, String data){
        ContentValues cv = new ContentValues();
        cv.put("PressaoDiastolica", pressaoDiastolica);
        cv.put("PressaoSistolica", pressaoSistolica);
        cv.put("FrequenciaCardiaca", frequenciaCardiaca);
        cv.put("Data", String.valueOf(data));

        return gw.getDatabase().insert(TABLE_PRESSAO_ARTERIAL, null, cv) > 0;
    }

    public List<PressaoArterial> retornarTodas(){
        List<PressaoArterial> pressoesArterial = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM PressoesArterial ORDER BY ID DESC", null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            float pressaoDiastolica = cursor.getFloat(cursor.getColumnIndex("PressaoDiastolica"));
            float pressaoSistolica = cursor.getFloat(cursor.getColumnIndex("PressaoSistolica"));
            float frequenciaCardiaca = cursor.getFloat(cursor.getColumnIndex("FrequenciaCardiaca"));
            String data = cursor.getString(cursor.getColumnIndex("Data"));

            pressoesArterial.add(new PressaoArterial(id, pressaoDiastolica, pressaoSistolica, frequenciaCardiaca, data));
        }

        cursor.close();
        return pressoesArterial;
    }

    public PressaoArterial retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM PressoesArterial ORDER BY ID DESC", null);
        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            float pressaoDiastolica = cursor.getFloat(cursor.getColumnIndex("PressaoDiastolica"));
            float pressaoSistolica = cursor.getFloat(cursor.getColumnIndex("PressaoSistolica"));
            float frequenciaCardiaca = cursor.getFloat(cursor.getColumnIndex("FrequenciaCardiaca"));
            String data = cursor.getString(cursor.getColumnIndex("Data"));
            cursor.close();
            return new PressaoArterial(id, pressaoDiastolica, pressaoSistolica, frequenciaCardiaca, data);
        }

        return null;
    }
}
