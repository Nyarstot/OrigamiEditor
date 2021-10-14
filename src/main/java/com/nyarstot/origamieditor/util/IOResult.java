package com.nyarstot.origamieditor.util;

public class IOResult<T> {
    // Private
    private T data;
    private boolean ok;
    // Public
    public IOResult(boolean ok, T data) {
        this.ok = ok;
        this.data = data;
    }

    public boolean s_ok() {
        return ok;
    }

    public boolean hasData() {
        return data != null;
    }

    public T getData() {
        return data;
    }
}
