package com.duoc.TecnicoQCC.model

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _usuarioDao: Lazy<UsuarioDao> = lazy {
    UsuarioDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "1aa3c1e5b0bd91f88eff4a70ae9e7a93", "50eec0031045af7f408be05ab19eb824") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `usuario` (`codigoUsuario` TEXT NOT NULL, PRIMARY KEY(`codigoUsuario`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1aa3c1e5b0bd91f88eff4a70ae9e7a93')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `usuario`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsUsuario: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUsuario.put("codigoUsuario", TableInfo.Column("codigoUsuario", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUsuario: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUsuario: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUsuario: TableInfo = TableInfo("usuario", _columnsUsuario, _foreignKeysUsuario, _indicesUsuario)
        val _existingUsuario: TableInfo = read(connection, "usuario")
        if (!_infoUsuario.equals(_existingUsuario)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |usuario(com.duoc.TecnicoQCC.model.UsuarioEntity).
              | Expected:
              |""".trimMargin() + _infoUsuario + """
              |
              | Found:
              |""".trimMargin() + _existingUsuario)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "usuario")
  }

  public override fun clearAllTables() {
    super.performClear(false, "usuario")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(UsuarioDao::class, UsuarioDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun usuarioDao(): UsuarioDao = _usuarioDao.value
}
