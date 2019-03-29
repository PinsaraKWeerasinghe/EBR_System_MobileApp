package com.example.spark;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context context;

    BackgroundTask(Context context){
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... voids) {
        String reg_url="http://10.0.2.2:8080/spark/mobileapp/userreg.php";
        String method=voids[0];
        if(method.equals("register")){
            String user_name=voids[1];
            String first_name=voids[2];
            String last_name=voids[3];
            String acc_no=voids[4];
            String mob_no=voids[5];
            String email=voids[6];
            String pass_word=voids[7];
            String con_pass_word=voids[8];
            String no=voids[9];
            String street=voids[10];
            String city=voids[11];
            String zip=voids[12];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("firstName","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")+"&"+
                        URLEncoder.encode("lastName","UTF-8")+"="+URLEncoder.encode(last_name,"UTF-8")+"&"+
                        URLEncoder.encode("accNo","UTF-8")+"="+URLEncoder.encode(acc_no,"UTF-8")+"&"+
                        URLEncoder.encode("mobNo","UTF-8")+"="+URLEncoder.encode(mob_no,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("pWord","UTF-8")+"="+URLEncoder.encode(pass_word,"UTF-8")+"&"+
                        URLEncoder.encode("cpWord","UTF-8")+"="+URLEncoder.encode(con_pass_word,"UTF-8")+"&"+
                        URLEncoder.encode("no","UTF-8")+"="+URLEncoder.encode(no,"UTF-8")+"&"+
                        URLEncoder.encode("street","UTF-8")+"="+URLEncoder.encode(street,"UTF-8")+"&"+
                        URLEncoder.encode("city","UTF-8")+"="+URLEncoder.encode(city,"UTF-8")+"&"+
                        URLEncoder.encode("zip","UTF-8")+"="+URLEncoder.encode(zip,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();
                Intent intent=new Intent(context,MainActivity.class);
                context.startActivity(intent);
                return "Registration Success.....";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
