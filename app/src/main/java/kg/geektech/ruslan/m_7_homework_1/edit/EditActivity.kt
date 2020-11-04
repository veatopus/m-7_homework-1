package kg.geektech.ruslan.m_7_homework_1.edit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kg.geektech.ruslan.m_7_homework_1.R
import kg.geektech.ruslan.m_7_homework_1.core.showToast
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    private var text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        getData()
        setDataAction()
    }

    private fun setDataAction() {
        edit_activity_button_password.setOnClickListener {
            val newText = edit_activity_edit_text_edit.text.toString()
            if (newText != text) setData(newText)
            else showToast(this, "введите новый пароль")
        }
    }

    private fun setData(text: String) {
        if (text.isNotEmpty()) {
            val intent = Intent()
            intent.putExtra(RESULT_CODE, text)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun getData() {
        text = intent.getStringExtra(RESULT_CODE).toString()
        edit_activity_edit_text_edit.setText(text)
    }

    companion object {
        const val RESULT_CODE = "code_result"
    }
}