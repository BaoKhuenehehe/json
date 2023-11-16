package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/** @noinspection ALL*/
public class MainActivity extends AppCompatActivity {
    ListView listview;
    ArrayList<DocData> mangdocdata;
    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView)findViewById(R.id.list) ;
        mangdocdata = new ArrayList<DocData>();
        new READJSON().execute("https://dummyjson.com/users");
    }
    private class READJSON extends AsyncTask<String, Void, String>{
        protected String doInBackground(String... strings){
            StringBuilder content= new StringBuilder();
            try {
                URL url= new URL(strings[0]);
                InputStreamReader inputStreamReader= new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine())!= null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();
        }
        protected void onPostExecute (String s){
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("users");
                for (int i =0; i <array.length();i++){
                    JSONObject subobject = array.getJSONObject(i);
                    String firstName = subobject.getString("firstName");
                    String LastName = subobject.getString("lastName");
                    String phone = subobject.getString("phone");
                    String img =subobject.getString("image");
                    JSONObject addressob = subobject.getJSONObject("address");
                    String address = addressob.getString("address");
                    mangdocdata.add(new DocData(firstName +" " + LastName,img,phone,address));
                }
                adapter = new MyListAdapter(MainActivity.this, android.R.layout.simple_list_item_1,mangdocdata);
                listview.setAdapter(adapter);
                super.onPostExecute(s);
                adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                throw new RuntimeException(e);

            }


        }

    }
}