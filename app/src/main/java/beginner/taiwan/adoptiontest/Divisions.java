package beginner.taiwan.adoptiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Divisions extends AppCompatActivity {
    String getintentstring = "";
    ArrayList<String> loadDivisions = new ArrayList<>();
    TextView mTextView;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        Getdata();
        initID();
        tv();
        lv();
    }
    //取得上一頁傳來的資訊
    public void Getdata() {
        Bundle bundle = getIntent().getExtras();//取得資訊
        if (bundle != null) {
            getintentstring = bundle.getString("country");//把取到的country放進getintentstring;
            loadDivisions = bundle.getStringArrayList("divisions");//把取到的divisions放進loadDivisions
        }
    }

    //ID統一管理
    public void initID() {
        mTextView = (TextView) findViewById(R.id.textview);
        mListView = (ListView) findViewById(R.id.listview);
    }

    //處理mTextView的部分
    public void tv() {
        mTextView.setText(getintentstring);
    }

    public void lv() {
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,loadDivisions);
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();//更新畫面
    }

}
