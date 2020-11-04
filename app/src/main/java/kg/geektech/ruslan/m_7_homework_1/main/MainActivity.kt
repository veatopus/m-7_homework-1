package kg.geektech.ruslan.m_7_homework_1.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import kg.geektech.ruslan.m_7_homework_1.edit.EditActivity
import kg.geektech.ruslan.m_7_homework_1.R
import kotlinx.android.synthetic.main.activity_main.*

const val EDIT_ACTIVITY = 41
class MainActivity : AppCompatActivity(){
    private var passwordText = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setArg()
        verifyPassword()
    }

    private fun verifyPassword() {
        main_activity_edit_text_password.addTextChangedListener {
            if(it.toString() == passwordText) startEditActivity()
        }
    }

    private fun startEditActivity() {
        if(passwordText.isNotEmpty()){
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(EditActivity.RESULT_CODE, passwordText)
            startActivityForResult(
                intent,
                EDIT_ACTIVITY
            )
        }
    }

    private fun setArg() {
        main_activity_text_view_password.text = passwordText
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == EDIT_ACTIVITY && data != null) {
            val text = data.getStringExtra(EditActivity.RESULT_CODE)
            main_activity_text_view_password.text = text
            passwordText = text.toString()
        }
    }
}