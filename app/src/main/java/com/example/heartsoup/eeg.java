package com.example.heartsoup;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.neurosky.AlgoSdk.NskAlgoConfig;
import com.neurosky.AlgoSdk.NskAlgoDataType;
import com.neurosky.AlgoSdk.NskAlgoSdk;
import com.neurosky.AlgoSdk.NskAlgoSignalQuality;
import com.neurosky.AlgoSdk.NskAlgoState;
import com.neurosky.AlgoSdk.NskAlgoType;
import com.neurosky.connection.ConnectionStates;
import com.neurosky.connection.TgStreamHandler;
import com.neurosky.connection.TgStreamReader;
import com.neurosky.connection.DataType.MindDataType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

public class eeg extends AppCompatActivity {

    // COMM SDK handles
    private TgStreamReader tgStreamReader;
    private BluetoothAdapter mBluetoothAdapter;
    private String TAG = "EEG";

    // internal variables
    private boolean bInited = false;
    private boolean bRunning = false;
    private NskAlgoType currentSelectedAlgo;

    // canned data variables
    private short raw_data[] = {0};
    private int raw_data_index = 0;
    private float output_data[];
    private int output_data_count = 0;
    private int raw_data_sec_len = 85;

    private String fpath = "storage/emulated/0/neuron/" ;
    //intialize filename by timeDate
    private String fname = initFilename("statics.txt");

    private NskAlgoSdk nskAlgoSdk;

    private int bLastOutputInterval = 1;

    int[] resID = new int[]{R.drawable.t1, R.drawable.t2, R.drawable.t3, R.drawable.t4, R.drawable.t6, R.drawable.t7};
    int blinkIndex = 0;
    int blinkFlag = 0;


    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_eeg);

        //intialize filepath by timeDate
        makeFilePath(fpath, fname);
        //图片切换
        final Button change_pic = (Button) findViewById(R.id.dummy_button);
        final ImageView pressure = (ImageView) findViewById(R.id.pressure);
        final TextView hint = (TextView) findViewById(R.id.eeg_hint);

        nskAlgoSdk = new NskAlgoSdk();
        try {
            // (1) Make sure that the device supports Bluetooth and Bluetooth is on
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                Toast.makeText(
                        this,
                        "Please enable your Bluetooth and re-run this program !",
                        Toast.LENGTH_LONG).show();
                //finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("eeg", "error:" + e.getMessage());
            return;
        }
        output_data_count = 0;
        output_data = null;

        raw_data = new short[512];
        raw_data_index = 0;


        // Example of constructor public TgStreamReader(BluetoothAdapter ba, TgStreamHandler tgStreamHandler)
        tgStreamReader = new TgStreamReader(mBluetoothAdapter, callback);

        if (tgStreamReader != null && tgStreamReader.isBTConnected()) {

            // Prepare for connecting
            tgStreamReader.stop();
            tgStreamReader.close();
        }

        // (4) Demo of  using connect() and start() to replace connectAndStart(),
        // please call start() when the state is changed to STATE_CONNECTED
        tgStreamReader.connect();
        pressure.setImageResource(R.drawable.t1);
        change_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hide button
                blinkIndex = 0;
                blinkFlag = 0;
                change_pic.setVisibility(View.INVISIBLE);
                hint.setText("");

                int algoTypes = 0;

                algoTypes += NskAlgoType.NSK_ALGO_TYPE_BLINK.value;
                algoTypes += NskAlgoType.NSK_ALGO_TYPE_BP.value;
                try {
                    if (bInited) {
                        nskAlgoSdk.NskAlgoUninit();
                        bInited = false;
                    }
                    int ret = nskAlgoSdk.NskAlgoInit(algoTypes, getFilesDir().getAbsolutePath());
                    if (ret == 0) {
                        bInited = true;
                    }

                    Log.d(TAG, "NSK_ALGO_Init() " + ret);
                    String sdkVersion = "SDK ver.: " + nskAlgoSdk.NskAlgoSdkVersion();

                    if ((algoTypes & NskAlgoType.NSK_ALGO_TYPE_BLINK.value) != 0) {
                        sdkVersion += "\nBlink ver.: " + nskAlgoSdk.NskAlgoAlgoVersion(NskAlgoType.NSK_ALGO_TYPE_BLINK.value);
                    }
                    if ((algoTypes & NskAlgoType.NSK_ALGO_TYPE_BP.value) != 0) {
                        sdkVersion += "\nEEG Bandpower ver.: " + nskAlgoSdk.NskAlgoAlgoVersion(NskAlgoType.NSK_ALGO_TYPE_BP.value);
                    }
//                    showToast(sdkVersion, Toast.LENGTH_LONG);
                } catch (Exception e) {
                    Log.v(TAG, "start failed");
                }
                if (!bRunning) {
                    nskAlgoSdk.NskAlgoStart(false);
                } else {
                    nskAlgoSdk.NskAlgoPause();
                }

                //welcome.java有用法
