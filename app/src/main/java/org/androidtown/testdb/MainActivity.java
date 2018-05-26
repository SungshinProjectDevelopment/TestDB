package org.androidtown.testdb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";

    Button btn ;
    TextView textView;
    EditText title_et, telephone_et;
    String title = "", telephone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.start_button);
        btn.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.resulttv);

        //title_et = (EditText) findViewById(R.id.title_et);
        telephone_et = (EditText) findViewById(R.id.telephone_et);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.start_button:
                //title = title_et.getText().toString();
                telephone = telephone_et.getText().toString();
                //if(title.length()<=1 || telephone.length()<=1){
                if(telephone.length()<=1)
                    Log.d(TAG, "데이터를 입력하세요");
                else
                    ConnectServer();
                break;
            default:
                break;
        }

    }
    private void ConnectServer(){

        final String SIGNIN_URL = "http://13.209.35.160:8080/testdir/connection_test2.jsp";
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
                            //textView.setText(result_tv);
                            textView.setText(json.getString("link"));
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