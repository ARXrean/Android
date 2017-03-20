package com.example.myplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {
    private Button btnPlay;
    private Button btnStop;
    private MediaPlayer player;
//    private Uri songUrl;
    private String url = null;
    private ListView list;
    private ArrayAdapter<String> arrAdapter;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.play);
        btnStop = (Button) findViewById(R.id.stop);
        list = (ListView) findViewById(R.id.listview);
        String[]arr_data={"Not Afraid","The Internet Millionaires' Club", "You're Never Over",
        "不要认为自己没有用","对面的女孩看过来"};
        arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
        list.setAdapter(arrAdapter);
        list.setOnItemClickListener(this);

        player = new MediaPlayer();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnPlay.getText() == "Play"){
                    if(url == null)
                        Toast.makeText(MainActivity.this, "Please choose one", Toast.LENGTH_SHORT).show();
                    else{
                        player.start();
                        btnPlay.setText("Pause");
                    }
                } else{
                    player.pause();
                    btnPlay.setText("Play");
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stop();
            }
        });

    }

    public void Play(){
        try {
            player.setDataSource(url);
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    btnPlay.setText("Pause");
                }
            });
        } catch (IllegalArgumentException e) {
            Toast.makeText(MainActivity.this, "Failed to play", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IllegalStateException e) {
            Toast.makeText(MainActivity.this, "Failed to play", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "Failed to play", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void Stop(){
        player.stop();
        btnPlay.setText("Play");
        url = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        player.reset();
        if(position == 0){
            url = "http://mpianatra.com/Courses/files/Eminem - Not Afraid_audio.mp3";
        }else if(position == 1){
            url = "http://mpianatra.com/Courses/files/The Internet Millionaires' Club.mp3";
        }else if(position == 2){
            url = "http://mpianatra.com/Courses/files/You're Never Over.mp3";
        }else if(position == 3){
            url = "http://mpianatra.com/Courses/files/不要认为自己没有用.mp3";
        }else if(position == 4){
            url = "http://mpianatra.com/Courses/files/对面的女孩看过来.mp3";
        }
        Play();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
