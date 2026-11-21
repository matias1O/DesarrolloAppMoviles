package com.duoc.TecnicoQCC.model

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class UsuarioDao_Impl(
  __db: RoomDatabase,
) : UsuarioDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUsuarioEntity: EntityInsertAdapter<UsuarioEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfUsuarioEntity = object : EntityInsertAdapter<UsuarioEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `usuario` (`codigoUsuario`) VALUES (?)"

      protected override fun bind(statement: SQLiteStatement, entity: UsuarioEntity) {
        statement.bindText(1, entity.codigoUsuario)
      }
    }
  }

  public override suspend fun guardar(usuario: UsuarioEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfUsuarioEntity.insert(_connection, usuario)
  }

  public override suspend fun obtener(): UsuarioEntity? {
    val _sql: String = "SELECT * FROM usuario LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfCodigoUsuario: Int = getColumnIndexOrThrow(_stmt, "codigoUsuario")
        val _result: UsuarioEntity?
        if (_stmt.step()) {
          val _tmpCodigoUsuario: String
          _tmpCodigoUsuario = _stmt.getText(_columnIndexOfCodigoUsuario)
          _result = UsuarioEntity(_tmpCodigoUsuario)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observar(): Flow<UsuarioEntity?> {
    val _sql: String = "SELECT * FROM usuario LIMIT 1"
    return createFlow(__db, false, arrayOf("usuario")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfCodigoUsuario: Int = getColumnIndexOrThrow(_stmt, "codigoUsuario")
        val _result: UsuarioEntity?
        if (_stmt.step()) {
          val _tmpCodigoUsuario: String
          _tmpCodigoUsuario = _stmt.getText(_columnIndexOfCodigoUsuario)
          _result = UsuarioEntity(_tmpCodigoUsuario)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun borrarTodo() {
    val _sql: String = "DELETE FROM usuario"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
