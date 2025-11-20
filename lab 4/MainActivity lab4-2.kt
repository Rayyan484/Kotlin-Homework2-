package com.example.lab4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // 優化1：將 UI 元件宣告在類別層級，方便在不同地方存取
    private lateinit var tvMeal: TextView
    private lateinit var btnChoice: Button

    // Step1：宣告 ActivityResultLauncher
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Step12：判斷回傳結果是否成功
        if (result.resultCode == Activity.RESULT_OK) {
            // Step13：使用 Scope Function (let) 讓程式碼更整潔
            result.data?.let { intent ->
                // 優化2：使用 ?: (Elvis Operator) 處理空值，避免螢幕出現 "null"
                val drink = intent.getStringExtra("drink") ?: "未選擇"
                val sugar = intent.getStringExtra("sugar") ?: "未選擇"
                val ice = intent.getStringExtra("ice") ?: "未選擇"

                // Step14：更新畫面 (因為上面已經宣告過 tvMeal，這裡直接用)
                tvMeal.text = "飲料：$drink\n\n甜度：$sugar\n\n冰塊：$ice"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 處理系統狀態列高度 (Boilerplate code)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Step2：一次性綁定所有元件
        tvMeal = findViewById(R.id.tvMeal)
        btnChoice = findViewById(R.id.btnChoice)

        // Step3：設定按鈕事件
        btnChoice.setOnClickListener {
            // Step4 & 5：切換頁面
            val intent = Intent(this, SecActivity::class.java)
            startForResult.launch(intent)
        }
    }
}