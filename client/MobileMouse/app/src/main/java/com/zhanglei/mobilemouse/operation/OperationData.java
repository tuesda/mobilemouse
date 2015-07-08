package com.zhanglei.mobilemouse.operation;

/**
 * Created by zhanglei on 15/7/2.
 */
public class OperationData {
    public static final int OPERATION_MOVE = 0;
    public static final int OPERATION_CLICK_DOWN = 1;
    public static final int OPERATION_CLICK_UP = 2;
    public static final int OPERATION_RIGHT_CLICK = 3;
    public static final int OPERATION_DEL_TEXT = 4;
    public static final int OPERATION_TYPE_TEXT = 5;



    private int operationKind = -1;

    // only valid when operationKind is OPERATION_MOVE
    private int moveX = -1;
    private int moveY = -1;

    //
    private String inputStr;

    public int getOperationKind() {
        return operationKind;
    }

    public void setOperationKind(int operationKind) {
        this.operationKind = operationKind;
    }

    public int getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    public String getInputStr() {
        return inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    @Override
    public String toString() {
        String oper;
        switch (operationKind) {
            case OPERATION_CLICK_DOWN:
                oper = "click down";
                break;
            case OPERATION_CLICK_UP:
                oper = "click up";
                break;
            case OPERATION_MOVE:
                oper = "move";
                break;
            case OPERATION_RIGHT_CLICK:
                oper = "right click";
                break;
            default:
                oper = "wrong operation";
                break;
        }
        return "OperationData [operationKind=" + oper + ", moveX=" + moveX + ", moveY=" + moveY + ", inputStr=" + inputStr + "]";
    }
}
