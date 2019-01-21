package com.rahmanarif.myasynctask;

public interface MyAsyncCallback {
    void onPreExecute();
    void onPostExecute(String text);
}
