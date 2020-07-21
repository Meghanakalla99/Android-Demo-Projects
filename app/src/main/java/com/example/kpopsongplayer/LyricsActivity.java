package com.example.kpopsongplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteStatement;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LyricsActivity extends AppCompatActivity {

    InputStream in;
    InputStreamReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);

        Intent intent = getIntent();
        String lyricsUrl= "https://www.azlyrics.com/lyrics/bangtanboys/"+intent.getStringExtra("songName")+".html";

        WebView webView = (WebView) findViewById(R.id.wv);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(lyricsUrl);

    }
}
