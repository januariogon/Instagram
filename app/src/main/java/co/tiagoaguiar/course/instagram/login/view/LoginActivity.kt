package co.tiagoaguiar.course.instagram.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.util.TxtWatcher
import co.tiagoaguiar.course.instagram.databinding.ActivityLoginBinding
import co.tiagoaguiar.course.instagram.login.Login
import co.tiagoaguiar.course.instagram.login.presentation.LoginPresenter
import co.tiagoaguiar.course.instagram.main.view.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = LoginPresenter(this)

        with(binding) {
            loginEditEmail.addTextChangedListener(watcher)
            loginEditEmail.addTextChangedListener(TxtWatcher{
                displayEmailFailure(null)
            })
            loginEditPassword.addTextChangedListener(watcher)
            loginEditPassword.addTextChangedListener(TxtWatcher{
                displayPasswordFailure(null)
            })
            loginBtnEnter.setOnClickListener {
                presenter.login(loginEditEmail.text.toString(), loginEditPassword.text.toString())

      //          Handler(Looper.getMainLooper()).postDelayed({
      //              loginBtnEnter.showProgress(false)
      //          }, 2000)
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher{
        binding.loginBtnEnter.isEnabled = binding.loginEditEmail.text.toString().isNotEmpty()
                && binding.loginEditPassword.text.toString().isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding.loginBtnEnter.showProgress(true)

    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.loginEditEmailInput.error = emailError?.let { getString(it) }

    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.loginEditPasswordInput.error = passwordError?.let { getString(it) }

    }

    override fun onUserAuthenticated() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}