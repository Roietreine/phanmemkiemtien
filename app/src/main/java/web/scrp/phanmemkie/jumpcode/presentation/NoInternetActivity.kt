package web.scrp.phanmemkie.jumpcode.presentation


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import web.scrp.phanmemkie.R

class NoInternetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)
    }
    companion object {
        fun createIntent(context: Context) = Intent(context, NoInternetActivity::class.java)
    }
}