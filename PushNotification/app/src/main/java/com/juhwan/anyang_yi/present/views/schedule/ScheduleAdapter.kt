package com.juhwan.anyang_yi.present.views.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemScheduleBinding
import com.juhwan.anyang_yi.domain.model.Schedule

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    var items = ArrayList<Schedule>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ScheduleViewHolder {
        val binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(list: List<Schedule>) {
        items.clear()
        items.addAll(list)
    }

    inner class ScheduleViewHolder(private val binding: ItemScheduleBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(schedule: Schedule) {
            binding.schedule = schedule
        }
    }
}