package id.phephen.al_islamic_apps.network.response

/**
 * Created by Phephen on 31/05/2022.
 */

data class ListSurahResponse(
    val code: Int,
    val status: String,
    val message: String,
    val data: List<DetailSurah>
) {
    data class DetailSurah(
        val number: Int,
        val sequence: Int,
        val numberOfVerses: Int,
        val name: NameSurah,
        val revelation: RevelationSurah,
        val tafsir: TafsirSurah
    ){
        data class NameSurah(
            val short: String,
            val long: String,
            val transliteration: Transliteration,
            val translation: Translation
        ){
            data class Transliteration(
                val en: String,
                val id: String
            )
            data class Translation(
                val en: String,
                val id: String
            )
        }
        data class RevelationSurah(
            val arab: String,
            val en: String,
            val id: String
        )
        data class TafsirSurah(
            val id: String
        )
    }
}