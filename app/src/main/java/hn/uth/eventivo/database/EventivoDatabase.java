package hn.uth.eventivo.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 3, exportSchema = false, entities = {Eventos.class, Invitados.class})
public abstract class EventivoDatabase extends RoomDatabase {
    public  abstract EventosDao eventosDao();
    public abstract InvitadosDao invitadosDao();

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
                                    InvitadosDao daoInvitados = INSTANCE.invitadosDao();
                                    dao.deleteAll();
                                    daoInvitados.deleteAll();

                                    //AQUI SE PUEDEN CREAR VALORES POR DEFECTO DE LA BASE DE DATOS
                                    //dao.insert();
                                    Eventos dato1 = new Eventos("Capitalismo", "Renato Chamorro", "20/11/2023", "activo", "El capitalismo en la actualidad");
                                    Eventos dato2 = new Eventos("Fisica", "Javier Santaolalla", "21/11/2023", "activo", "Fisica de particulas");

                                    dao.insert(dato1);
                                    dao.insert(dato2);

                                    Invitados dato5= new Invitados("Carlos Moradel", "c_2@yahoo.com", "11/09/2022");
                                    Invitados dato6= new Invitados("Ian Saenz", "iansaenz@gmail.com", "08/01/2021");
                                    Invitados dato7= new Invitados("Carlos Moradel", "c_2@yahoo.com", "11/09/2022");
                                    Invitados dato8= new Invitados("Ian Saenz", "iansaenz@gmail.com", "08/01/2021");

                                    daoInvitados.insert(dato5);
                                    daoInvitados.insert(dato6);
                                    daoInvitados.insert(dato7);
                                    daoInvitados.insert(dato8);


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

