package com.forcode.base.design.fsm.v1.example;

import com.forcode.base.design.fsm.v1.event.AbstractEvent;

public class LocalStateEvent extends AbstractEvent<LocalStateEventType> {

    private String uuid;

    public LocalStateEvent(String uuid, LocalStateEventType type) {
        super(type);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
