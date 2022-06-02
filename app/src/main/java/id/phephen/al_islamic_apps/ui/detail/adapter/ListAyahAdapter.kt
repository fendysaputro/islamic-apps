package id.phephen.al_islamic_apps.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.phephen.al_islamic_apps.databinding.CustomListAyahBinding
import id.phephen.al_islamic_apps.network.response.DetailSurahResponse

/**
 * Created by Phephen on 02/06/2022.
 */
class ListAyahAdapter(
    val listAyat: List<DetailSurahResponse.DataSurah.VersesSurah>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<ListAyahAdapter.vHolder>() {

    class vHolder(val binding: CustomListAyahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
        CustomListAyahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ListAyahAdapter.vHolder, position: Int) {
        val ayat = listAyat[position]
        holder.binding.tvArabic.text = ayat.text.arab
        holder.binding.tvLatin.text = ayat.text.transliteration.en
        holder.binding.tvTerjemah.text = ayat.translation.id
        holder.binding.container.setOnClickListener {
            listener.onClick(ayat)
        }
    }

    override fun getItemCount(): Int = listAyat.size

    interface OnAdapterListener {
        fun onClick(result: DetailSurahResponse.DataSurah.VersesSurah)
    }

}