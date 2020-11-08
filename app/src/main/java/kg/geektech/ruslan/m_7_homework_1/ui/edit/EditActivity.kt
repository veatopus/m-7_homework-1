package kg.geektech.ruslan.m_7_homework_1.ui.edit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import kg.geektech.ruslan.m_7_homework_1.R
import kg.geektech.ruslan.m_7_homework_1.core.showToast
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setDataAction()
    }

    private fun setDataAction() {
        edit_activity_button_password.setOnClickListener {
            val url = edit_activity_edit_text_edit.text.toString().trim()
            if (URLUtil.isValidUrl(url))
                if (URLUtil.isHttpsUrl(url)) setData(url)
                else showToast(this, "запросы иного типа от https не поддерживаються")
            else showToast(this, "не верный формат ссылки")
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

    companion object {
        const val RESULT_CODE = "code_result"
    }
}