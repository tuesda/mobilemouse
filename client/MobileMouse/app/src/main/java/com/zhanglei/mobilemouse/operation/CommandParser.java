package com.zhanglei.mobilemouse.operation;

import com.google.gson.Gson;

/**
 * Created by zhanglei on 15/7/3.
 */
public class CommandParser {

    public static String parseCommand(OperationData operation) {
        Gson gson = new Gson();
        return gson.toJson(operation, OperationData.class);
    }
}
