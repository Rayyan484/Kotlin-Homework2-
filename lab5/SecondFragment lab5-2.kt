package com.example.lab5

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    // 優化 1：定義靜態常數 TAG
    // 類似 Java 的 "static final String TAG = ...;"
    companion object {
        private const val TAG = "SecondFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        log("onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        log("onCreateView")
        // 這裡假設你有建立 fragment_second.xml
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        log("onDetach")
    }

    // 優化 2 & 3：封裝 Log 函式 + 改用 Log.d
    private fun log(event: String) {
        Log.d(TAG, event)
    }
}