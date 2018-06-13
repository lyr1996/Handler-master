package com.example.dell_.handler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    private TextView statusTextView=null;
    private Handler uihandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusTextView=(TextView)findViewById(R.id.statusTextView);
        Button btnDownload=(Button)findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(this);
        System.out.println("Main Thread id"+Thread.currentThread().getId());
    }

    @Override
    public void onClick(View view) {
        MainActivity.this.statusTextView.setText("正在下载...");
        DownloadThread downloadThread=new DownloadThread();
        downloadThread.start();
    }
    class DownloadThread extends Thread{
        @Override
        public void run(){
            try{
            System.out.println("Download id"+Thread.currentThread().getId());
            System.out.println("开始下载文件");
            //模拟下载过程
            Thread.sleep(5000);
            System.out.println("下载完成");
            //下载完成更新UI
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    System.out.println("Runnable Thread id"+Thread.currentThread().getId());
                    MainActivity.this.statusTextView.setText("下载完成");
                }
            };
            uihandler.post(runnable);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
