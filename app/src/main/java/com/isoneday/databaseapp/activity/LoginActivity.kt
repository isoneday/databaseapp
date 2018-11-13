package com.isoneday.databaseapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.isoneday.databaseapp.MainActivity
import com.isoneday.databaseapp.R
import com.isoneday.databaseapp.model.ResponseRegisterLogin
import com.isoneday.databaseapp.network.InitRetrofit
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {


    private var strlevel: String? = null
    private var stremail: String? = null
    private var strpassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        regAdmin.setOnClickListener(this)
        regMahasiswa.setOnClickListener(this)
        regBtnRegister.setOnClickListener(this)
        regBtnLogin.setOnClickListener(this)
        if (regAdmin.isChecked){
            strlevel ="admin"
        }else{
            strlevel ="mahasiswa"
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.regAdmin -> strlevel = "admin"
            R.id.regMahasiswa -> strlevel = "mahasiswa"
            R.id.regBtnLogin -> login()
            R.id.regBtnRegister -> startActivity<RegisterActivity>()
        } }

    private fun login() {
        stremail = regUsername.text.toString()
        strpassword = regPass.text.toString()
        if (TextUtils.isEmpty(stremail)) {
            regUsername.error = "email tidak boleh kosong"
            regUsername.requestFocus()
        } else if (TextUtils.isEmpty(strpassword)) {
            regPass.error = "password tidak boleh kosong"
            regPass.requestFocus()

        }  else if (strpassword!!.length < 6) {
            regPass.error = "password harus lebih dari 6 karakter"
            regPass.requestFocus()
        }  else {
            proseslogin()
        }
    }

    private fun proseslogin() {
        val dialog = progressDialog(
            message = "proses login user"
            , title = "loading . . . "
        )
        val api = InitRetrofit.getInstance()
        val responlogin = api.loginuser(
            stremail.toString(), strpassword.toString()
        )
        //panggil callback
        responlogin.enqueue(object : Callback<ResponseRegisterLogin>{

            override fun onResponse(call: Call<ResponseRegisterLogin>, response: Response<ResponseRegisterLogin>) {
         dialog.dismiss()
           var result = response.body()?.result
                var msg = response.body()?.msg
                if (result!!.equals(1)){
                    startActivity<MainActivity>()
                    toast(msg.toString())
                    finish()

                }else{
                    toast(msg.toString())
                }
            }

            override fun onFailure(call: Call<ResponseRegisterLogin>, t: Throwable) {
           dialog.dismiss()
            toast("gagal koneksi "+t.localizedMessage)
            }

        })
    }
}
