package com.mykieta.sasinmedmobile

import android.graphics.Color
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext

class RecyclerAdapter(private val data: Array<ShowQueueActivity.Visit>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dateOfVisit: TextView = view.findViewById(R.id.date_of_visit)
        val doctor: TextView = view.findViewById(R.id.doctor)
        val patient: TextView = view.findViewById(R.id.patient)
        val timeOfVisit: TextView = view.findViewById(R.id.time_of_visit)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.queue_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val visit: ShowQueueActivity.Visit = data[position]
        holder.dateOfVisit.text = visit.dateOfVisit
        holder.doctor.text = visit.doctor
        holder.patient.text = visit.patient
        holder.timeOfVisit.text = visit.timeOfVisit.substring(0, 5)

        if (position % 2 == 0) {
            var background = holder.itemView.background
            background.setTint(Color.parseColor("#ebffff"))
            holder.itemView.background = background
        }
    }

    override fun getItemCount() = data.size

}