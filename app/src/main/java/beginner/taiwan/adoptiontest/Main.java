package beginner.taiwan.adoptiontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {
    Button mButton;
    String buttontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initID();
    }

    //ID統一管理
    public void initID() {
        mButton = (Button) findViewById(R.id.button);
    }

    //button點擊時執行
    public void onclick(View view) {
        buttontext = mButton.getText().toString();//取得button的文字
        Intent intent = new Intent(this, Country.class);//連結Country頁面
        intent.putExtra("name", buttontext);//傳送buttontext到country頁面
        startActivity(intent);//開始轉換頁面
        finish();//結束本頁

    }
}