//                final Handler handler2 = new Handler();
//                Runnable runnable2 = new Runnable() {
//                    @Override
//                    public void run() {
//                        handler2.postDelayed(this, 1000);
//                    }
//                };
//                Field[] fields = R.drawable.class.getDeclaredFields();

//                Log.v("field", fields.toString());
//                for(int i=2;i<8;i++)
//                {
//                    String name = "t" + i;
//                    for(Field field:fields){
//                        if(field.getName() == name)
//                        {
//                            int resID = getResources().getIdentifier(field.getName(),
//                                    "drawable", getClass().getPackage().getName());
//                            pressure.setImageResource(resID);
//                            delay(1000);
//                        }
//                    }
//                }
                delay(500);
                pressure.setImageResource(R.drawable.t1);
                showToast("仔细看，它在动吗？", 2000);
            }
        });
//        nskAlgoSdk.setOnStateChangeListener(new NskAlgoSdk.OnStateChangeListener() {
//            @Override
//            public void onStateChange(int state, int reason) {
//                String stateStr = "";
//                String reasonStr = "";
//                for (NskAlgoState s : NskAlgoState.values()) {
//                    if (s.value == state) {
//                        stateStr = s.toString();
//                    }
//                }
//                for (NskAlgoState r : NskAlgoState.values()) {
//                    if (r.value == reason) {
//                        reasonStr = r.toString();
//                    }
//                }
//                Log.d(TAG, "NskAlgoSdkStateChangeListener: state: " + stateStr + ", reason: " + reasonStr);
//                final String finalStateStr = stateStr + " | " + reasonStr;
//                final int finalState = state;
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // change UI elements here
////                        stateText.setText(finalStateStr);
//
//                        if (finalState == NskAlgoState.NSK_ALGO_STATE_RUNNING.value || finalState == NskAlgoState.NSK_ALGO_STATE_COLLECTING_BASELINE_DATA.value) {
//                            bRunning = true;
////                            startButton.setText("Pause");
////                            startButton.setEnabled(true);
////                            stopButton.setEnabled(true);
//                        } else if (finalState == NskAlgoState.NSK_ALGO_STATE_STOP.value) {
//                            bRunning = false;
//                            raw_data = null;
//                            raw_data_index = 0;
////                            startButton.setText("Start");
////                            startButton.setEnabled(true);
////                            stopButton.setEnabled(false);
////
////                            headsetButton.setEnabled(true);
////                            cannedButton.setEnabled(true);
//
//                            if (tgStreamReader != null && tgStreamReader.isBTConnected()) {
//
//                                // Prepare for connecting
//                                tgStreamReader.stop();
//                                tgStreamReader.close();
//                            }
//
//                            output_data_count = 0;
//                            output_data = null;
//
//                            System.gc();
//                        } else if (finalState == NskAlgoState.NSK_ALGO_STATE_PAUSE.value) {
//                            bRunning = false;
////                            startButton.setText("Start");
////                            startButton.setEnabled(true);
////                            stopButton.setEnabled(true);
//                        } else if (finalState == NskAlgoState.NSK_ALGO_STATE_ANALYSING_BULK_DATA.value) {
//                            bRunning = true;
////                            startButton.setText("Start");
////                            startButton.setEnabled(false);
////                            stopButton.setEnabled(true);
//                        } else if (finalState == NskAlgoState.NSK_ALGO_STATE_INITED.value || finalState == NskAlgoState.NSK_ALGO_STATE_UNINTIED.value) {
//                            bRunning = false;
////                            startButton.setText("Start");
////                            startButton.setEnabled(true);
////                            stopButton.setEnabled(false);
//                        }
//                    }
//                });
//            }
//        });


        nskAlgoSdk.setOnBPAlgoIndexListener(new NskAlgoSdk.OnBPAlgoIndexListener() {
            @Override
            public void onBPAlgoIndex(float delta, float theta, float alpha, float beta, float gamma) {
                final String sqStr = delta + " " + theta + " " + alpha + " " + beta + " " + gamma + "\n";
                Log.d(TAG, "NskAlgoBPAlgoIndexListener: BP: D[" + delta + " dB] T[" + theta + " dB] A[" + alpha + " dB] B[" + beta + " dB] G[" + gamma + "]");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // change UI elements here
//                        AddValueToPlot(bp_deltaSeries, fDelta);
//                        AddValueToPlot(bp_thetaSeries, fTheta);
//                        AddValueToPlot(bp_alphaSeries, fAlpha);
//                        AddValueToPlot(bp_betaSeries, fBeta);
//                        AddValueToPlot(bp_gammaSeries, fGamma);
                        //hint.setText(sqStr);
                        write(sqStr);
                        Log.v(TAG, "waiting for processing");
                    }
                });
            }
        });

        nskAlgoSdk.setOnEyeBlinkDetectionListener(new NskAlgoSdk.OnEyeBlinkDetectionListener() {
            @Override
            public void onEyeBlinkDetect(int strength) {
                Log.d(TAG, "NskAlgoEyeBlinkDetectionListener: Eye blink detected: " + strength);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v(TAG, "blink detected");
                        Timer timer = new Timer();

                        timer.schedule(new TimerTask() {
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.v(TAG, "blink detected");
                                        blinkFlag++;
                                        if(blinkFlag == resID.length)
                                        {
                                            blinkIndex++;
                                            blinkFlag = 0;
                                            if(blinkIndex < resID.length)
                                            {
                                                pressure.setImageResource(resID[blinkIndex]);
                                            }
                                        }
                                        if(blinkIndex == resID.length)
                                        {
                                            change_pic.setText("once more for precise result");
                                            change_pic.setVisibility(View.VISIBLE);
                                            nskAlgoSdk.NskAlgoPause();
                                            showToast("正在生成结果...", 3000);
                                            hint.setText(analyzeResult());
                                            write("\n\n\n");
                                        }

                                    }
                                });
                            }
                        }, 500);
                    }
                });
            }
        });


    }

    private short [] readData(InputStream is, int size) throws IOException {
        short data[] = new short[size];
        int lineCount = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while (lineCount < size) {
                String line = reader.readLine();
                if (line == null || line.isEmpty()) {
                    Log.d(TAG, "lineCount=" + lineCount);
                    break;
                }
                data[lineCount] = Short.parseShort(line);
                lineCount++;
            }
            Log.d(TAG, "lineCount=" + lineCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    @Override
    public void onBackPressed() {
        nskAlgoSdk.NskAlgoUninit();
//        Intent intent=new Intent();
//        intent.setComponent(new ComponentName("com.android.heartsoup", "com.android.menu"));
//        startActivity(intent);
        showToast("成功退出", 1000);
        finish();
    }


    private void delay(int ms) {
        try {
            Thread.currentThread();
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private TgStreamHandler callback = new TgStreamHandler() {

        @Override
        public void onStatesChanged(int connectionStates) {
            // TODO Auto-generated method stub
            Log.d(TAG, "connectionStates change to: " + connectionStates);
            switch (connectionStates) {
                case ConnectionStates.STATE_CONNECTING:
                    // Do something when connecting
                    break;
                case ConnectionStates.STATE_CONNECTED:
                    // Do something when connected
                    tgStreamReader.start();
                    showToast("Connected", Toast.LENGTH_SHORT);
                    break;
                case ConnectionStates.STATE_WORKING:
                    // Do something when working

                    //(9) demo of recording raw data , stop() will call stopRecordRawData,
                    //or you can add a button to control it.
                    //You can change the save path by calling setRecordStreamFilePath(String filePath) before startRecordRawData
                    //tgStreamReader.startRecordRawData();

//                    eeg.this.runOnUiThread(new Runnable() {
//                        public void run() {
//                            Button startButton = (Button) findViewById(R.id.startButton);
//                            startButton.setEnabled(true);
//                        }
//
//                    });

                    break;
                case ConnectionStates.STATE_GET_DATA_TIME_OUT:
                    // Do something when getting data timeout

                    //(9) demo of recording raw data, exception handling
                    //tgStreamReader.stopRecordRawData();

                    showToast("Get data time out!", Toast.LENGTH_SHORT);

                    if (tgStreamReader != null && tgStreamReader.isBTConnected()) {
                        tgStreamReader.stop();
                        tgStreamReader.close();
                    }

                    break;
                case ConnectionStates.STATE_STOPPED:
                    // Do something when stopped
                    // We have to call tgStreamReader.stop() and tgStreamReader.close() much more than
                    // tgStreamReader.connectAndstart(), because we have to prepare for that.

                    break;
                case ConnectionStates.STATE_DISCONNECTED:
                    // Do something when disconnected
                    break;
                case ConnectionStates.STATE_ERROR:
                    // Do something when you get error message
                    break;
                case ConnectionStates.STATE_FAILED:
                    // Do something when you get failed message
                    // It always happens when open the BluetoothSocket error or timeout
                    // Maybe the device is not working normal.
                    // Maybe you have to try again
                    break;
            }
        }

        @Override
        public void onRecordFail(int flag) {
            // You can handle the record error message here
            Log.e(TAG, "onRecordFail: " + flag);

        }

        @Override
        public void onChecksumFail(byte[] payload, int length, int checksum) {
            // You can handle the bad packets here.
        }

        @Override
        public void onDataReceived(int datatype, int data, Object obj) {
            // You can handle the received data here
            // You can feed the raw data to algo sdk here if necessary.
            //Log.i(TAG,"onDataReceived");
            switch (datatype) {
                case MindDataType.CODE_ATTENTION:
                    short attValue[] = {(short) data};
                    nskAlgoSdk.NskAlgoDataStream(NskAlgoDataType.NSK_ALGO_DATA_TYPE_ATT.value, attValue, 1);
                    break;
                case MindDataType.CODE_MEDITATION:
                    short medValue[] = {(short) data};
                    nskAlgoSdk.NskAlgoDataStream(NskAlgoDataType.NSK_ALGO_DATA_TYPE_MED.value, medValue, 1);
                    break;
                case MindDataType.CODE_POOR_SIGNAL:
                    short pqValue[] = {(short) data};
                    nskAlgoSdk.NskAlgoDataStream(NskAlgoDataType.NSK_ALGO_DATA_TYPE_PQ.value, pqValue, 1);
                    break;
                case MindDataType.CODE_RAW:
                    raw_data[raw_data_index++] = (short) data;
                    if (raw_data_index == 512) {
                        nskAlgoSdk.NskAlgoDataStream(NskAlgoDataType.NSK_ALGO_DATA_TYPE_EEG.value, raw_data, raw_data_index);
                        raw_data_index = 0;
                    }
                    break;
                default:
                    break;
            }
        }

    };

    public void showToast(final String msg, final int timeStyle) {
        eeg.this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), msg, timeStyle).show();
            }

        });
    }

    public String read() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            try {
                FileInputStream inStream = this.openFileInput(fpath + fname);
                byte[] buffer = new byte[1024];
                int hasRead;
                StringBuilder sb = new StringBuilder();
                while ((hasRead = inStream.read(buffer)) != -1) {
                    sb.append(new String(buffer, 0, hasRead));
                }

                inStream.close();
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void write(String msg){
        // 步骤1：获取输入值
        if(msg == null) return;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            try {
                // 步骤2:创建一个FileOutputStream对象,MODE_APPEND追加模式
                File file = new File(fpath + fname);
                FileOutputStream fos = new FileOutputStream(file, true);
                // 步骤3：将获取过来的值放入文件
                fos.write(msg.getBytes());
                // 步骤4：关闭数据流
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private String initFilename(String fname){
        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance();
        return df.format(date) + fname;
    }

    //analyze the result
    public String analyzeResult()
    {
        String result = "你的评分为：75 分\n 需要放松一下哦";
        return result;
    }

    // 生成文件
    public File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }
}
