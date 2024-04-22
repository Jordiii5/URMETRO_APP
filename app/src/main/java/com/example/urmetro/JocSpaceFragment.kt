package com.example.urmetro

import android.graphics.Point
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myvideogame.view.GameView

class JocSpaceFragment : Fragment() {


    lateinit var gameView: GameView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val display = requireActivity().windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        gameView = GameView(requireContext(), size)
        return gameView
    }
}