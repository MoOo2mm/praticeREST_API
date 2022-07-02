package com.example.dologinout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dologinout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LogInView {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpBt.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.logInBt.setOnClickListener{
            login()
        }
    }

    private fun login(){
        if (binding.putIdEt.text.toString().isEmpty() || binding.putEmailEt.text.toString().isEmpty()){
            Toast.makeText(this, "이메일 형식이 잘못되었습니댜.", Toast.LENGTH_SHORT).show()
            return
        }
        if (binding.putPasswordEt.text.toString().isEmpty()){
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        var email : String = binding.putIdEt.text.toString() + "@" + binding.putEmailEt.text.toString()
        val password : String = binding.putPasswordEt.text.toString()

        val authService = AuthService()
        authService.setLogInView(this)

        authService.login(User(email, password, ""))

    }

    private fun saveJwt(jwt : String)
    {
        val spf = getSharedPreferences("auth", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    override fun onLogInSuccess(code : Int, result: Result) {
        when (code)
        {
            1000 -> {
                saveJwt(result.jwt)
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onLogInFailure() {
        TODO("Not yet implemented")
    }
}