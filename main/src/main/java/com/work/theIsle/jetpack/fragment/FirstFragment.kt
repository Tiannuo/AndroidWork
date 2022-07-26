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

class FirstFragment : Fragment() {
    private var root: View? = null
    private lateinit var seekBar: SeekBar
    private lateinit var vm: SeekBarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_first, container, false)
            seekBar = root!!.findViewById(R.id.sb_first)
            vm = ViewModelProvider(activity!!)[SeekBarViewModel::class.java]
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
        fun newInstance() = FirstFragment()
    }
}