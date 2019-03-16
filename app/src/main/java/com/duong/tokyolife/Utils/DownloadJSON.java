package com.duong.tokyolife.Utils;

import android.net.Uri;
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
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadJSON extends AsyncTask<String,Void,String> {

    String duongdan;
    List<HashMap<String,String>> attri;
    StringBuilder data;
    boolean method=true;
    String dataJson="";

    public DownloadJSON(String duongdan){
        this.duongdan=duongdan;
        this.method=true;
    }

    public DownloadJSON(String duongdan, List<HashMap<String,String>> attri){
        this.attri=attri;
        this.duongdan=duongdan;
        this.method=false;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(duongdan);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if(!method){
                dataJson=methodPost(connection);
            } else{
                dataJson=methodGet(connection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataJson;
    }

    public String methodGet(HttpURLConnection httpURLConnection){
        try {

            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(streamReader);

            data = new StringBuilder();
            String line = "";
            while ((line=reader.readLine())!=null){
                data.append(line);
            }
            reader.close();
            streamReader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    public String methodPost(HttpURLConnection connection){
        String data="";
        String key="";
        String value="";
        try {

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();

            int count=attri.size();
            for (int i=0;i<count;i++){

                for (Map.Entry<String,String> entry : attri.get(i).entrySet()){
                    key=entry.getKey();
                    value=entry.getValue();
                }
                builder.appendQueryParameter(key,value);
            }

            String query = builder.build().getEncodedQuery();

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(streamWriter);

            writer.write(query);
            writer.flush();
            data=methodGet(connection);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
