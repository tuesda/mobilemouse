package com.zhanglei.mobilemouse.utils;

/**
 * Created by SreejuZzz on 27022018
 */


import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyCodeDict {
	private static Map<Character, Integer> dict = new HashMap<Character, Integer>();
	private static Map<Character, Integer> dictShift = new HashMap<Character, Integer>();
	
	static {
		for (int i='a',j=KeyEvent.VK_A; i<='z';i++,j++) {
			dict.put((char)i, j);
		}
		for (int i='A',j=KeyEvent.VK_A; i<='Z'; i++,j++) {
			dict.put((char)i, j);
		}
		
		// for 0 - 9
		for (int i = '0', j=KeyEvent.VK_0; i <= '9'; i++, j++) {
			dict.put((char)i, j);
		}
		
		// special char
		dict.put('`', KeyEvent.VK_BACK_QUOTE);
		dict.put('-', KeyEvent.VK_MINUS);
		dict.put('=', KeyEvent.VK_EQUALS);
		dict.put('_', KeyEvent.VK_UNDERSCORE);
		dict.put('+', KeyEvent.VK_PLUS);
		dict.put('[', KeyEvent.VK_OPEN_BRACKET);
		dict.put(']', KeyEvent.VK_OPEN_BRACKET);
		dict.put('\\', KeyEvent.VK_BACK_SLASH);
		dict.put(';', KeyEvent.VK_SEMICOLON);
		dict.put('\'', KeyEvent.VK_QUOTE);
		dict.put(',', KeyEvent.VK_COMMA);
		dict.put('.', KeyEvent.VK_PERIOD);
		dict.put('/', KeyEvent.VK_SLASH);
		dict.put(' ', KeyEvent.VK_SPACE);
		
		dict.put('\n', KeyEvent.VK_ENTER);
		
		
		// set of character which need to be typed with shift key
		dictShift.put('%', KeyEvent.VK_5);
		dictShift.put('!', KeyEvent.VK_1);
		dictShift.put('@', KeyEvent.VK_2);
		dictShift.put('#', KeyEvent.VK_3);
		dictShift.put('$', KeyEvent.VK_4);
		dictShift.put('^', KeyEvent.VK_6);
		dictShift.put('&', KeyEvent.VK_7);
		dictShift.put('*', KeyEvent.VK_8);
		dictShift.put('(', KeyEvent.VK_9);
		dictShift.put(')', KeyEvent.VK_0);
		dictShift.put(':', KeyEvent.VK_SEMICOLON);
		dictShift.put('"', KeyEvent.VK_QUOTE);
		dictShift.put('<', KeyEvent.VK_COMMA);
		dictShift.put('>', KeyEvent.VK_PERIOD);
		dictShift.put('?', KeyEvent.VK_SLASH);
		dictShift.put('_', KeyEvent.VK_MINUS);
		dictShift.put('+', KeyEvent.VK_EQUALS);
		
		
		
		
		
		
	}
	
	public static boolean isNotShift(char c) {
		return dict.containsKey(c);
	}
	
	public static boolean isShiftKey(char c) {
		return dictShift.containsKey(c);
	}
	
	public static int getShiftCode(char c) {
		if (!dictShift.containsKey(c)) {
			return -1;
		}
		return dictShift.get(c);
	}
	
	public static int getCodeByChar(char c) {
		if (!dict.containsKey(c)) {
			return -1;
		}
		return dict.get(c);
	}
	
	
}
