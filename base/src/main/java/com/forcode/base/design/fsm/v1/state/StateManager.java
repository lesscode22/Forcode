package com.forcode.base.design.fsm.v1.state;

import com.forcode.base.design.fsm.v1.event.AbstractEvent;
import com.forcode.base.design.fsm.v1.event.Dispatcher;
import com.forcode.base.design.fsm.v1.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class StateManager<T extends AbstractEvent> implements EventHandler<T> {

    private List<StateListener> listeners = new ArrayList<>();
    private List<StateTransition> autoTransitions = new ArrayList<>();

    protected Dispatcher dispatcher;

    public StateManager(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        init();
    }

    /**
     * 初始话方法，子类实现
     */
    protected abstract void init();

    /**
     * 状态管理器标识
     */
    public abstract String managerKey();

    /**
     * 当前状态
     */
    protected abstract int currentState(String uuid);

    /**
     * 更新状态
     */
    protected abstract boolean setState(String uuid, int newState, int expectState);

    /**
     * 注册状态监听器
     */
    public synchronized void registerListener(StateListener listener) {
        if (listeners.contains(listener)) {
            return;
        }
        listeners.add(listener);
    }

    /**
     * 注册自动状态转换动作
     */
    protected void registerAutoTransition(StateTransition transition) {
        if (autoTransitions.contains(transition)) {
            return;
        }
        autoTransitions.add(transition);
    }

    /**
     * 执行状态转换操作
     */
    protected void doTransition(String uuid, StateTransition transition) {
        int current = currentState(uuid);
        if (!transition.preState().contains(current)) {
            return;
        }

        System.out.println(managerKey() + " manual do: currentState=" + current);
        System.out.println(managerKey() + " manual do: do something");
        int newState = transition.operation(uuid);
        System.out.println(managerKey() + " manual do: newState=" + newState);
        boolean isSuccess = setState(uuid, newState, current);
        System.out.println(managerKey() + " manual do: setState result=" + isSuccess);
        if (isSuccess) {
            notifyListeners(uuid, current, newState);
        }
    }

    /**
     * 自动同步状态
     */
    public void autoSyncState(String uuid) {
        List<StateTransition> innerAutoTransitions = new ArrayList<>(autoTransitions);
        if (innerAutoTransitions.size() == 0) {
            return;
        }

        int current = currentState(uuid);
        while (true) {
            StateTransition matchedTransition = null;
            for (StateTransition transition : innerAutoTransitions) {
                if (transition.preState().contains(current)) {
                    matchedTransition = transition;
                }
            }
            if (matchedTransition == null) {
                break;
            }
            System.out.println(managerKey() + " autoSync: currentState=" + current);
            System.out.println(managerKey() + " autoSync: do something");
            int newState = matchedTransition.operation(uuid);
            System.out.println(managerKey() + " autoSync: newState=" + newState);
            boolean isSuccess = setState(uuid, newState, current);
            System.out.println(managerKey() + " autoSync: setState result=" + isSuccess);

            notifyListeners(uuid, current, newState);

            if (isSuccess) {
                current = newState;
            } else {
                current = currentState(uuid);
            }
        }
    }

    private void notifyListeners(String uuid, int current, int newState) {
        try {
            // 通知
            List<StateListener> innerListeners = new ArrayList<>(listeners);
            for (StateListener listener : innerListeners) {
                System.out.println(managerKey() + " notify listener; uuid=" + uuid + ";oldState=" + current + ";newState=" + newState);
                listener.stateChanged(uuid, current, newState);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
