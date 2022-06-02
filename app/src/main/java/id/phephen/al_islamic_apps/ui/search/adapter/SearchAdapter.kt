package id.phephen.al_islamic_apps.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import id.phephen.al_islamic_apps.databinding.CustomListSearchSurahBinding
import id.phephen.al_islamic_apps.network.response.ListSurahResponse

/**
 * Created by Phephen on 02/06/2022.
 */

class SearchAdapter(
    val listSurah: ArrayList<ListSurahResponse.DetailSurah>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<SearchAdapter.vHolder>(), Filterable {

    class vHolder(val binding: CustomListSearchSurahBinding): RecyclerView.ViewHolder(binding.root)

    private var searchResult = ArrayList<ListSurahResponse.DetailSurah>()

    init {
        searchResult = listSurah
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
        CustomListSearchSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SearchAdapter.vHolder, position: Int) {
        val dataSurah = searchResult[position]
        holder.binding.tvIndo.text = dataSurah.name.transliteration.id
        holder.binding.container.setOnClickListener {
            listener.onClick(dataSurah)
        }
    }

    override fun getItemCount() = searchResult.size

    interface OnAdapterListener {
        fun onClick(result: ListSurahResponse.DetailSurah)
    }

    fun setData(data: List<ListSurahResponse.DetailSurah>) {
        listSurah.clear()
        listSurah.addAll(data)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSequence = p0.toString()

                if (charSequence.isEmpty()) {
                    searchResult = listSurah
                } else {
                    val surahFiltered = ArrayList<ListSurahResponse.DetailSurah>()
                    for (surah in listSurah) {
                        if (surah.name.transliteration.id.toLowerCase().contains(charSequence.toLowerCase())) {
                            surahFiltered.add(surah)
                        }
                    }
                    searchResult = surahFiltered
                }
                val finalResult = FilterResults()
                finalResult.values = searchResult
                return  finalResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                searchResult = p1?.values as ArrayList<ListSurahResponse.DetailSurah>
                notifyDataSetChanged()
            }

        }
    }


}