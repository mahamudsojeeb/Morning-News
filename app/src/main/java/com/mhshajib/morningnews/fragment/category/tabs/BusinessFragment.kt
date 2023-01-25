package com.mhshajib.morningnews.fragment.category.tabs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mhshajib.morningnews.R
import com.mhshajib.morningnews.adapter.CardAdapter
import com.mhshajib.morningnews.databinding.FragmentBusinessBinding
import com.mhshajib.morningnews.global.Global
import com.mhshajib.morningnews.viewmodel.NewsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BusinessFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private var _binding: FragmentBusinessBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBusinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        Global.category = "business"
        viewModel.readNewsFromLocal()

        val recycler = binding.cardNewsRecycler
        recycler.setHasFixedSize(true)
        viewModel.readNews.observe(viewLifecycleOwner) {
//            Log.d("TAG", "onResume:  ${it.size}")
            if (it.isEmpty()) {
                viewModel.getNewsFromRemote()
                recycler.adapter?.notifyDataSetChanged()
            }
            recycler.adapter = CardAdapter(it, viewModel)
        }
    }
}