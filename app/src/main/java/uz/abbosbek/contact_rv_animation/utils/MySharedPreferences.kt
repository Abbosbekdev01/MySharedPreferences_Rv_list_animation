package uz.abbosbek.contact_rv_animation.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.abbosbek.contact_rv_animation.models.User

object MySharedPreference {
    /** Catch_file_name-> bu biz ma'lumotlarimiz saqlanadigan faly nomi  */
    private const val NAME = "Catch_file_name"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var userList:ArrayList<User>
    /** USTOZDAN SO'RAB OLAMIZ */
        get() = gsonStringToArrayList(preferences.getString("my_list", "[]")!!)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString("my_list", listToString(value))
            }
        }

    /** gson*/
    fun gsonStringToArrayList(gsonString: String): ArrayList<User> {
        val list = ArrayList<User>()
        val type = object : TypeToken<ArrayList<User>>(){}.type
        list.addAll(Gson().fromJson(gsonString, type))
        return list
    }

    fun listToString(list: ArrayList<User>):String{
        return Gson().toJson(list)
    }
}