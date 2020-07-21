package com.example.kpopsongplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp ;
    Button b1, b2;
    Switch s;
    int pos;
    public String []songs= {"Black Swan", "Blood Sweat and Tears" , "Boy in Luv", "Boy With Luv", "Butterfly", "Danger", "Dionysus", "DNA","Fake Love", "Dope", "I Need U", "Mic Drop","My Time", "Not Today", "Run", "Spring Day", "Tomorrow"};
    ListView lv;
    SeekBar sb, sb2;
    AudioManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1= (Button) findViewById(R.id.button1);
        b2= (Button) findViewById(R.id.button2);
        s= (Switch) findViewById(R.id.switch1);
        lv= (ListView) findViewById(R.id.listview1);
        mp= new MediaPlayer();
        sb= (SeekBar) findViewById(R.id.seekbar1);
        sb2= (SeekBar) findViewById(R.id.seekbar2);

        am= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol= am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVol= am.getStreamVolume(AudioManager.STREAM_MUSIC);

        sb.setMin(0);
        sb2.setMax(maxVol);
        sb2.setProgress(currVol);

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mp.setLooping(isChecked);
            }
        });

        listinit();
        seekbarinit();

    }
    public void restart (View v){
        mp.seekTo(0);
        mp.start();
        sb.setProgress(mp.getCurrentPosition());
    }

    public void pause(View v){
        if(mp.isPlaying())
            mp.pause();
    }

    public void resume (View v){
        mp. start();
    }

    public void listinit(){
        final ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songs);
        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                pos= position;

                mp.release();

                Toast.makeText(MainActivity.this, songs[position], Toast.LENGTH_SHORT).show();

                switch (position){
                    case 0:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.blackswan);
                        break;
                    case 1:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.bloodsweattears);
                        break;
                    case 2:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.boyinluvsangnamja);
                        break;
                    case 3:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.boywithluv);
                        break;
                    case 4:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.butterfly);
                        break;
                    case 5:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.danger);
                        break;
                    case 6:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.dionysus);
                        break;
                    case 7:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.dna);
                        break;
                    case 8:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.fakelove);
                        break;
                    case 9:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.fire);
                        break;
                    case 10:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.ineedu);
                        break;
                    case 11:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.micdrop);
                        break;
                    case 12:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.mytime);
                        break;
                    case 13:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.nottoday);
                        break;
                    case 14:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.run);
                        break;
                    case 15:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.springday);
                        break;
                    case 16:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.tomorrow);
                        break;
                    default:
                        mp= MediaPlayer.create(getApplicationContext(), R.raw.bloodsweattears);
                        break;
                }
                sb.setMax(mp.getDuration());
                mp.start();
            }
        });
    }

    public void seekbarinit(){
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(mp.isPlaying())
                    mp.pause();
                mp.seekTo(sb.getProgress());
                mp.start();
            }
        });

        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sb.setProgress(mp.getCurrentPosition());
            }
        }, 0, 1000);
    }

    public void getLyrics(View view){

        String songName="";
        switch(pos){
            case 0:
                songName= "blackswan";
                break;
            case 1:
                songName= "bloodsweattears";
                break;
            case 2:
                songName= "boyinluvsangnamja";
                break;
            case 3:
                songName= "boywithluv";
                break;
            case 4:
                songName= "butterfly";
                break;
            case 5:
                songName= "danger";
                break;
            case 6:
                songName="dionysus";
                break;
            case 7:
                songName="dna";
                break;
            case 8:
                songName= "fakelove";
                break;
            case 9:
                songName= "dope";
                break;
            case 10:
                songName= "ineedu";
                break;
            case 11:
                songName= "micdrop";
                break;
            case 12:
                songName="mytime";
                break;
            case 13:
                songName="nottoday";
                break;
            case 14:
                songName="run";
                break;
            case 15:
                songName="springday";
                break;
            case 16:
                songName="tomorrow";
                break;
            default:
                songName="bloodsweattears";
                break;
        }


        //Toast.makeText(getApplicationContext(), songName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LyricsActivity.class);
        intent.putExtra("songName",songName);
        startActivity(intent);
    }
}
