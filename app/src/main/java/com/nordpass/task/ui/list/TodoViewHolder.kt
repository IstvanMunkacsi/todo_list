package com.nordpass.task.ui.list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nordpass.task.R
import com.nordpass.tt.usecase.Todo

class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(todo: Todo, listener: (Todo) -> Unit) {
        fun TextView.strikeTextIfCompleted() {
            paintFlags = if (todo.isCompleted) {
                paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        itemView.apply {
            setOnClickListener { listener(todo) }

            findViewById<TextView>(R.id.itemTitleTextView)?.apply {
                text = todo.title
                strikeTextIfCompleted()
            }

            findViewById<TextView>(R.id.itemDueOnTextView)?.apply {
                text = context.getString(R.string.todoListDueOn, todo.dueOn)
                strikeTextIfCompleted()
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): TodoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_todo, parent, false)
            return TodoViewHolder(view)
        }
    }
}