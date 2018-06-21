package org.androidtown.testdb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    SharedPreference pref = getSharedPreference("KEY", MODE_PRIVATE);

    private final String TAG = "MainActivity";

    Button btn, listbtn, gotominebtn;
    TextView textView;
    EditText telephone_et, name_et;
    String telephone = "", name = "";

    ListView listview;
    ArrayList<RPostItem> postitem;
    RPostItem pi;
    MyListAdapter MyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.resulttv);

        telephone_et = (EditText) findViewById(R.id.telephone_et);
        name_et = (EditText)findViewById(R.id.name_et);

        postitem = new ArrayList<RPostItem>();
        pi = new RPostItem("송편1","김도형","naver.com"); postitem.add(pi); //desc/title/add
        pi = new RPostItem("치킨1","파이썬","daum.net"); postitem.add(pi); //desc/title/add
        MyAdapter = new MyListAdapter(this, R.layout.listitem, postitem);

        listview = (ListView)findViewById(R.id.listview_local);
        listview.setAdapter(MyAdapter);

        btn = (Button) findViewById(R.id.start_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telephone = telephone_et.getText().toString();
                if(telephone.length()<=1)
                    Log.d(TAG, "데이터를 입력하세요");
                else
                    ConnectServer();
            }
        });

        listbtn = (Button) findViewById(R.id.makelist_button);
        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = name_et.getText().toString();
                if(name.length() <= 1)
                    Toast.makeText(MainActivity.this, "name 데이터를 입력하세요", Toast.LENGTH_SHORT).show();
                else
                    ConnectServer_list();
            }
        });

        gotominebtn = (Button)findViewById(R.id.minepage_button);
        gotominebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MineList.class));
            }
        });
    }

    private void ConnectServer_list() {

        final String SIGNIN_URL = "http://13.209.48.149:8080/testdir/connection_test3.jsp";
        final String urlSuffix = "?title=" + name;
//        final String urlSuffix = "?telephone=" + telephone;
        Log.d("urlSuffix", urlSuffix);
        class SignupUser extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Log.d(TAG,s);
                if (s != null) {
                    try{
                        JSONArray jArr = new JSONArray(s);

                        for (int i = 0; i < jArr.length(); i++) {
                            pi = new RPostItem(jArr.getJSONObject(i).getString("description"),jArr.getJSONObject(i).getString("title"),
                                    jArr.getJSONObject(i).getString("address"));
                            postitem.add(pi); //desc/title/add
                            MyAdapter.notifyDataSetChanged();
                        }

                    }catch(Exception e){
                        e.printStackTrace();
                        Log.d(TAG, "onPostExecute에서 오류");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                BufferedReader bufferedReader = null;

                try {
                    HttpClient client = new DefaultHttpClient();  // 보낼 객체 만들기
                    HttpPost post = new HttpPost(SIGNIN_URL + urlSuffix);  // 주소 뒤에 데이터를 넣기

                    HttpResponse response = client.execute(post); // 데이터 보내기

                    BufferedReader bufreader = new BufferedReader(
                            new InputStreamReader(
                                    response.getEntity().getContent(), "utf-8"));

                    String line = null;
                    String page = "";

                    while ((line = bufreader.readLine()) != null) {
                        page += line;
                    }
                    Log.d(TAG, page);
                    return page;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        SignupUser su = new SignupUser();
        su.execute(urlSuffix);
    }

    private void ConnectServer(){

        final String SIGNIN_URL = "http://13.209.48.149:8080/testdir/connection_test2.jsp";
        //final String urlSuffix = "?title=" + title + "&telephone=" + telephone;
        final String urlSuffix = "?telephone=" + telephone;
        Log.d("urlSuffix", urlSuffix);
        class SignupUser extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Log.d(TAG,s);
                if (s != null) {
                    try{
                        JSONArray jArr = new JSONArray(s);;
                        JSONObject json = new JSONObject();

                        for (int i = 0; i < jArr.length(); i++) {
                            json = jArr.getJSONObject(i);
                            String result_tv = "TITLE : "+json.getString("title")+"\n"
                                    +"TELEPHONE : "+json.getString("telephone")+"\n"
                                    +"LINK : "+json.getString("link")+"\n"
                                    +"DESCRIPTION : "+json.getString("description")+"\n"
                                    +"ADDRESS : "+json.getString("address")+"\n"
                                    +"MAPX : "+json.getString("mapX")+"\n"
                                    +"MAPY : "+json.getString("mapY");
                            textView.setText(result_tv);
                            //textView.setText(json.getString("link"));
                        }

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                BufferedReader bufferedReader = null;

                try {

                    HttpClient client = new DefaultHttpClient();  // 보낼 객체 만들기
                    HttpPost post = new HttpPost(SIGNIN_URL + urlSuffix);  // 주소 뒤에 데이터를 넣기

                    HttpResponse response = client.execute(post); // 데이터 보내기

                    BufferedReader bufreader = new BufferedReader(
                            new InputStreamReader(
                                    response.getEntity().getContent(), "utf-8"));

                    String line = null;
                    String page = "";

                    while ((line = bufreader.readLine()) != null) {
                        page += line;
                    }
                    Log.d(TAG, page);
                    return page;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        SignupUser su = new SignupUser();
        su.execute(urlSuffix);
    }
}