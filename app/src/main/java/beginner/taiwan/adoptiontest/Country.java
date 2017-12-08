package beginner.taiwan.adoptiontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class Country extends AppCompatActivity {
    String getintentstring = "";
    TextView mTextView;
    ListView mListView;
    ArrayList<String> saveName = new ArrayList<>();
    ArrayList<String> saveDivisions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        Getdata();
        initID();
        tv();
        lv();
    }



    //取得上一頁傳來的資訊
    public void Getdata() {
        Bundle bundle = getIntent().getExtras();//取得資訊
        if (bundle != null) {
            getintentstring = bundle.getString("name");//把取到的name放進getintentstring;
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

    //處理mListView的部分
    public void lv() {
        ParseJsonData parseJsonData = new ParseJsonData();//呼叫解析
        String str = parseJsonData.ParseJsonData(this);//把Context代入，並回傳String資料
        /*
        由於原始資料是Json格式
        故在此開始解析Json
        使用google的套件Gson來解析
        在build.gradle中的dependencies加入下面文字
        compile 'com.google.code.gson:gson:2.8.2'

        Json的概念
        遇到{}就用JsonObject拆
        遇到[]就用JsonArray拆
        拆到要的資訊就用getAs系列來讀取
         */
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(str);//解析資料
         /*     main
        {"regions":[{"name":"中華民國","divisions":["臺北市","新北市",
         */
        JsonObject main = element.getAsJsonObject();
        /*      regions
        [{"name":"中華民國","divisions":["臺北市","新北市",
         */
        //加上final的原因，在mListView.setOnItemClickListener會用到這邊資訊
        final JsonArray regions = main.getAsJsonArray("regions");
        for (int i = 0; i < regions.size(); i++) {
            JsonObject country = regions.get(i).getAsJsonObject();//這邊有好幾層，用get(i)來分別取各層資訊
            String name = country.get("name").getAsString();//取出title為name的資訊，也就是國家名稱
            saveName.add(name);//放進Arraylist供ArrayAdapter使用

        }
        /*
        ArrayAdapter(Context,ListView顯示型態,ListView顯示內容);
        ListView要顯示兩個內容要使用  SimpleAdapter
        ListView要顯示自訂內容要使用  BaseAdapter
         */
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, saveName);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int serious, long l) {
                saveDivisions.clear();//清除舊有的divisions
                /*
                選到第幾項，就讀取第幾項下面的divisions資訊
                 */

                JsonArray divisions = regions.get(serious).getAsJsonObject().getAsJsonArray("divisions");
                for (int j =0;j<divisions.size();j++)
                {
                    //把divisions全部寫進去saveDivisions供下一個頁面使用
                    saveDivisions.add(divisions.get(j).getAsString());
                    System.out.println(saveDivisions);
                }
                Intent intent = new Intent(Country.this, Divisions.class);
                intent.putExtra("country",saveName.get(serious));
                intent.putStringArrayListExtra("divisions", saveDivisions);
                startActivity(intent);
            }
        });
    }
}
