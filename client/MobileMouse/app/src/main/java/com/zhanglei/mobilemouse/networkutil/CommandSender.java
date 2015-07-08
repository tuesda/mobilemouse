package com.zhanglei.mobilemouse.networkutil;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.zhanglei.mobilemouse.operation.CommandParser;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * Created by zhanglei on 15/7/3.
 */
public class CommandSender extends Thread {
    public static final int SEND_COMAND = 0;


    public static final String SERVER_IP = "192.168.1.101";
    public static final int SERVER_PORT = 9898;

    private String mServerIP;
    private int mServerPort;
    private Socket socket;

    public Handler mHandler;


    public CommandSender(String serverIp, int port) {
        mServerIP = serverIp == null ? SERVER_IP : serverIp;
        mServerPort = port == -1 ? SERVER_PORT : port;

    }


    private boolean send(String str) {
        try {

            Log.i("fuck", "ip: " + mServerIP + "port: " + SERVER_PORT);

            socket = new Socket(mServerIP, SERVER_PORT);
            Log.i("fuck", "socket: " + socket);
            Writer writer = new OutputStreamWriter(socket.getOutputStream());

            writer.append(str);
            writer.flush();
            writer.close();
            socket.close();

            return true;


        } catch (IOException e) {
            return false;
        }
    }


    @Override
    public void run() {

        Looper.prepare();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case SEND_COMAND:
                        String command = (String) msg.obj;
                        send(command);
                        break;
                    default:
                        Log.e(CommandSender.class.getName(), "Unknown command msg.what = " + msg.what);
                }
            }
        };
        Looper.loop();
    }




}
