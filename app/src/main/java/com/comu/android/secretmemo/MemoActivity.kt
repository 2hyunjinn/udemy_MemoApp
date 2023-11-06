package com.comu.android.secretmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MemoActivity : AppCompatActivity() {
    // 전역 변수 선언 - 메모 내용을 저장합니다.
    private var memoContent: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activity_memo 레이아웃을 화면에 표시합니다.
        setContentView(R.layout.activity_memo)

        // EditText 및 Button을 참조합니다.
        val editText = findViewById<EditText>(R.id.memo_editText_et)
        val checkButton = findViewById<Button>(R.id.memo_buttonNext)

        // '확인' 버튼을 클릭했을 때의 동작
        checkButton.setOnClickListener {
            val memoText = editText.text.toString()

            // AlertDialog를 통해 입력된 내용 확인
            AlertDialog.Builder(this)
                .setTitle("내용 확인")
                .setMessage("입력된 내용이 맞습니까?\n$memoText")
                .setPositiveButton("확인") { _, _ ->
                    // MainActivity로 이동
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        // '다시쓰기' 버튼 참조
        val retryButton = findViewById<Button>(R.id.memo_buttonRetry)

        // '다시쓰기' 버튼 클릭시 onRestart() 메서드 직접 호출
        retryButton.setOnClickListener {
            this.onRestart() // onRestart() 메서드를 직접 호출합니다.
        }
    }

    override fun onResume() {
        super.onResume()
        val editText = findViewById<EditText>(R.id.memo_editText_et)

        // 이전에 onPause()에서 저장한 memoContent로 EditText 내용 설정
        if (memoContent.isNotEmpty()) {
            editText.setText(memoContent)
        }
    }

    override fun onPause() {
        super.onPause()
        // EditText에 입력된 내용을 memoContent에 저장
        memoContent = findViewById<EditText>(R.id.memo_editText_et).text.toString()
    }

    override fun onRestart() {
        super.onRestart()

        // EditText 참조
        val editText = findViewById<EditText>(R.id.memo_editText_et)

        // 다시 작성할 것인지 묻는 AlertDialog 표시
        AlertDialog.Builder(this)
            .setTitle("다시 작성할까요?\n")
            .setPositiveButton("네") { _, _ ->
                // '네'를 선택하여 다시 작성하지 않을 시, memoContent 및 EditText 비우기
                memoContent = ""
                editText.text.clear()
            }
            .setNegativeButton("아니요") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}