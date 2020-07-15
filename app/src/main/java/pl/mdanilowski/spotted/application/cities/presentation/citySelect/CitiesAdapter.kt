package pl.mdanilowski.spotted.application.cities.presentation.citySelect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_city_tile.view.*
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.application.cities.data.entity.CityEntity

class CitiesAdapter(private val onCityClicked: OnCityClicked) :
    RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    private val cities = mutableListOf<CityEntity>()

    fun setCities(citiesList: List<CityEntity>) {
        cities.clear()
        cities.addAll(citiesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_city_tile, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cities.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        holder.cityName.text = city.name
        Glide.with(holder.itemView)
            .load(city.image)
            .placeholder(R.color.colorAccent)
            .into(holder.cityImage)
        holder.bind(city, onCityClicked)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cityTile: View = view.cityTileView
        val cityImage: ImageView = view.cityImage
        val cityName: TextView = view.cityNameTv

        fun bind(city: CityEntity, onCityClicked: OnCityClicked) {
            cityTile.setOnClickListener { onCityClicked.onClick(city) }
        }
    }

    interface OnCityClicked {
        fun onClick(city: CityEntity)
    }
}