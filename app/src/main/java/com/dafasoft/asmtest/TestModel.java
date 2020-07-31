package com.dafasoft.asmtest;

import android.view.View;

/**
 * @author zhangyulong
 * @date 2020/7/31.
 * Description:
 */
public class TestModel {

    private TestListener listener;

    public void setListener(TestListener listener) {
        this.listener = listener;
    }


    public interface TestListener {
        void onClick(View view);
    }
}
