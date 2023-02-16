package uz.abbosbek.contact_rv_animation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import uz.abbosbek.contact_rv_animation.adapters.RvAction
import uz.abbosbek.contact_rv_animation.adapters.UserAdapter
import uz.abbosbek.contact_rv_animation.databinding.ActivityMainBinding
import uz.abbosbek.contact_rv_animation.models.User
import uz.abbosbek.contact_rv_animation.utils.MySharedPreference

class MainActivity : AppCompatActivity(), RvAction {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var userAdapter: UserAdapter
    private lateinit var list:ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("key", true) // true agar add bo'lsa
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        MySharedPreference.init(this)
        /** listga catch dagi ma'lumotlarni o'qib olyabti */
         list = MySharedPreference.userList
        userAdapter = UserAdapter(list, this)
        binding.rv.adapter = userAdapter

    }

    override fun myPopup(user: User, position: Int, imageView: ImageView) {
        val popupMenu = PopupMenu(this,imageView)
        popupMenu.inflate(R.menu.item_popup_menu)

        popupMenu.setOnMenuItemClickListener ( PopupMenu.OnMenuItemClickListener{item:MenuItem?->
            when(item!!.itemId){
                R.id.menu_edit->{
                    val intent = Intent(this, AddActivity::class.java)
                    intent.putExtra("key", false) // true agar add bo'lsa aks holda edit
                    intent.putExtra("position", position)
                    startActivity(intent)

                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.menu_delete -> {
                    list.removeAt(position)
                    MySharedPreference.userList = list
                    userAdapter.list = list
                    userAdapter.notifyItemRemoved(position)
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }
            }
            true
        })

        popupMenu.show()
    }
}