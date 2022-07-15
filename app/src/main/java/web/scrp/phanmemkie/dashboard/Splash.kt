package web.scrp.phanmemkie.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import web.scrp.phanmemkie.R
import web.scrp.phanmemkie.jumpcode.presentation.NoInternetActivity
import web.scrp.phanmemkie.jumpcode.presentation.WebViewActivity
import web.scrp.phanmemkie.jumpcode.utils.UiState
import web.scrp.phanmemkie.jumpcode.utils.isNetworkConnected
import web.scrp.phanmemkie.jumpcode.viewmodel.JumpCodeViewModel

class Splash : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[JumpCodeViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.urlResponse.observe(this) { state ->
            when (state) {
                is UiState.Success -> {
                    if (state.data.code == "0") {
                        Log.d("JumpCode", state.data.data?.jumpAddress ?: "")
                        if (state.data.data?.jump == true) {
                            toNextActivity(state.data.data.jumpAddress)
                        } else {
                            toNextActivity()
                        }
                    } else errorHandling(state.data.msg ?: "")
                }
                is UiState.Error -> errorHandling(state.exception.localizedMessage ?: "")
            }
        }
        if (isNetworkConnected()) viewModel.getJumpUrl(packageName)
        else toNoInternetActivity()
    }

    private fun toNextActivity(url: String? = null) {
        if(url != null){
            startActivity(
                WebViewActivity.createIntent(this, url))
        }else{
            startActivity(MainActivity.getStartIntent(this))
        }
        finish()
    }

    private fun errorHandling(message: String) {
        Log.d("Error", message)
        toNextActivity()
    }
    private fun toNoInternetActivity() {
        startActivity(NoInternetActivity.createIntent(this))
        finish()
    }
}