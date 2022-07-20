package com.work.theIsle.jetpack.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.work.theIsle.R
import com.work.theIsle.jetpack.vm.SeekBarViewModel

class SecondFragment : Fragment() {
    private var root: View? = null
    private lateinit var seekBar: SeekBar
    private lateinit var vm: SeekBarViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_second, container, false)
            seekBar = root!!.findViewById(R.id.sb_second)
            vm = ViewModelProvider(activity!!).get(SeekBarViewModel::class.java)
            //vm = ViewModelProvider(activity!!,ViewModelProvider.AndroidViewModelFactory(activity!!.application)).get(SeekBarViewModel::class.java)
            vm.getProgress().observe(viewLifecycleOwner) {
                seekBar.progress = it
            }
            seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                      vm.getProgress().value = progress
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            })
        }
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}