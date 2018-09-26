package com.example.ayoub.codingchallenge;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    ArrayList<Repository> repoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repoList = new ArrayList<>();
        //listView = findViewById(R.id.list);

        new GetRepo().execute();
    }

    private class GetRepo extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            HttpHandler httpHandler = new HttpHandler();

            //making request ang getting response from the url
            String response = httpHandler.makeServiceCall("https://api.github.com/search/repositories?q=created:%3E2017-10-22&sort=stars&order=desc");

           if(response != null){
               try{
                   JSONObject jsonObject = new JSONObject(response);
                   JSONArray jsonArray = jsonObject.optJSONArray("items");



                   for (int i=0; i< jsonArray.length(); i++){
                       JSONObject obj = jsonArray.getJSONObject(i);
                       JSONObject owner = obj.getJSONObject("owner");

                       String name = obj.getString("name");
                       String description = obj.getString("description");
                       String star = obj.getString("stargazers_count");
                       String username = owner.getString("login");
                       String avatar = owner.getString("avatar_url");
                       String date = obj.getString("created_at");

                       repoList.add(new Repository(name, description, star, username, avatar, date));

                   }

               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }else {
               Log.e("123", "Couldn't get json from server.");
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(getApplicationContext(),
                               "Couldn't get json from server. Check LogCat for possible errors!",
                               Toast.LENGTH_LONG).show();
                   }
               });
           }

            return null;
        }
    }
}
