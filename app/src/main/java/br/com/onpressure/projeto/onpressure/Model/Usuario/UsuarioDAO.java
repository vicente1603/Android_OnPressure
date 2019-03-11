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
            String nomeContato = cursor.getString(cursor.getColumnIndex("NomeContato"));
            String telefoneContato = cursor.getString(cursor.getColumnIndex("TelefoneContato"));
            String tratamento = cursor.getString(cursor.getColumnIndex("Tratamento"));


            usuarios.add(new Usuario(id, email, nome, dataNascimento, ocupacao, tipoSanguineo, grauHipertensao, sexo, telefone, nomeContato, telefoneContato, tratamento));
        }
        cursor.close();
        return usuarios;
    }

    public Usuario retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Usuarios ORDER BY ID DESC", null);
        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String email = cursor.getString(cursor.getColumnIndex("Email"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String dataNascimento = cursor.getString(cursor.getColumnIndex("DataNascimento"));
            String ocupacao = cursor.getString(cursor.getColumnIndex("Ocupacao"));
            String tipoSanguineo = cursor.getString(cursor.getColumnIndex("TipoSanguineo"));
            String grauHipertensao = cursor.getString(cursor.getColumnIndex("GrauHipertensao"));
            String sexo = cursor.getString(cursor.getColumnIndex("Sexo"));
            String telefone = cursor.getString(cursor.getColumnIndex("Telefone"));
            String nomeContato = cursor.getString(cursor.getColumnIndex("NomeContato"));
            String telefoneContato = cursor.getString(cursor.getColumnIndex("TelefoneContato"));
            String tratamento = cursor.getString(cursor.getColumnIndex("Tratamento"));
            cursor.close();
            return new Usuario(id, email, nome, dataNascimento, ocupacao, tipoSanguineo, grauHipertensao, sexo, telefone, nomeContato, telefoneContato, tratamento);
        }

        return null;
    }

    public boolean salvar(String email, String nome, String dataNascimento, String ocupacao, String grauHipertensao,
                          String tipoSanguineo, String telefone, String sexo, String nomeContato, String telefoneContato, String tratamento){
        return salvar(0, email, nome, dataNascimento, ocupacao, grauHipertensao, tipoSanguineo, telefone, sexo, nomeContato, telefoneContato, tratamento);
    }


    public boolean salvar(int id, String email, String nome, String dataNascimento, String ocupacao, String grauHipertensao,
                          String tipoSanguineo, String telefone, String sexo, String nomeContato, String telefoneContato, String tratamento){
        ContentValues cv = new ContentValues();
        cv.put("Email", email);
        cv.put("Nome", nome);
        cv.put("DataNascimento", String.valueOf(dataNascimento));
        cv.put("Ocupacao", ocupacao);
        cv.put("TipoSanguineo", tipoSanguineo);
        cv.put("GrauHipertensao", grauHipertensao);
        cv.put("Telefone", telefone);
        cv.put("Sexo", sexo);
        cv.put("NomeContato", nomeContato);
        cv.put("TelefoneContato", telefoneContato);
        cv.put("Tratamento", tratamento);

        if(id > 0)
            return gw.getDatabase().update(TABLE_USUARIOS, cv, "ID=?", new String[]{ id + "" }) > 0;
        else
            return gw.getDatabase().insert(TABLE_USUARIOS, null, cv) > 0;    }

}
