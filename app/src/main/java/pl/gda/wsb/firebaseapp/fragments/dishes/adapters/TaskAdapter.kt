package pl.gda.wsb.firebaseapp.fragments.dishes.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.gda.wsb.firebaseapp.databinding.TaskItemLayoutBinding
import pl.gda.wsb.firebaseapp.fragments.dishes.DishResponse

class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.TaskItemViewHolder>() {
    var items = emptyList<DishResponse>()

    // .let .allow. .with .apply w kotlinie co to znaczy i kiedy uzywamy
    //

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskItemLayoutBinding.inflate(inflater, parent, false)
            .let(::TaskItemViewHolder)
        setTaskData(items)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int): Unit =
        with(holder) {
            bind(items[position])
        }

    inner class TaskItemViewHolder(private val layout: TaskItemLayoutBinding) :
        RecyclerView.ViewHolder(layout.root) {
        // tutaj przypisujemy dane do elementów z widoku np. textView
        fun bind(item: DishResponse) = with(layout) {
            Log.d("valueTasksItP", "${item.name}")
            tvName.text = item.name
            tvRestaurant.text = item.restaurant
            tvDescription.text = item.description
        }
    }

    fun setTaskData(list: List<DishResponse>) {
        this.items = emptyList()
        this.items = list
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = items.size
}