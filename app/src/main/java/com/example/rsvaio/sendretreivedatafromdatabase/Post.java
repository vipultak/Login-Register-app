package com.example.rsvaio.sendretreivedatafromdatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by rsvaio on 2/9/2016.
 */
public class Post extends AsyncTask<String,Void,String>{
    AlertDialog alertDialog;
    Context ctx;
    Post(Context ctx)
    {
        this.ctx =ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }

    @Override
    protected String doInBackground(String... params) {
        String post_url = "http://rsvipul.comxa.com/dbConnect.php";
        String name = params[0];
        String address = params[1];
        try {
            URL url = new URL(post_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream OS =httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

            String data =URLEncoder.encode("name","UTF-8") +"="+ URLEncoder.encode(name,"UTF-8")+"&"+URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream IS = httpURLConnection.getInputStream();
            IS.close();
            httpURLConnection.disconnect();
            return "Registration Success...";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if(s.equals("Registration Success...")){
            Toast.makeText(ctx,s,Toast.LENGTH_LONG).show();
        }
        else{
            alertDialog.setMessage(s);
            alertDialog.show();
        }
    }
}
