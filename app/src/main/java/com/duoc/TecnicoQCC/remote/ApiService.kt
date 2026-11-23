package com.duoc.TecnicoQCC.remote

import com.duoc.TecnicoQCC.model.PerfilUsuario
import com.duoc.TecnicoQCC.model.Rack
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // 1. GET: Listar todos los racks
    @GET("racks")
    suspend fun getRacks(): List<Rack>

    // 2. POST: Crear un nuevo rack
    @POST("racks")
    suspend fun crearRack(@Body rack: Rack): Response<Rack>

    // 3. GET: Obtener un rack por su ID
    @GET("racks/{racks_id}")
    suspend fun getRackById(@Path("racks_id") id: String): Rack

    // 4. PATCH: Editar parcialmente un rack
    @PATCH("racks/{racks_id}")
    suspend fun editarRack(
        @Path("racks_id") id: String,
        @Body rack: Rack
    ): Response<Rack>

    // 5. PUT: Actualizar completamente un rack
    @PUT("racks/{racks_id}")
    suspend fun actualizarRack(
        @Path("racks_id") id: String,
        @Body rack: Rack
    ): Response<Rack>

    // 6. DELETE: Eliminar un rack
    @DELETE("racks/{racks_id}")
    suspend fun eliminarRack(@Path("racks_id") id: String): Response<Unit>

    // 7. GET: Listar todos los usuarios
    @GET("usuario")
    suspend fun getUsuarios(): List<PerfilUsuario>

    // 8. POST: Crear un nuevo usuario
    @POST("usuario")
    suspend fun crearUsuario(@Body usuario: PerfilUsuario): Response<PerfilUsuario>

    // 9. GET: Obtener un usuario por su ID
    @GET("usuario/{usuario_id}")
    suspend fun getUsuarioById(@Path("usuario_id") id: Int): PerfilUsuario

    // 10. PUT: Actualizar completamente un usuario
    @PUT("usuario/{usuario_id}")
    suspend fun actualizarUsuario(
        @Path("usuario_id") id: Int,
        @Body usuario: PerfilUsuario
    ): Response<PerfilUsuario>

    // 11. PATCH: Editar parcialmente un usuario
    @PATCH("usuario/{usuario_id}")
    suspend fun editarUsuario(
        @Path("usuario_id") id: Int,
        @Body usuario: PerfilUsuario
    ): Response<PerfilUsuario>

    // 12. DELETE: Eliminar un usuario
    @DELETE("usuario/{usuario_id}")
    suspend fun eliminarUsuario(@Path("usuario_id") id: Int): Response<Unit>
}