package com.example.rsvaio.sendretreivedatafromdatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by rsvaio on 2/10/2016.
 */
public class Login extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context ctx;
    Login(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Checking Information...");
    }

    @Override
    protected String doInBackground(String... params) {
        String login_name = params[0];
        String login_pass = params[1];
        try {
            URL url = new URL("http://rsvipul.comxa.com/dbRetreive.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();


            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String response = "";
            String line  = "";
            while ((line = bufferedReader.readLine())!=null)
            {
                response+= line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return response;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
