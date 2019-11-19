package com.dicoding.picodiploma.submissionkeempat3.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.picodiploma.submissionkeempat3.entity.DataTv;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvViewModel extends ViewModel {

    private static final String API_KEY = "7c56e00359f5c13ed5413fa7650c047f";
    private MutableLiveData<ArrayList<DataTv>> listTv = new MutableLiveData<>();

    public void setTv(final String language){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<DataTv> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language="+ language;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String( responseBody );
                    JSONObject responseObject = new JSONObject( result );
                    JSONArray list = responseObject.getJSONArray( "results" );
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject film = list.getJSONObject( i );
                        DataTv dataTv = new DataTv( film );
                        listItems.add(dataTv);
                    }
                    listTv.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<DataTv>> getTv(){
        return listTv;
    }
}
