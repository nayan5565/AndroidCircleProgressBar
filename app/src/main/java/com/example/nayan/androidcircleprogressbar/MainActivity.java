package com.example.nayan.androidcircleprogressbar;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int count = 0, sec = 0, min = 1;
    private Handler handler;
    private String s = "" + 59, m = "" + 00;
    private Runnable runnable;

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        handler = new Handler();
        progressBar = (ProgressBar) findViewById(R.id.progress);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(m + ":" + s);
        time();
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                change();
                setProgressBar2();
            }
        });
    }

    private void time() {
        count = min * 60 + sec;

        runnable = new Runnable() {
            @Override
            public void run() {
                sec--;

                if (sec < 0) {
                    sec = 59;
                    min--;
                }
//                if (min == 60) {
//                    min = 0;
//                }
                if (sec <= 9) {
                    s = "0" + sec;
                } else {
                    s = sec + "";
                }


                if (min <= 9) {
                    m = "0" + min;
                } else {
                    m = "" + min;
                }
                if (count <= 0) {
                    count = min * 60 + sec;
//                    timeDone();
                } else {
                    progressBar.setProgress(count);
                    textView.setText(m + ":" + s);
                    change();
                }
            }
        };
        handler = new Handler();
    }

    private void setProgressBar() {

        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus + "/" + progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void setProgressBar2() {
        count = min * 60 + sec;
        progressBar.setMax(count);
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (count > 0) {
                    count -= 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            sec--;

                            if (sec < 0) {
                                sec = 59;
                                min--;
                            }
//                if (min == 60) {
//                    min = 0;
//                }
                            if (sec <= 9) {
                                s = "0" + sec;
                            } else {
                                s = sec + "";
                            }


                            if (min <= 9) {
                                m = "0" + min;
                            } else {
                                m = "" + min;
                            }
                            progressBar.setProgress(count);
                            textView.setText(m + ":" + s);
                        }
                    });
                    try {
                        // Sleep for 1000 milliseconds.
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void change() {

        count--;
        handler.postDelayed(runnable, 1000);

    }
}

