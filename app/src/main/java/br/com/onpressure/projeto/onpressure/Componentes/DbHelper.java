package br.com.onpressure.projeto.onpressure.Componentes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "OnPressure.db";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE_USUARIOS = "CREATE TABLE Usuarios (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Nome TEXT NOT NULL, " +
            "Email TEXT NOT NULL, " +
            "DataNascimento TEXT NOT NULL, " +
            "Ocupacao TEXT NOT NULL, " +
            "TipoSanguineo TEXT NOT NULL, " +
            "GrauHipertensao TEXT NOT NULL, " +
            "Telefone INTEGER NOT NULL, " +
            "Sexo TEXT NOT NULL);";

    private final String CREATE_TABLE_PRESSAO_ARTERIAL = "CREATE TABLE PressoesArterial (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " PressaoDiastolica REAL NOT NULL, " +
            " PressaoSistolica REAL NOT NULL, " +
            " FrequenciaCardiaca REAL NOT NULL, " +
            " Data TEXT NOT NULL);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIOS);
        db.execSQL(CREATE_TABLE_PRESSAO_ARTERIAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}