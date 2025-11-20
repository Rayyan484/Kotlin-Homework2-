package com.example.lab6

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // 建立一個擴充功能 (Extension Function) 來簡化 Toast 的呼叫
    // 在任何 Activity 或 Context 內都可以直接呼叫 showToast("訊息")
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 啟用邊到邊顯示 (讓內容延伸到系統列後方，如狀態列和導航列)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 處理系統列內邊距 (Insects)，確保內容不會被狀態列或導航列遮擋
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- 元件初始化 (Component Initialization) ---
        // 定義元件變數，並通過 findViewById 取得元件
        val btnToast = findViewById<Button>(R.id.btnToast)
        val btnSnackBar = findViewById<Button>(R.id.btnSnackBar)
        val btnDialog1 = findViewById<Button>(R.id.btnDialog1)
        val btnDialog2 = findViewById<Button>(R.id.btnDialog2)
        val btnDialog3 = findViewById<Button>(R.id.btnDialog3)

        // 建立要顯示在列表上的字串陣列
        // 最佳實務建議：將這些字串定義在 res/values/strings.xml 中，以支援多國語言
        val item = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

        // --- 設定按鈕的點擊事件 (Set Click Listeners) ---

        // 1. 預設 Toast 訊息
        btnToast.setOnClickListener {
            // 呼叫 showToast 擴充功能方法，顯示一個短暫的 Toast 訊息
            showToast("預設 Toast") // 建議使用 getString(R.string.default_toast)
        }

        // 2. 帶有動作按鈕的 Snackbar 訊息
        btnSnackBar.setOnClickListener {
            // 建立 Snackbar 物件，傳入 View、訊息和持續時間
            Snackbar.make(it, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
                // 設定 Snackbar 動作按鈕的文字與點擊事件
                .setAction("按鈕") {
                    // 點擊動作按鈕後，顯示回應 Toast
                    showToast("已回應")
                }.show() // 顯示 Snackbar
        }

        // 3. 標準按鈕式 AlertDialog (有三個按鈕：中、左、右)
        btnDialog1.setOnClickListener {
            // 使用 AlertDialog.Builder 並利用 Kotlin 的 apply 區塊簡化設定
            AlertDialog.Builder(this).apply {
                // 設定標題
                setTitle("按鈕式 AlertDialog")
                // 設定內容
                setMessage("AlertDialog 內容")
                
                // 設定正向按鈕 (通常是主要的確認動作)
                setPositiveButton("右按鈕") { _, _ ->
                    // 點擊後執行動作：顯示 Toast 訊息
                    showToast("右按鈕")
                }
                
                // 設定負向按鈕 (通常是取消或次要動作)
                setNegativeButton("中按鈕") { _, _ ->
                    showToast("中按鈕")
                }

                // 設定中性按鈕 (通常是忽略或幫助動作)
                setNeutralButton("左按鈕") { _, _ ->
                    showToast("左按鈕")
                }
                
                // 創建並顯示 AlertDialog
            }.show()
        }

        // 4. 列表式 AlertDialog (點擊選項後立即回應)
        btnDialog2.setOnClickListener {
            AlertDialog.Builder(this).apply {
                // 設定標題
                setTitle("列表式 AlertDialog")
                
                // 設定列表項目及點擊事件
                // i 為被點擊項目的索引 (index)
                setItems(item) { dialogInterface, i ->
                    // 根據索引 i 取得對應的項目文字，並顯示 Toast 訊息
                    showToast("你選的是${item[i]}")
                    // 列表式對話框在點擊後會自動關閉
                }
            }.show()
        }

        // 5. 單選式 AlertDialog (顯示圓形單選按鈕，需點擊「確定」才回應)
        btnDialog3.setOnClickListener {
            // 宣告變數 position 用來記錄當前選擇的項目索引，預設為 0 (第一個項目)
            var position = 0
            
            AlertDialog.Builder(this).apply {
                // 設定標題
                setTitle("單選式 AlertDialog")
                
                // 設定單選列表項目、預設選擇的項目索引 (0) 及選擇事件
                setSingleChoiceItems(item, 0) { _, i ->
                    // 每當用戶選擇不同的項目時，更新變數 position 的值
                    position = i
                }
                
                // 設定確定按鈕
                setPositiveButton("確定") { _, _ ->
                    // 點擊確定後，根據最後記錄的 position 顯示 Toast 訊息
                    showToast("你選的是${item[position]}")
                }
                // (可選) 通常單選對話框會配備一個取消按鈕，例如 setNegativeButton("取消")
            }.show()
        }
    }
    // 注意：原程式碼中 showToast 方法被移到類別內部，作為私有函式，使其可以在 onCreate 中方便地被呼叫。
}