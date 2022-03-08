package com.example.noteapp.view.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.viewmodel.NoteModel

class NoteAdapter(private val notes: ArrayList<NoteModel.Data>, private val listener: OnAdapterListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_note, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = notes[position]
        holder.textNote.text = data.note
        holder.itemView.setOnClickListener {
            listener.onUpdate(data)
        }
        holder.imageDelete.setOnClickListener {
            listener.onDelete(data)
        }
    }

    override fun getItemCount() = notes.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textNote: TextView = view.findViewById(R.id.text_note)
        val imageDelete: ImageView = view.findViewById(R.id.image_delete)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<NoteModel.Data>) {
        notes.clear()
        notes.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onUpdate(note: NoteModel.Data)
        fun onDelete(note: NoteModel.Data)
    }
}