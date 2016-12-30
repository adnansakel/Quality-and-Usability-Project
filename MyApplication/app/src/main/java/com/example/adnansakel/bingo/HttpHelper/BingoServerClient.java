package com.example.adnansakel.bingo.HttpHelper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.adnansakel.bingo.Util.AppConstants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Adnan Sakel on 12/27/2016.
 */
public class BingoServerClient {
   // private static final String BASE_URL = "http://10.0.2.2:8080/queuedb/";//testing

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        //client.setBasicAuth("paksom","cheska");
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url, StringEntity jsonObjectStringentity, AsyncHttpResponseHandler responseHandler) {
        //client.setBasicAuth("paksom","cheska");
        //System.out.println("Url:"+getAbsoluteUrl(url));
        client.post(context,getAbsoluteUrl(url),jsonObjectStringentity,"application/json",responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setBasicAuth("paksom","cheska");
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return AppConstants.BASE_URL + relativeUrl;
    }
}
