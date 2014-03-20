package com.zombie.util;


import com.zombie.ZombieApplication;

public abstract class GuiCallback<T> implements Callback<T> {
    @Override
    public void callback(final T params) {
        ZombieApplication.postRunnable(new Runnable() {
            @Override
            public void run() {
                call(params);
            }
        });
    }


    protected abstract void call(T params);
}
