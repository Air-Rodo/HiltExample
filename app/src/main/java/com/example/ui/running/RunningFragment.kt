package com.example.ui.running

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hilt.databinding.FragmentRunningBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/20 16:36
 * Email: lijt@eetrust.com
 */
@AndroidEntryPoint
class RunningFragment:Fragment() {
    private lateinit var binding:FragmentRunningBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }
}