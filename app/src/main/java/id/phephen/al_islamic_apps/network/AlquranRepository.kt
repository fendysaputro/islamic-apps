package id.phephen.al_islamic_apps.network

/**
 * Created by Phephen on 31/05/2022.
 */
class AlquranRepository(
    private val api: EndPoint
) {
    suspend fun fetchListSurah() = api.listSurah()

    suspend fun fetchDetailSurah(
        surah: String
    ) = api.detailSurah(surah)

    suspend fun fetchTafsir(
        surah: String,
        ayah: String
    ) = api.tafsirSurah(surah, ayah)
}