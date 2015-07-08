package com.zhanglei.mobilemouse.utils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;

public class SlowMove {
	
	public static int SCREEN_W;
	public static int SCREEN_H;
	
	static {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SCREEN_W = (int) screenSize.getWidth();
		SCREEN_H = (int) screenSize.getHeight();

	}
	
	public static void moveRelatively(final Robot robot, final long duration,  final int relativeX, final int relativeY) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				long startTime = System.currentTimeMillis();
				long endTime = startTime + duration;
				int cur_x = MouseInfo.getPointerInfo().getLocation().x;
				int cur_y = MouseInfo.getPointerInfo().getLocation().y;
				while (System.currentTimeMillis() < endTime) {
					
					long curTime = System.currentTimeMillis();
					float ratio = (float)(curTime - startTime) / duration;
					int move_x = (int)(cur_x + (ratio * relativeX));
					int move_y = (int) (cur_y + (ratio * relativeY));
					
					move_x = move_x < 0 ? 0 : move_x;
					move_y = move_y < 0 ? 0 : move_y;
					// if set move_x with SCREEN instead of SCREEN - 1, MouseInfo.getPointerInfo() will be null exception
					move_x = move_x >= SCREEN_W ? SCREEN_W-1 : move_x;
					move_y = move_y >= SCREEN_H ? SCREEN_H-1 : move_y;
					// System.out.println("x: " + move_x + "y: " + move_y + "max value" + SCREEN_W + " " + SCREEN_H);
					robot.mouseMove(move_x, move_y);
				}
			}
		}).start();;
	}
	
	public static void main(String[] args) {
		try {
			Robot robot = new Robot();
			moveRelatively(robot, 400, 300, 300);
			moveRelatively(robot, 400, -300, 300);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


