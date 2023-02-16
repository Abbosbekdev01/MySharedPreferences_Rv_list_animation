package uz.abbosbek.contact_rv_animation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.contact_rv_animation.databinding.ItemRvBinding
import uz.abbosbek.contact_rv_animation.models.User

class UserAdapter(var list: ArrayList<User>, val rvAction: RvAction):RecyclerView.Adapter<UserAdapter.Vh>() {

    inner class Vh(val itemRv: ItemRvBinding):RecyclerView.ViewHolder(itemRv.root){
        fun onBind(user: User, position: Int){
            itemRv.tvName.text = user.name
            itemRv.tvNumber.text = user.number
            itemRv.imageMenu.setOnClickListener {
                rvAction.myPopup(user,position, itemRv.imageMenu)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int =list.size
}

interface RvAction{
    fun myPopup(user: User, position:Int, imageView: ImageView)
}