package com.zhanglei.mobilemouse.networkutil;

/**
 * Created by SreejuZzz on 27022018
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class CommandReceiver extends Thread {
	public static final int PORT = 9898;

	private ServerSocket svrSocket;
	private DeliveryCommand mDeliver;
	private boolean mQuit = false;

	public interface DeliveryCommand {
		void deliverResult(String command);

		void deliverError(String error);
	}

	public CommandReceiver(DeliveryCommand deliver) {
		mDeliver = deliver;
		try {
			svrSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error in new ServerSocket");
			svrSocket = null;
		}
	}

	public void quit() {
		mQuit = true;
		interrupt();
	}

	@Override
	public void run() {

		
		try {
			
			while (true) {
				if (mQuit) {
					return;
				}
				//System.out.println("waiting a socket to accept...");
				Socket socket = svrSocket.accept();
				//System.out.println("got a socket... socket:" + socket);
				//System.out.println("entering while loop...");
				
				byte[] buf = new byte[1024 * 10];
				InputStream in = socket.getInputStream();
				StringBuilder stringBuilder = new StringBuilder();
				int len;
//				while((len = in.read(buf, 0, buf.length)) == -1);
//				stringBuilder.append(new String(buf, 0, len));
				while ((len = in.read(buf, 0, buf.length)) != -1) {
					stringBuilder.append(new String(buf, 0, len));
				}
				String result = stringBuilder.toString();
				if (result != null && !result.isEmpty()) {
					mDeliver.deliverResult(result);
				} else {
					mDeliver.deliverError("Unknow Error");
				}
				socket.close();

			}

		} catch (IOException e) {
			mDeliver.deliverError("IOError: " + e.toString());
		}

	}
}
