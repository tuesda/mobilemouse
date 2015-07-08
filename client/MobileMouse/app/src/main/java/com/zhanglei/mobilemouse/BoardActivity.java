package com.zhanglei.mobilemouse;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.renderscript.BaseObj;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanglei.mobilemouse.networkutil.CommandSender;
import com.zhanglei.mobilemouse.operation.CommandParser;
import com.zhanglei.mobilemouse.operation.OperationData;

/**
 * Created by zhanglei on 15/7/5.
 */
public class BoardActivity extends Activity {


    private CommandSender mSender;

    private View touchBoard;

    private View mLeftClick;
    private View mRightClick;

    private EditText boardInput;
    private String mLastInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        init();
    }

    private void init() {
        String ipStr = getIntent().getStringExtra(MainActivity.SERVER_IP_FLAG);
        int port = getIntent().getIntExtra(MainActivity.SERVER_PORT_FLAG, -1);

        setContentView(R.layout.activity_board);
        touchBoard = (View) findViewById(R.id.touch_board);
        mLeftClick = (View) findViewById(R.id.btn_left_click);
        mRightClick = (View) findViewById(R.id.btn_right_click);

        boardInput = (EditText) findViewById(R.id.edit_board_input);


        mLeftClick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        sendCommand(OperationData.OPERATION_CLICK_DOWN, 0, 0, null);
                        break;
                    case MotionEvent.ACTION_UP:
                        sendCommand(OperationData.OPERATION_CLICK_UP, 0, 0, null);
                        break;
                }
                return true;
            }
        });

        mRightClick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        sendCommand(OperationData.OPERATION_RIGHT_CLICK, 0, 0, null);
                        break;
                }
                return true;
            }
        });

        boardInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
               //if (KeyEvent.KEYCODE_BACK == keyCode) {
                    //Toast.makeText(BoardActivity.this, "click back" + keyCode, Toast.LENGTH_LONG).show();
                //}

                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    sendCommand(OperationData.OPERATION_DEL_TEXT, 0, 0, null);
                }
                return false;
            }
        });



        boardInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("fuck", "before: " + s);
                mLastInput = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0) return;
                Log.i("fuck", "on: " + s + ", last char: " + s.toString().substring(s.length() - 1));
                String lastChar = s.subSequence(s.length() - 1, s.length()).toString();
                if (!TextUtils.isEmpty(lastChar) && s.length() > mLastInput.length()) {
                    sendCommand(OperationData.OPERATION_TYPE_TEXT, 0, 0, lastChar);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("fuck", "after: " + s.toString());
            }
        });







        mSender = new CommandSender(ipStr, port);
        mSender.start();




        touchBoard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                onBoardTouch(event);
                return true;
            }
        });
    }

    private long mDownTime;
    private float mDownX;
    private float mDownY;

    private long mUpTime;
    private float mUpX;
    private float mUpY;

    private float mLastMoveX = Float.MAX_VALUE;
    private float mLastMoveY = Float.MAX_VALUE;

    private float mCurMoveX;
    private float mCurMoveY;
    private long mLastMoveTime;




    private void onBoardTouch(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                onSingClick(event, true);
                break;
            case MotionEvent.ACTION_UP:
                onSingClick(event, false);
                break;
            case MotionEvent.ACTION_MOVE:
                onMove(event);
                break;

        }
    }

    private void onMove(MotionEvent event) {
        int distanceX = 0;
        int distanceY = 0;

        mCurMoveX = event.getX();
        mCurMoveY = event.getY();


        if (mLastMoveX!=Float.MAX_VALUE && mLastMoveY != Float.MAX_VALUE) {
            distanceX = (int) (mCurMoveX - mDownX);
            distanceY = (int) (mCurMoveY - mDownY);
        }

        int distance  = (int) Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        // send a move command per 0.5 s
        if (distance > 100 || System.currentTimeMillis() - mLastMoveTime > 100) {
            sendCommand(OperationData.OPERATION_MOVE, distanceX, distanceY, null);
            mLastMoveX = mCurMoveX;
            mLastMoveY = mCurMoveY;
            mLastMoveTime = System.currentTimeMillis();
        }
//        sendCommand(OperationData.OPERATION_MOVE, distanceX, distanceY);


    }

    private void onSingClick(MotionEvent event, boolean down) {
        if (down) {


            mDownTime = System.currentTimeMillis();
            mDownX = event.getX();
            mDownY = event.getY();
        } else {
            mUpTime = System.currentTimeMillis();
            mUpX = event.getX();
            mUpY = event.getY();
        }


    }


    /**
     *
     * @param operationKind
     * @param x    0 means this value is null
     * @param y    0 means this value is null
     * @param input null means this value is null
     */
    private void sendCommand(int operationKind, int x, int y, String input) {
        Message msg = Message.obtain(mSender.mHandler);
        OperationData operation = new OperationData();
        operation.setOperationKind(operationKind);
        if (x!=0) operation.setMoveX(x);
        if (y!=0) operation.setMoveY(y);
        if (input!=null) operation.setInputStr(input);
        Log.i("fuck", "" + operation);
        msg.obj = CommandParser.parseCommand(operation);
        msg.sendToTarget();
    }





    @Override
    protected void onPause() {
        super.onPause();
        mSender.interrupt();
    }

}
