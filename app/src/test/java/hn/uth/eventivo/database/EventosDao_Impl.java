package hn.uth.eventivo.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EventosDao_Impl implements EventosDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Eventos> __insertionAdapterOfEventos;

  private final EntityDeletionOrUpdateAdapter<Eventos> __deletionAdapterOfEventos;

  private final EntityDeletionOrUpdateAdapter<Eventos> __updateAdapterOfEventos;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public EventosDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEventos = new EntityInsertionAdapter<Eventos>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `eventos_table` (`id`,`expositor`,`tema`,`fecha`,`estado`,`detalle`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Eventos value) {
        stmt.bindLong(1, value.getId_eventos());
        if (value.getExpositor() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getExpositor());
        }
        if (value.getTema() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTema());
        }
        if (value.getFecha() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFecha());
        }
        if (value.getEstado() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEstado());
        }
        if (value.getDetalle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDetalle());
        }
      }
    };
    this.__deletionAdapterOfEventos = new EntityDeletionOrUpdateAdapter<Eventos>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `eventos_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Eventos value) {
        stmt.bindLong(1, value.getId_eventos());
      }
    };
    this.__updateAdapterOfEventos = new EntityDeletionOrUpdateAdapter<Eventos>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `eventos_table` SET `id` = ?,`expositor` = ?,`tema` = ?,`fecha` = ?,`estado` = ?,`detalle` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Eventos value) {
        stmt.bindLong(1, value.getId_eventos());
        if (value.getExpositor() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getExpositor());
        }
        if (value.getTema() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTema());
        }
        if (value.getFecha() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFecha());
        }
        if (String.valueOfvalue.getEstado(()) == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, String.valueOf(value.getEstado()));
        }
        if (value.getDetalle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDetalle());
        }
        stmt.bindLong(7, value.getId_eventos());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM eventos_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Eventos nuevo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEventos.insert(nuevo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Eventos eliminar) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfEventos.handle(eliminar);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Eventos actualizar) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfEventos.handle(actualizar);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Eventos>> getEventos() {
    final String _sql = "SELECT * FROM eventos_table order by expositor";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"eventos_table"}, false, new Callable<List<Eventos>>() {
      @Override
      public List<Eventos> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdEventos = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfExpositor = CursorUtil.getColumnIndexOrThrow(_cursor, "expositor");
          final int _cursorIndexOfTema = CursorUtil.getColumnIndexOrThrow(_cursor, "tema");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
          final int _cursorIndexOfDetalle = CursorUtil.getColumnIndexOrThrow(_cursor, "detalle");
          final List<Eventos> _result = new ArrayList<Eventos>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Eventos _item;
            final String _tmpExpositor;
            if (_cursor.isNull(_cursorIndexOfExpositor)) {
              _tmpExpositor = null;
            } else {
              _tmpExpositor = _cursor.getString(_cursorIndexOfExpositor);
            }
            final String _tmpTema;
            if (_cursor.isNull(_cursorIndexOfTema)) {
              _tmpTema = null;
            } else {
              _tmpTema = _cursor.getString(_cursorIndexOfTema);
            }
            final String _tmpFecha;
            if (_cursor.isNull(_cursorIndexOfFecha)) {
              _tmpFecha = null;
            } else {
              _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
            }
            final String _tmpEstado;
            if (_cursor.isNull(_cursorIndexOfEstado)) {
              _tmpEstado = null;
            } else {
              _tmpEstado = _cursor.getString(_cursorIndexOfEstado);
            }
            final String _tmpDetalle;
            if (_cursor.isNull(_cursorIndexOfDetalle)) {
              _tmpDetalle = null;
            } else {
              _tmpDetalle = _cursor.getString(_cursorIndexOfDetalle);
            }
            _item = new Eventos(_tmpTema,_tmpExpositor,_tmpFecha,_tmpEstado,_tmpDetalle);
            final int _tmpId_eventos;
            _tmpId_eventos = _cursor.getInt(_cursorIndexOfIdEventos);
            _item.setId_eventos(_tmpId_eventos);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Eventos>> getEventosPorExpositor(final String expositor) {
    final String _sql = "SELECT * FROM eventos_table WHERE expositor LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (expositor == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, expositor);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"eventos_table"}, false, new Callable<List<Eventos>>() {
      @Override
      public List<Eventos> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdEventos = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfExpositor = CursorUtil.getColumnIndexOrThrow(_cursor, "expositor");
          final int _cursorIndexOfTema = CursorUtil.getColumnIndexOrThrow(_cursor, "tema");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
          final int _cursorIndexOfDetalle = CursorUtil.getColumnIndexOrThrow(_cursor, "detalle");
          final List<Eventos> _result = new ArrayList<Eventos>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Eventos _item;
            final String _tmpExpositor;
            if (_cursor.isNull(_cursorIndexOfExpositor)) {
              _tmpExpositor = null;
            } else {
              _tmpExpositor = _cursor.getString(_cursorIndexOfExpositor);
            }
            final String _tmpTema;
            if (_cursor.isNull(_cursorIndexOfTema)) {
              _tmpTema = null;
            } else {
              _tmpTema = _cursor.getString(_cursorIndexOfTema);
            }
            final String _tmpFecha;
            if (_cursor.isNull(_cursorIndexOfFecha)) {
              _tmpFecha = null;
            } else {
              _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
            }
            final String _tmpEstado;
            if (_cursor.isNull(_cursorIndexOfEstado)) {
              _tmpEstado = null;
            } else {
              _tmpEstado = _cursor.getString(_cursorIndexOfEstado);
            }
            final String _tmpDetalle;
            if (_cursor.isNull(_cursorIndexOfDetalle)) {
              _tmpDetalle = null;
            } else {
              _tmpDetalle = _cursor.getString(_cursorIndexOfDetalle);
            }
            _item = new Eventos(_tmpTema,_tmpExpositor,_tmpFecha,_tmpEstado,_tmpDetalle);
            final int _tmpId_eventos;
            _tmpId_eventos = _cursor.getInt(_cursorIndexOfIdEventos);
            _item.setId_eventos(_tmpId_eventos);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
