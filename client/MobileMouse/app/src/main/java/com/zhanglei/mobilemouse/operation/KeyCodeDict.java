package com.zhanglei.mobilemouse.operation;

import android.view.KeyEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanglei on 15/7/7.
 */
public class KeyCodeDict {
    private static final Map<Integer, Integer> dict = new HashMap<Integer, Integer>();

    static {
        dict.put(KeyEvent.KEYCODE_0, 10);
        dict.put(KeyEvent.KEYCODE_1, 11);
        dict.put(KeyEvent.KEYCODE_2, 12);
        dict.put(KeyEvent.KEYCODE_3, 13);
        dict.put(KeyEvent.KEYCODE_4, 14);
        dict.put(KeyEvent.KEYCODE_5, 15);
        dict.put(KeyEvent.KEYCODE_6, 16);
        dict.put(KeyEvent.KEYCODE_7, 17);
        dict.put(KeyEvent.KEYCODE_8, 18);
        dict.put(KeyEvent.KEYCODE_9, 19);

        dict.put(KeyEvent.KEYCODE_A, 20);
        dict.put(KeyEvent.KEYCODE_B, 21);
        dict.put(KeyEvent.KEYCODE_C, 22);
        dict.put(KeyEvent.KEYCODE_D, 23);
        dict.put(KeyEvent.KEYCODE_E, 24);
        dict.put(KeyEvent.KEYCODE_F, 25);
        dict.put(KeyEvent.KEYCODE_G, 26);
        dict.put(KeyEvent.KEYCODE_H, 27);
        dict.put(KeyEvent.KEYCODE_I, 28);
        dict.put(KeyEvent.KEYCODE_J, 29);
        dict.put(KeyEvent.KEYCODE_K, 30);
        dict.put(KeyEvent.KEYCODE_L, 31);
        dict.put(KeyEvent.KEYCODE_M, 32);
        dict.put(KeyEvent.KEYCODE_N, 33);
        dict.put(KeyEvent.KEYCODE_O, 34);
        dict.put(KeyEvent.KEYCODE_P, 35);
        dict.put(KeyEvent.KEYCODE_Q, 36);
        dict.put(KeyEvent.KEYCODE_R, 37);
        dict.put(KeyEvent.KEYCODE_S, 38);
        dict.put(KeyEvent.KEYCODE_T, 39);
        dict.put(KeyEvent.KEYCODE_U, 40);
        dict.put(KeyEvent.KEYCODE_V, 41);
        dict.put(KeyEvent.KEYCODE_W, 42);
        dict.put(KeyEvent.KEYCODE_X, 43);
        dict.put(KeyEvent.KEYCODE_Y, 44);
        dict.put(KeyEvent.KEYCODE_Z, 45);

        dict.put(KeyEvent.KEYCODE_BACK, 50);
        dict.put(KeyEvent.KEYCODE_SHIFT_LEFT, 51);
        dict.put(KeyEvent.KEYCODE_SPACE, 52);
        dict.put(KeyEvent.KEYCODE_SLASH, 53);
        dict.put(KeyEvent.KEYCODE_CAPS_LOCK, 54);
        dict.put(KeyEvent.KEYCODE_NUMPAD_DOT, 55);




    }

    public static int getValue(int code) {
        return dict.get(code);
    }
}
