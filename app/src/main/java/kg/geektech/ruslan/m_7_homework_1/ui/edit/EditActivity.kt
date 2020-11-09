package kg.geektech.ruslan.m_7_homework_1.ui.edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kg.geektech.ruslan.m_7_homework_1.R
import kg.geektech.ruslan.m_7_homework_1.core.showToast
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    private var mViewModel: EditViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        mViewModel = ViewModelProvider(this, EditViewModelFactory()).get(EditViewModel::class.java)
        setDataAction()
        observe()
    }

    private fun observe() {
        mViewModel?.isFinished?.observe(this, Observer { if (it) finish() })
        mViewModel?.showToast?.observe(this, Observer { showToast(this, it) })
        mViewModel?.result?.observe(this, Observer { setResult(it) })
    }

    private fun setDataAction() {
        edit_activity_button_password.setOnClickListener {
            mViewModel?.setDataAction(edit_activity_edit_text_edit.text.toString().trim())
        }
    }

    companion object {
        const val RESULT_CODE = "code_result"
    }

    private fun setResult(result: String){
        val intent = Intent()
        intent.putExtra(RESULT_CODE, result)
        setResult(Activity.RESULT_OK, intent)
    }
}
