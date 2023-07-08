package hn.uth.eventivo.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, exportSchema = false, entities = {Eventos.class})
public abstract class EventivoDatabase extends RoomDatabase {
    public  abstract EventosDao eventosDao();
    //public abstract InvitadosDao invitadosDao();

    private static volatile EventivoDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExectutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static EventivoDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (EventivoDatabase.class){
                if(INSTANCE == null){
                        Callback miCallback = new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                databaseWriteExectutor.execute(() -> {
                                    EventosDao dao = INSTANCE.eventosDao();
                                    dao.deleteAll();

                                    //AQUI SE PUEDEN CREAR VALORES POR DEFECTO DE LA BASE DE DATOS
                                    //dao.insert();
                                    Eventos dato1 = new Eventos("Capitalismo", "Renato Chamorro", "20/11/2023", "activo", "El capitalismo en la actualidad");
                                    Eventos dato2 = new Eventos("Fisica", "Javier Santaolalla", "21/11/2023", "activo", "Fisica de particulas");

                                    dao.insert(dato1);
                                    dao.insert(dato2);
                                });
                            }
                        };
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EventivoDatabase.class, "eventivo_db").addCallback(miCallback).build();
                    }
                }
            }
            return INSTANCE;
        }
    }

