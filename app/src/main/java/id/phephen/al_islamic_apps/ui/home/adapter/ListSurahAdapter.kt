package id.phephen.al_islamic_apps.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.phephen.al_islamic_apps.databinding.CustomListSuratBinding
import id.phephen.al_islamic_apps.network.response.ListSurahResponse

/**
 * Created by Phephen on 31/05/2022.
 */

class ListSurahAdapter(val listSurah: ArrayList<ListSurahResponse.DetailSurah>, val listener: OnAdapterListener): RecyclerView.Adapter<ListSurahAdapter.vHolder>() {

    class vHolder(val binding: CustomListSuratBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder (
        CustomListSuratBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ListSurahAdapter.vHolder, position: Int) {
        val dataSurah = listSurah[position]
        holder.binding.tvAyah.text = dataSurah.name.long
        holder.binding.tvDetail.text = dataSurah.revelation.id+" | "+dataSurah.numberOfVerses
        holder.binding.tvNumberAyah.text = dataSurah.number.toString()
        holder.binding.container.setOnClickListener {
            listener.onClick(dataSurah)
        }
    }

    override fun getItemCount() = listSurah.size

    fun setData(data: List<ListSurahResponse.DetailSurah>) {
        listSurah.clear()
        listSurah.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(result: ListSurahResponse.DetailSurah)
    }

}