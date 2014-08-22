package com.lutshe.emenu.wsclient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lutshe.emenu.R;
import com.lutshe.emenu.model.Data;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.concurrent.ExecutionException;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EBean
public class DataDeserializer {

    @ViewById(R.id.progress)
    ProgressBar progressBar;

    @RootContext
    Context context;

    public Data load(String url) {
        String xmlData = null;
        Data result = null;
        try {
            xmlData = new HttpRequestAsyncTask().execute(url).get();
            Serializer serializer = new Persister();
            Reader reader = new StringReader(xmlData);
            result = serializer.read(Data.class, reader, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Data readFile(String inFile) {
        String tContents = "";
        Data result = null;
        try {
            InputStream stream = context.getAssets().open(inFile);
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Serializer serializer = new Persister();
        Reader reader = new StringReader(tContents);
        try {
            result = serializer.read(Data.class, reader, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Created by Arsen Adzhiametov on 7/31/13.
     */
    public class HttpRequestAsyncTask extends AsyncTask<String, Integer, String> {

        int progress_status;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress_status = 0;
        }

        @Override
        protected String doInBackground(String... params) {
            String result = retrieve(params[0]);
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        private String retrieve(String url) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(url);
            try {
                HttpResponse getResponse = client.execute(getRequest);

                final int statusCode = getResponse.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    return null;
                }

                HttpEntity getResponseEntity = getResponse.getEntity();
                if (getResponseEntity != null) {
                    return EntityUtils.toString(getResponseEntity);
                }

            } catch (IOException e) {
                Log.e("12345", "Error for URL " + url, e);
                getRequest.abort();
            }
            return null;
        }
    }
}
