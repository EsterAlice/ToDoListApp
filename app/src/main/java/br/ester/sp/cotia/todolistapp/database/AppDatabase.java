package br.ester.sp.cotia.todolistapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.ester.sp.cotia.todolistapp.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    // variável para acessar a database
    private static AppDatabase database;
    //método para tarefaDao
    public abstract TarefaDao getTarefaDao();
    // método para
    public static AppDatabase getDatabase(Context context){
        //verifico se a database é nula
        if (database == null){
            //instancia a database
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "todolist").build();
        }
        // retorna a database
        return database;
    }
}
