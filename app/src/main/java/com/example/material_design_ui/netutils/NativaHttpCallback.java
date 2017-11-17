package com.example.material_design_ui.netutils;

/**
 * Created by 张金瑞 on 2017/11/6.
 */

public interface NativaHttpCallback<T> {

    void Success(T bean);

    void Faield(Throwable ex);

    void onShowProgress();

    void onHideProgress();

    Object onCreateBean(String requestResult);

    String setHeaderInfo();
}
