package com.sreejuzzz.mobilemouse.operation;

import com.google.gson.Gson;

/**
 * Created by SreejuZzz on 27022018
 */

public class CommandParser {

    public static String parseCommand(OperationData operation) {
        Gson gson = new Gson();
        return gson.toJson(operation, OperationData.class);
    }
}
