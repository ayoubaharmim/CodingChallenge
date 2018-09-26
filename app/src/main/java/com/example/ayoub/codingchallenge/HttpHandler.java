package com.example.ayoub.codingchallenge;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {


        public HttpHandler(){

        }

        public String makeServiceCall(String link){

            String response = null;

            try {

                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                System.out.println("> GETTING DATA ...");
                //reading the response
                InputStream in = new BufferedInputStream(connection.getInputStream());

                response = convertToString(in);
                System.out.println("> CONVERSION : "+response);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        //convert an input to a string
        private String convertToString(InputStream in) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            try {
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } // end of catch

            return stringBuilder.toString();

        }//end of the method



}
