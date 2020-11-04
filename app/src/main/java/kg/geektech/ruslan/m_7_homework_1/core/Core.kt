package kg.geektech.ruslan.m_7_homework_1.core

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, text: String){
    Toast.makeText(context,text, Toast.LENGTH_LONG).show()
}