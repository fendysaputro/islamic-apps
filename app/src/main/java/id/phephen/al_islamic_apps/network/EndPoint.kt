package id.phephen.al_islamic_apps.network

import id.phephen.al_islamic_apps.network.response.DetailSurahResponse
import id.phephen.al_islamic_apps.network.response.ListSurahResponse
import id.phephen.al_islamic_apps.network.response.TafsirResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Phephen on 31/05/2022.
 */
interface EndPoint {

    @GET("surah")
    suspend fun listSurah(): Response<ListSurahResponse>

    @GET("surah/{surah}")
    suspend fun detailSurah(@Path("surah") surah: String): Response<DetailSurahResponse>

    @GET("surah/{surah}/{ayah}")
    suspend fun tafsirSurah(@Path("surah") surah: String,
    @Path("ayah") ayah: String): Response<TafsirResponse>

}