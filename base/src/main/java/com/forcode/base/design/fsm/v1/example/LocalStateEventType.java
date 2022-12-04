package com.forcode.base.design.fsm.v1.example;

public enum LocalStateEventType {

    DoSomething(1, "do something"),
    Sync(2, "同步"),
    ;

    int type;
    String remark;

    LocalStateEventType(int type, String remark) {
        this.type = type;
        this.remark = remark;
    }
}
