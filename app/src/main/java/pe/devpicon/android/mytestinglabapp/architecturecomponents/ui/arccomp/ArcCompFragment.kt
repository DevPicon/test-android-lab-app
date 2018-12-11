package pe.devpicon.android.mytestinglabapp.architecturecomponents.ui.arccomp

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.devpicon.android.mytestinglabapp.R

class ArcCompFragment : Fragment() {

    companion object {
        fun newInstance() = ArcCompFragment()
    }

    private lateinit var viewModel: ArcCompViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.arc_comp_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArcCompViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
