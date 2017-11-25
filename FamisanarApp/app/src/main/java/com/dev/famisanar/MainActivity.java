package com.dev.famisanar;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.brouding.blockbutton.BlockButton;
import com.dev.Adapter.ListAdapter;
import com.dev.Interfaces.RedditApi;
import com.dev.model.Child.Children;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by juan.vanegas on 23/11/2017.
 */
public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://www.reddit.com/reddits/";
    private static final String TAG = "MainActivity";
    private Retrofit retrofit;
    private RedditApi redditAPI;
    private BlockButton b;
    private List<Children> childrenList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();

        setupRecyclerView();
        initRetrofit();


        refresh();


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();

            }
        });

    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        redditAPI = retrofit.create(RedditApi.class);
    }

    private void init() {
        b = (BlockButton) findViewById(R.id.button);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    private void refresh() {
        if (isInternetAvailable(this)) {
            refreshFeed();
        } else {


            Toast toast = Toast.makeText(this, "Sin conexión a internet", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private void refreshFeed() {

        Call<Feed> call = redditAPI.getData();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                ArrayList<Children> childrenList = response.body().getData().getChildren();
                mAdapter = new ListAdapter(childrenList);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) return false;

        switch (activeNetwork.getType()) {
            case ConnectivityManager.TYPE_WIFI:
                if ((activeNetwork.getState() == NetworkInfo.State.CONNECTED ||
                        activeNetwork.getState() == NetworkInfo.State.CONNECTING) &&
                        isInternet())
                    return true;
                break;
            case ConnectivityManager.TYPE_MOBILE:
                if ((activeNetwork.getState() == NetworkInfo.State.CONNECTED ||
                        activeNetwork.getState() == NetworkInfo.State.CONNECTING) &&
                        isInternet())
                    return true;
                break;
            default:
                return false;
        }
        return false;
    }

    private static boolean isInternet() {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
