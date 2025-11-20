package com.example.lab4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecActivity : AppCompatActivity() {

    // 優化1：將 UI 元件宣告在類別層級
    private lateinit var edDrink: TextView
    private lateinit var rgSugar: RadioGroup
    private lateinit var rgIce: RadioGroup
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sec)

        // 處理視窗邊距 (Boilerplate)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Step6：綁定所有元件
        edDrink = findViewById(R.id.edDrink)
        rgSugar = findViewById(R.id.rgSugar)
        rgIce = findViewById(R.id.rgIce)
        btnSend = findViewById(R.id.btnSend)

        // Step7：設定點擊事件
        btnSend.setOnClickListener {
            // 取得目前輸入的狀態
            val drinkName = edDrink.text.toString()
            val selectedSugarId = rgSugar.checkedRadioButtonId
            val selectedIceId = rgIce.checkedRadioButtonId

            // 優化2：完整的防呆機制 (防止使用者漏填導致閃退)
            when {
                drinkName.isEmpty() -> {
                    showToast("請輸入飲料名稱")
                }
                selectedSugarId == -1 -> {
                    showToast("請選擇甜度")
                }
                selectedIceId == -1 -> {
                    showToast("請選擇冰塊")
                }
                else -> {
                    // Step9：都驗證通過了，才去抓取 RadioButton 的文字
                    // findViewById 有可能找不到(理論上不會，因為上面檢查過了)，但為了安全還是用 ?.text
                    val sugarText = findViewById<RadioButton>(selectedSugarId)?.text?.toString() ?: "無"
                    val iceText = findViewById<RadioButton>(selectedIceId)?.text?.toString() ?: "無"

                    // 建立 Bundle
                    val b = bundleOf(
                        "drink" to drinkName,
                        "sugar" to sugarText,
                        "ice" to iceText
                    )

                    // Step10 & 11：回傳結果並結束
                    val intent = Intent().putExtras(b)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    // 小工具：簡化 Toast 的寫法
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}