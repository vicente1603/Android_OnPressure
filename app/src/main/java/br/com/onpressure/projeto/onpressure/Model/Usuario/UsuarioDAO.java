package br.com.onpressure.projeto.onpressure.Model.Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.onpressure.projeto.onpressure.Componentes.DbGateway;

public class UsuarioDAO {

    private final String TABLE_USUARIOS = "Usuarios";
    private DbGateway gw;

    public UsuarioDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }


    public boolean salvar(String email, String nome, String dataNascimento, String ocupacao, String grauHipertensao,  String tipoSanguineo, String telefone, String sexo){
        ContentValues cv = new ContentValues();
        cv.put("Email", email);
        cv.put("Nome", nome);
        cv.put("DataNascimento", String.valueOf(dataNascimento));
        cv.put("Ocupacao", ocupacao);
        cv.put("TipoSanguineo", tipoSanguineo);
        cv.put("GrauHipertensao", grauHipertensao);
        cv.put("Telefone", telefone);
        cv.put("Sexo", sexo);

        return gw.getDatabase().insert(TABLE_USUARIOS, null, cv) > 0;
    }

    public List<Usuario> retornarTodos(){
        List<Usuario> usuarios = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Usuarios", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String email = cursor.getString(cursor.getColumnIndex("Email"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String dataNascimento = cursor.getString(cursor.getColumnIndex("DataNascimento"));
            String ocupacao = cursor.getString(cursor.getColumnIndex("Ocupacao"));
            String tipoSanguineo = cursor.getString(cursor.getColumnIndex("TipoSanguineo"));
            String grauHipertensao = cursor.getString(cursor.getColumnIndex("GrauHipertensao"));
            String sexo = cursor.getString(cursor.getColumnIndex("Sexo"));
            String telefone = cursor.getString(cursor.getColumnIndex("Telefone"));


            usuarios.add(new Usuario(id, email, nome, dataNascimento, ocupacao, tipoSanguineo, grauHipertensao, sexo, telefone));
        }
        cursor.close();
        return usuarios;
    }
}
