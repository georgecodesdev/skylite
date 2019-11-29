package com.example.skylite.Data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ConstellationRoomDatabase_Impl extends ConstellationRoomDatabase {
  private volatile ConstellationDao _constellationDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(7) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `constellation_table` (`Id` TEXT NOT NULL, `ObservationSeason` TEXT, `Name` TEXT, `Area` REAL NOT NULL, `Declination` TEXT, `RightAscension` TEXT, `PrincipalStar` TEXT, `CelestialEquatorZone` TEXT, `EclipticZone` TEXT, `Quadrant` TEXT, `NameOrigin` TEXT, `Meaning` TEXT, `Image` TEXT, `Story` TEXT, `FirstAppeared` TEXT, PRIMARY KEY(`Id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a6297d2abea3cc8a4afddee656ad0cb8')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `constellation_table`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsConstellationTable = new HashMap<String, TableInfo.Column>(15);
        _columnsConstellationTable.put("Id", new TableInfo.Column("Id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("ObservationSeason", new TableInfo.Column("ObservationSeason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("Name", new TableInfo.Column("Name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("Area", new TableInfo.Column("Area", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("Declination", new TableInfo.Column("Declination", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("RightAscension", new TableInfo.Column("RightAscension", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("PrincipalStar", new TableInfo.Column("PrincipalStar", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("CelestialEquatorZone", new TableInfo.Column("CelestialEquatorZone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("EclipticZone", new TableInfo.Column("EclipticZone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("Quadrant", new TableInfo.Column("Quadrant", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("NameOrigin", new TableInfo.Column("NameOrigin", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("Meaning", new TableInfo.Column("Meaning", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("Image", new TableInfo.Column("Image", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("Story", new TableInfo.Column("Story", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConstellationTable.put("FirstAppeared", new TableInfo.Column("FirstAppeared", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysConstellationTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesConstellationTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoConstellationTable = new TableInfo("constellation_table", _columnsConstellationTable, _foreignKeysConstellationTable, _indicesConstellationTable);
        final TableInfo _existingConstellationTable = TableInfo.read(_db, "constellation_table");
        if (! _infoConstellationTable.equals(_existingConstellationTable)) {
          return new RoomOpenHelper.ValidationResult(false, "constellation_table(com.example.skylite.Data.Constellation).\n"
                  + " Expected:\n" + _infoConstellationTable + "\n"
                  + " Found:\n" + _existingConstellationTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a6297d2abea3cc8a4afddee656ad0cb8", "654e421235f60bdc396b56aa6606c139");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "constellation_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `constellation_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public ConstellationDao constellationDao() {
    if (_constellationDao != null) {
      return _constellationDao;
    } else {
      synchronized(this) {
        if(_constellationDao == null) {
          _constellationDao = new ConstellationDao_Impl(this);
        }
        return _constellationDao;
      }
    }
  }
}
