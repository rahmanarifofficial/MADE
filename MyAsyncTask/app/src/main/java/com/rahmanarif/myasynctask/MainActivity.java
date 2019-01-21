package com.rahmanarif.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements MyAsyncCallback {
    private TextView tv_status, tv_description;
    final static String INPUT_STRING = "Halo ini demo Asynctask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_status = findViewById(R.id.tv_status);
        tv_description = findViewById(R.id.tv_desc);

        DemoAsync demoAsync = new DemoAsync(this);
        demoAsync.execute(INPUT_STRING);
    }


    @Override
    public void onPreExecute() {
        tv_status.setText("onPreExecute");
        tv_description.setText(INPUT_STRING);
    }

    @Override
    public void onPostExecute(String text) {
        tv_status.setText("onPostExecute");
        if (text!=null){
            tv_description.setText(text);
        }
    }

    private static class DemoAsync extends AsyncTask<String, Void, String> {
        static final String LOG_ASYNC = "Demo Async";
        WeakReference<MyAsyncCallback> myListener;

        DemoAsync(MyAsyncCallback myListener) {
            this.myListener = new WeakReference<>(myListener);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(LOG_ASYNC, "status : onPostExecute");
            MyAsyncCallback myListener = this.myListener.get();
            if (myListener != null) {
                myListener.onPostExecute(s);
            }
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            Log.d(LOG_ASYNC, "status : onPreExecute");
            MyAsyncCallback myListener = this.myListener.get();
            if (myListener != null) {
                myListener.onPreExecute();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(LOG_ASYNC, "status : doInBackground");
            String output = null;
            try {
                String input = strings[0];
                output = input + " Selamat Belajar!!";
                Thread.sleep(5000);
            } catch (Exception e) {
                Log.d(LOG_ASYNC, e.getMessage());
            }
            return output;
        }
    }
}
