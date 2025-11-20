package com.example.lab5

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    // 優化1：統一管理 Log 標籤
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 處理視窗邊距 (Boilerplate)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        log("onCreate")

        // 優化2：使用 Scope Function (apply) 來設定 ViewPager2
        // 這樣就不需要重複寫 "viewPager2.adapter = ...", "viewPager2.xxx = ..."
        findViewById<ViewPager2>(R.id.viewPager2).apply {
            // 設定 Adapter
            adapter = ViewPagerAdapter(supportFragmentManager, this@MainActivity.lifecycle)
            // 設定預載頁面數量
            offscreenPageLimit = 1
        }
    }

    override fun onRestart() {
        super.onRestart()
        log("onRestart")
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

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    // 優化3：封裝 Log 函式，並改用正確的 Debug 層級
    private fun log(msg: String) {
        Log.d(TAG, msg)
    }
}