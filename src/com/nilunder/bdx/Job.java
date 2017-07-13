package com.nilunder.bdx;

import com.nilunder.bdx.utils.Named;

public class Job<T extends GameObject> implements Named {
    public State state;
    public String name;
    protected T g;

    public Job(T g){
        this.g = g;
        name = this.getClass().getSimpleName();
    }

    public boolean main() {
        if (state != null) {
            state.main();
            return false;
        }

        return true;
    }

    @Override
    public String name() {
        return name;
    }

    public void state(State newState){
        if (state != null)
            state.exit();
        state = newState;
        if (state != null)
            state.enter();
    }
}
