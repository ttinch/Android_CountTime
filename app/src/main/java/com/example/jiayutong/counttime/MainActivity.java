package com.example.jiayutong.counttime;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputEt;
    private Button getTime,startTime,stopTime;
    private TextView time;
    private int i = 0;
    private Timer timer = null;
    private  TimerTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        inputEt = (EditText) findViewById(R.id.inputTime);
        getTime = (Button) findViewById(R.id.getTime);
        startTime = (Button) findViewById(R.id.startTime);
        stopTime = (Button) findViewById(R.id.stopTime);
        time = (TextView) findViewById(R.id.time);

        getTime.setOnClickListener(this);
        startTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getTime:
                time.setText(inputEt.getText().toString());
                i = Integer.parseInt(inputEt.getText().toString());
                break;
            case R.id.startTime:
                startTime();
                break;
            case R.id.stopTime:
                stopTime();
                break;
        }
    }

    private android.os.Handler mHandler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            time.setText(msg.arg1+"");
            startTime();
        }
    };

    public void startTime() {
        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                i--;
                Message message = mHandler.obtainMessage();
                message.arg1 = i;
                mHandler.sendMessage(message);
            }
        };
        timer.schedule(task, 1000);  //时间以ms为单位

    }

    public void stopTime() {
        timer.cancel();
    }
}
