package web.scrp.phanmemkie.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import web.scrp.phanmemkie.R
import web.scrp.phanmemkie.databinding.FragmentMainBinding

class MainFragment : Fragment(),View.OnClickListener {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMainBinding.inflate(inflater,container,false)
        val rootView = (binding.root)
        onClickMain()
        lottieFun()
        return rootView

    }

    private fun onClickMain(){

        binding.webView1.setOnClickListener(this)
        binding.webView2.setOnClickListener(this)
        binding.webView3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v){
            binding.webView1 -> findNavController().navigate(R.id.action_mainFragment_to_homeFragment)
            binding.webView2 -> findNavController().navigate(R.id.action_mainFragment_to_indexFragment)
            binding.webView3 -> findNavController().navigate(R.id.action_mainFragment_to_privacyFragment)
      }
    }

    private fun lottieFun(){
        binding.lottieAnimationView.playAnimation()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}