package com.zhanglei.mobilemouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {

    public static final String SERVER_IP_FLAG = "com.zhanglei.server.ip";
    public static final String SERVER_PORT_FLAG = "com.zhanglei.server.port";

    private static final String PATTERN =
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private EditText editIp, editPort;
    private Button btnDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editIp = (EditText) findViewById(R.id.edit_ip);
        editPort = (EditText) findViewById(R.id.edit_port);
        btnDone = (Button) findViewById(R.id.ip_port_sure);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ipStr = null;
                int port = -1;
                boolean isAddressInvalid = false;
                boolean isPortInvalid = false;

                if (checkEditText(editIp)) {
                    ipStr = editIp.getText().toString();
                    if (!isValidAddress(ipStr)) {
                        ipStr = null;
                        isAddressInvalid = true;
                    }

                }

                if (checkEditText(editPort)) {
                    port = Integer.parseInt(editPort.getText().toString());
                    if (!isPortValid(port)) {
                        port = -1;
                        isPortInvalid = true;
                    }
                }

                String errorStr = "";
                if (isAddressInvalid && isPortInvalid) {
                    errorStr = "Invalid address and port!";
                } else if (isAddressInvalid || isPortInvalid) {
                    errorStr = "Invalid address or port";
                }
                if (!errorStr.equals("")) {
                    Toast.makeText(MainActivity.this, errorStr, Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent (MainActivity.this, BoardActivity.class);

                if (port != -1) {
                    intent.putExtra(SERVER_PORT_FLAG, port);
                }

                if (ipStr!=null) {
                    intent.putExtra(SERVER_IP_FLAG, ipStr);
                }

                startActivity(intent);
            }

        });

    }


    private boolean checkEditText(EditText edit) {
        boolean result = true;
        String editStr = edit.getText().toString();
        if (editStr == null || editStr.equals("")) {
            return false;
        }
        return result;
    }

    private boolean isPortValid(int server_port) {
        boolean result = true;
        if (server_port < 0 || server_port > 65535) {
            return false;
        }
        return result;
    }

    private boolean isValidAddress(String ip) {
        boolean result = true;


        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(ip);
        if (!matcher.matches()) {
            return false;
        }

        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
