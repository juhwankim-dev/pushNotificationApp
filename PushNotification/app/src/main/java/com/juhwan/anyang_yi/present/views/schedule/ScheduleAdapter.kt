package com.juhwan.anyang_yi.present.views.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.data.model.Schedule
import com.juhwan.anyang_yi.data.repository.ScheduleRepository
import com.juhwan.anyang_yi.databinding.ItemScheduleBinding

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    var items = ScheduleRepository.schedule

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ScheduleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemScheduleBinding.inflate(layoutInflater, parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ScheduleViewHolder(private val binding: ItemScheduleBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(schedule: Schedule) {
            binding.tvScheduleTitle.text = schedule.content
            binding.tvScheduleDate.text = schedule.date
        }
    }
}