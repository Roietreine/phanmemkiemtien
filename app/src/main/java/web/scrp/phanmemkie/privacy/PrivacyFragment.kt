package web.scrp.phanmemkie.privacy

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import web.scrp.phanmemkie.R
import web.scrp.phanmemkie.common.model.DataModel
import web.scrp.phanmemkie.databinding.FragmentPrivacyBinding
import web.scrp.phanmemkie.privacy.viewmodel.PrivacyViewModel

class PrivacyFragment : Fragment(), PrivacyListener {

    private var _binding: FragmentPrivacyBinding? = null
    private val binding get() = _binding!!
    private var privacyData = PrivacyViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPrivacyBinding.inflate(inflater,container,false)
        val rootView = (binding.root)
        privacyData = ViewModelProvider(this)[PrivacyViewModel::class.java]

        privacyData.termiFun()
        val adpts = PrivacyAdapter(this)
        privacyData.trmNf.observe(viewLifecycleOwner){
            if(it != null){
                adpts.setAdapter(it)
                binding.privacyRecycler.apply {
                    setHasFixedSize(true)
                    adapter = adpts
                }
            }
        }
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(data: DataModel) {
        val dialog = Dialog(requireContext())
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_show_all)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val title = dialog.findViewById<TextView>(R.id.title_view_all)
        val desc = dialog.findViewById<TextView>(R.id.desc_view_all)

        title.text = data.contentTitle
        desc.text = data.contentDesc
        dialog.show()
    }
}