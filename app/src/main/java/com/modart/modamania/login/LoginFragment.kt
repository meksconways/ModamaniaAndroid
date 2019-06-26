package com.modart.modamania.login

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.irozon.alertview.AlertActionStyle
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertView
import com.irozon.alertview.objects.AlertAction

import com.modart.modamania.R
import com.modart.modamania.base.getAppComponent
import com.modart.modamania.main.MainActivityVM
import com.modart.modamania.util.gone
import com.modart.modamania.util.invisible
import com.modart.modamania.util.visible
import com.modart.modamania.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.getAppComponent().inject(this)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: LoginViewModel
    private lateinit var mainVM: MainActivityVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)
        mainVM = ViewModelProviders.of(activity!!)[MainActivityVM::class.java]
        observeVM()
        btnSignUp.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        btnLogin.setOnClickListener {
            if (edtUsername.text.isNullOrEmpty()){
                edtUsername.error = "Kullanıcı Adı Boş Olamaz"
                return@setOnClickListener
            }

            if (edt_Password.text.isNullOrEmpty()){
                edt_Password.error = "Parola Boş Olamaz"
                return@setOnClickListener
            }

            viewModel.login(edtUsername.text.toString(),edt_Password.text.toString())
        }

    }

    private fun observeVM() {
        viewModel.getPageLoading().observe(this, Observer {
            if (it) progress.visible() else progress.invisible()
        })

        viewModel.getRouteMain().observe(this, Observer {
            if (it){
                mainVM.setMainScreenState(true)
            }

        })

        viewModel.getAlertMessage().observe(this, Observer{
            it?.let { message ->

                viewModel.clearAlert()

                val alert = AlertView("Oops!", message, AlertStyle.DIALOG)
                alert.addAction(AlertAction("Tamam", AlertActionStyle.NEGATIVE) {
                    viewModel.clearAlert()
                })
                alert.show(this.activity as AppCompatActivity)

            }
        })
    }

}
