package com.zhanglei.mobilemouse.operation;

import com.google.gson.Gson;

public class OperationDecode {
	public static OperationData decode(String input) {
		Gson gson = new Gson();
		OperationData result = new OperationData();
		if (input == null || input.equals("")) {
			return null;
		}
		result = gson.fromJson(input, OperationData.class);
		return result;
	}
}
