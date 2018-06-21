package org.androidtown.testdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AddMine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mine);

        Button addmine = (Button) findViewById(R.id.btn_addmine);
        addmine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 내용을 DB에 저장하고
                //ConnectServer();
                // finish();
            }
        });
    }
//
//    private void ConnectServer(){
//
//        final String SIGNIN_URL = "http://13.209.48.149:8080/testdir/connection_test_minelist.jsp";
//        //final String urlSuffix = "?title=" + title + "&telephone=" + telephone;
//        final String urlSuffix = "?telephone=" + telephone;
//        Log.d("urlSuffix", urlSuffix);
//        class SignupUser extends AsyncTask<String, Void, String> {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//
//                Log.d(TAG,s);
//                if (s != null) {
//                    try{
//                        JSONArray jArr = new JSONArray(s);;
//                        JSONObject json = new JSONObject();
//
//                        for (int i = 0; i < jArr.length(); i++) {
//                            json = jArr.getJSONObject(i);
//                            String result_tv = "TITLE : "+json.getString("title")+"\n"
//                                    +"TELEPHONE : "+json.getString("telephone")+"\n"
//                                    +"LINK : "+json.getString("link")+"\n"
//                                    +"DESCRIPTION : "+json.getString("description")+"\n"
//                                    +"ADDRESS : "+json.getString("address")+"\n"
//                                    +"MAPX : "+json.getString("mapX")+"\n"
//                                    +"MAPY : "+json.getString("mapY");
//                            textView.setText(result_tv);
//                            //textView.setText(json.getString("link"));
//                        }
//
//                    }catch(Exception e){
//                        e.printStackTrace();
//                    }
//                } else {
//                    Toast.makeText(MainActivity.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//                BufferedReader bufferedReader = null;
//
//                try {
//
//                    HttpClient client = new DefaultHttpClient();  // 보낼 객체 만들기
//                    HttpPost post = new HttpPost(SIGNIN_URL + urlSuffix);  // 주소 뒤에 데이터를 넣기
//
//                    HttpResponse response = client.execute(post); // 데이터 보내기
//
//                    BufferedReader bufreader = new BufferedReader(
//                            new InputStreamReader(
//                                    response.getEntity().getContent(), "utf-8"));
//
//                    String line = null;
//                    String page = "";
//
//                    while ((line = bufreader.readLine()) != null) {
//                        page += line;
//                    }
//                    Log.d(TAG, page);
//                    return page;
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//        }
//        SignupUser su = new SignupUser();
//        su.execute(urlSuffix);
//    }
}


