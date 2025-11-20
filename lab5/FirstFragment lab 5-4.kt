package com.example.lab5

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    // 優化 1: 使用 Companion Object 定義靜態常數 TAG
    // 這樣就不會每個地方都手打 "FirstFragment"，避免打錯字且好維護
    companion object {
        private const val TAG = "FirstFragment"
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
        // 載入佈局 (注意：如果是 ViewBinding 這裡寫法會不同，但目前這樣寫是標準的)
        return inflater.inflate(R.layout.fragment_first, container, false)
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

    // 優化 2: 封裝 Log 函式
    // 將 Log.e 改為 Log.d (Debug)，並統一管理，保持程式碼乾淨
    private fun log(event: String) {
        Log.d(TAG, event)
    }
}