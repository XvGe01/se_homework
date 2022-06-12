package net.lemontree.push;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.os.Handler;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private static String host = "";
    private Button sendBt;
    private Handler handler = new Handler();
    private TextView infoTv;
    private static int PORT = 8888; //电脑端端口
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTv = findViewById(R.id.info);
        sendBt = findViewById(R.id.send);

        sp = getSharedPreferences("settings", MODE_PRIVATE);

        host = sp.getString("host", "");
//监听按钮点击事件，获取系统剪切板数据发送至服务器端
        sendBt.setOnClickListener(view -> new Thread(() -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = clipboard.getPrimaryClip();
            String clipStr = "";
            if (clipData != null && clipData.getItemCount() > 0) {
                clipStr = clipData.getItemAt(0).getText().toString();
            }
            if (sendSocket(host, clipStr)) {
                handler.post(() -> Toast.makeText(MainActivity.this, "发送成功", Toast.LENGTH_SHORT).show());
            } else {
                handler.post(() -> Toast.makeText(MainActivity.this, "发送失败", Toast.LENGTH_SHORT).show());
            }
        }).start());


    }

    @Override
    protected void onResume() {

        if (!NetworkUtil.isWifiConnected(this)) {
            Toast.makeText(this, "请打开WIFI连接局域网", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (!host.equals("")) {
                infoTv.setText("电脑端IP地址为：" + host + ":" + PORT);
            }
        }
        Intent intent = getIntent();
        String action = intent.getAction();
        if (!host.equals("") && action != null) {
            String share = "";
            if (Intent.ACTION_SEND.equals(action)) {
                share = intent.getStringExtra(Intent.EXTRA_TEXT);
            }
            if (Intent.ACTION_VIEW.equals(action)) {
                share = intent.getDataString();
            }
            String finalShare = share;
            if (!finalShare.equals("")) {
                new Thread(() -> {
                    if (sendSocket(host, finalShare)) {
                        handler.post(() -> {
                                    Toast.makeText(MainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                        );


                    } else {
                        handler.post(() -> Toast.makeText(MainActivity.this, "发送失败", Toast.LENGTH_SHORT).show());
                        finish();
                    }
                }).start();
            }
        } else {
            Toast.makeText(this, "请手动设置电脑IP地址", Toast.LENGTH_LONG).show();
        }
        super.onResume();
    }
    //通过socket连接，向服务器端发送数据
    public static boolean sendSocket(String host, String content) {
        int port = PORT;
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket(host, port);
            // 建立连接后获得输出流
            outputStream = socket.getOutputStream();
            String message = content;
            socket.getOutputStream().write(message.getBytes("UTF-8"));
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    //设置服务器端IP地址
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.set) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("电脑IP地址");
            EditText editText = new EditText(this);
            editText.setHint("请输入电脑端IP地址");
            builder.setView(editText);
            builder.setPositiveButton("确定", (dialogInterface, i) -> {
                SharedPreferences.Editor editor = sp.edit();
                //检验是否为IP地址
                host = editText.getText().toString();
                if (!host.equals("")){
                    editor.putString("host", host);
                    editor.apply();
                    infoTv.setText("电脑端：" + host + ":" + PORT);
                }else {
                    showToast("IP地址为空，请重新输入");
                }

            });
            builder.setNegativeButton("取消", (dialogInterface, i) -> {
                dialogInterface.dismiss();
            });
            builder.create().show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
