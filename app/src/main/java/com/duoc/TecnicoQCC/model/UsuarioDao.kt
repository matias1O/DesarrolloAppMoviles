package com.duoc.TecnicoQCC.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardar(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuario LIMIT 1")
    suspend fun obtener(): UsuarioEntity?

    @Query("SELECT * FROM usuario LIMIT 1")
    fun observar(): Flow<UsuarioEntity?>

    @Query("DELETE FROM usuario")
    suspend fun borrarTodo()
}