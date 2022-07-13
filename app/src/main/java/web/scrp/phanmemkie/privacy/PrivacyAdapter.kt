package web.scrp.phanmemkie.privacy

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import web.scrp.phanmemkie.common.model.DataModel
import web.scrp.phanmemkie.databinding.PrivacyViewBinding

class PrivacyAdapter (val listener : PrivacyListener) : RecyclerView.Adapter<PrivacyAdapter.AdapterHolder>() {

    private var privacyList = emptyList<DataModel>()
    class AdapterHolder (val adpts : PrivacyViewBinding) : RecyclerView.ViewHolder(adpts.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder = AdapterHolder(
        PrivacyViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
       with (holder){
           with(privacyList[position]){
               adpts.privacyTitle.text  = this.contentTitle
               adpts.privacyDesc.text = this.contentDesc
               adpts.privacyDesc.ellipsize = TextUtils.TruncateAt.MARQUEE
               adpts.privacyDesc.isSelected = true

               adpts.onclickCardview.setOnClickListener {
                   listener.onItemClick(this)
               }

           }
       }
    }

    override fun getItemCount(): Int {
        return privacyList.size
    }

    fun setAdapter (setAdapt : List<DataModel>){
        this.privacyList = setAdapt
    }
}