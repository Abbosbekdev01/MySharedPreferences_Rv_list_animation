package uz.abbosbek.contact_rv_animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.abbosbek.contact_rv_animation.databinding.ActivityAddBinding
import uz.abbosbek.contact_rv_animation.models.User
import uz.abbosbek.contact_rv_animation.utils.MySharedPreference

class AddActivity : AppCompatActivity() {

    var isAdd = true
    var position = -1
    private lateinit var list: ArrayList<User>
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MySharedPreference.init(this)

        isAdd = intent.getBooleanExtra("key", true)
        if (!isAdd){
            position = intent.getIntExtra("position", -1)
            list = MySharedPreference.userList
            binding.edtName.setText(list[position].name)
            binding.edtNumber.setText(list[position].number)
        }

        binding.apply {
            btnSave.setOnClickListener {
                if (isAdd) {
                    val user = User(edtName.text.toString(), edtNumber.text.toString())

                    /** bu yerda catch dan listga ma'lumotlarni o'qib oladi*/
                    val list = MySharedPreference.userList

                    /** catch ga ma'lumotni listga qo'shib oladi*/
                    list.add(user)

                    /** qo'ahilgan ma'lumotni catch ga yozib qo'yilyabti */
                    MySharedPreference.userList = list

                    Toast.makeText(this@AddActivity, "Save", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    val user = User(edtName.text.toString(), edtNumber.text.toString())
                    list[position] = user
                    MySharedPreference.userList = list
                    Toast.makeText(this@AddActivity, "Edited", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}