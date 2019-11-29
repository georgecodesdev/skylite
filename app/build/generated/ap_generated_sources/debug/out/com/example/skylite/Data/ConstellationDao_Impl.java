package com.example.skylite.Data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ConstellationDao_Impl implements ConstellationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Constellation> __insertionAdapterOfConstellation;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ConstellationDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfConstellation = new EntityInsertionAdapter<Constellation>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `constellation_table` (`Id`,`ObservationSeason`,`Name`,`Area`,`Declination`,`RightAscension`,`PrincipalStar`,`CelestialEquatorZone`,`EclipticZone`,`Quadrant`,`NameOrigin`,`Meaning`,`Image`,`Story`,`FirstAppeared`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Constellation value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getObservationSeason() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getObservationSeason());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        stmt.bindDouble(4, value.getArea());
        if (value.getDeclination() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDeclination());
        }
        if (value.getRightAscension() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRightAscension());
        }
        if (value.getPrincipalStar() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPrincipalStar());
        }
        if (value.getCelestialEquatorZone() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getCelestialEquatorZone());
        }
        if (value.getEclipticZone() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getEclipticZone());
        }
        if (value.getQuadrant() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getQuadrant());
        }
        if (value.getNameOrigin() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getNameOrigin());
        }
        if (value.getMeaning() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getMeaning());
        }
        if (value.getImage() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getImage());
        }
        if (value.getStory() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getStory());
        }
        if (value.getFirstAppeared() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getFirstAppeared());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM constellation_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Constellation constellation) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfConstellation.insert(constellation);
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
  public LiveData<List<Constellation>> getAll() {
    final String _sql = "SELECT * FROM constellation_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"constellation_table"}, false, new Callable<List<Constellation>>() {
      @Override
      public List<Constellation> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfObservationSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "ObservationSeason");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "Area");
          final int _cursorIndexOfDeclination = CursorUtil.getColumnIndexOrThrow(_cursor, "Declination");
          final int _cursorIndexOfRightAscension = CursorUtil.getColumnIndexOrThrow(_cursor, "RightAscension");
          final int _cursorIndexOfPrincipalStar = CursorUtil.getColumnIndexOrThrow(_cursor, "PrincipalStar");
          final int _cursorIndexOfCelestialEquatorZone = CursorUtil.getColumnIndexOrThrow(_cursor, "CelestialEquatorZone");
          final int _cursorIndexOfEclipticZone = CursorUtil.getColumnIndexOrThrow(_cursor, "EclipticZone");
          final int _cursorIndexOfQuadrant = CursorUtil.getColumnIndexOrThrow(_cursor, "Quadrant");
          final int _cursorIndexOfNameOrigin = CursorUtil.getColumnIndexOrThrow(_cursor, "NameOrigin");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "Meaning");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "Image");
          final int _cursorIndexOfStory = CursorUtil.getColumnIndexOrThrow(_cursor, "Story");
          final int _cursorIndexOfFirstAppeared = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstAppeared");
          final List<Constellation> _result = new ArrayList<Constellation>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Constellation _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpObservationSeason;
            _tmpObservationSeason = _cursor.getString(_cursorIndexOfObservationSeason);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpDeclination;
            _tmpDeclination = _cursor.getString(_cursorIndexOfDeclination);
            final String _tmpRightAscension;
            _tmpRightAscension = _cursor.getString(_cursorIndexOfRightAscension);
            final String _tmpPrincipalStar;
            _tmpPrincipalStar = _cursor.getString(_cursorIndexOfPrincipalStar);
            final String _tmpCelestialEquatorZone;
            _tmpCelestialEquatorZone = _cursor.getString(_cursorIndexOfCelestialEquatorZone);
            final String _tmpEclipticZone;
            _tmpEclipticZone = _cursor.getString(_cursorIndexOfEclipticZone);
            final String _tmpQuadrant;
            _tmpQuadrant = _cursor.getString(_cursorIndexOfQuadrant);
            final String _tmpNameOrigin;
            _tmpNameOrigin = _cursor.getString(_cursorIndexOfNameOrigin);
            final String _tmpMeaning;
            _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            final String _tmpImage;
            _tmpImage = _cursor.getString(_cursorIndexOfImage);
            final String _tmpStory;
            _tmpStory = _cursor.getString(_cursorIndexOfStory);
            final String _tmpFirstAppeared;
            _tmpFirstAppeared = _cursor.getString(_cursorIndexOfFirstAppeared);
            _item = new Constellation(_tmpId,_tmpObservationSeason,_tmpName,_tmpArea,_tmpDeclination,_tmpRightAscension,_tmpPrincipalStar,_tmpCelestialEquatorZone,_tmpEclipticZone,_tmpQuadrant,_tmpNameOrigin,_tmpMeaning,_tmpImage,_tmpStory,_tmpFirstAppeared);
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
  public LiveData<Constellation> get(final String id) {
    final String _sql = "SELECT * FROM constellation_table WHERE Id == ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"constellation_table"}, false, new Callable<Constellation>() {
      @Override
      public Constellation call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfObservationSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "ObservationSeason");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "Area");
          final int _cursorIndexOfDeclination = CursorUtil.getColumnIndexOrThrow(_cursor, "Declination");
          final int _cursorIndexOfRightAscension = CursorUtil.getColumnIndexOrThrow(_cursor, "RightAscension");
          final int _cursorIndexOfPrincipalStar = CursorUtil.getColumnIndexOrThrow(_cursor, "PrincipalStar");
          final int _cursorIndexOfCelestialEquatorZone = CursorUtil.getColumnIndexOrThrow(_cursor, "CelestialEquatorZone");
          final int _cursorIndexOfEclipticZone = CursorUtil.getColumnIndexOrThrow(_cursor, "EclipticZone");
          final int _cursorIndexOfQuadrant = CursorUtil.getColumnIndexOrThrow(_cursor, "Quadrant");
          final int _cursorIndexOfNameOrigin = CursorUtil.getColumnIndexOrThrow(_cursor, "NameOrigin");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "Meaning");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "Image");
          final int _cursorIndexOfStory = CursorUtil.getColumnIndexOrThrow(_cursor, "Story");
          final int _cursorIndexOfFirstAppeared = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstAppeared");
          final Constellation _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpObservationSeason;
            _tmpObservationSeason = _cursor.getString(_cursorIndexOfObservationSeason);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpDeclination;
            _tmpDeclination = _cursor.getString(_cursorIndexOfDeclination);
            final String _tmpRightAscension;
            _tmpRightAscension = _cursor.getString(_cursorIndexOfRightAscension);
            final String _tmpPrincipalStar;
            _tmpPrincipalStar = _cursor.getString(_cursorIndexOfPrincipalStar);
            final String _tmpCelestialEquatorZone;
            _tmpCelestialEquatorZone = _cursor.getString(_cursorIndexOfCelestialEquatorZone);
            final String _tmpEclipticZone;
            _tmpEclipticZone = _cursor.getString(_cursorIndexOfEclipticZone);
            final String _tmpQuadrant;
            _tmpQuadrant = _cursor.getString(_cursorIndexOfQuadrant);
            final String _tmpNameOrigin;
            _tmpNameOrigin = _cursor.getString(_cursorIndexOfNameOrigin);
            final String _tmpMeaning;
            _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            final String _tmpImage;
            _tmpImage = _cursor.getString(_cursorIndexOfImage);
            final String _tmpStory;
            _tmpStory = _cursor.getString(_cursorIndexOfStory);
            final String _tmpFirstAppeared;
            _tmpFirstAppeared = _cursor.getString(_cursorIndexOfFirstAppeared);
            _result = new Constellation(_tmpId,_tmpObservationSeason,_tmpName,_tmpArea,_tmpDeclination,_tmpRightAscension,_tmpPrincipalStar,_tmpCelestialEquatorZone,_tmpEclipticZone,_tmpQuadrant,_tmpNameOrigin,_tmpMeaning,_tmpImage,_tmpStory,_tmpFirstAppeared);
          } else {
            _result = null;
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
}
