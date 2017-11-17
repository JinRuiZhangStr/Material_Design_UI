package com.example.material_design_ui.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;

import com.example.material_design_ui.netutils.HttpHelper;
import com.example.material_design_ui.netutils.HttpMethod;
import com.example.material_design_ui.netutils.NativaHttpCallback;

import java.util.Map;

/**
 * Created by 张金瑞 on 2017/11/16.
 */

public class NativeNetHelper extends AsyncTask<Void,Void,Message>{

    private Context mContext;
    private HttpMethod mMethod;
    private String visitUrl;
    private Map<String,String> mParams;
    private NativaHttpCallback mCallback;


    /**
     *
     * @param context  上下文
     * @param method   请求方式
     * @param url       请求网址
     * @param params    请求参数  以map的形式上传
     * @param callback   自定义接口
     */
    public NativeNetHelper(Context context, HttpMethod method, String url, Map<String,String> params, NativaHttpCallback callback){

        this.mContext = context;
        this.mMethod = method;
        this.visitUrl = url;
        this.mParams = params;
        this.mCallback = callback;

    };

    public NativeNetHelper submit(){

        execute();

        return this;
    }


    public static NativeNetHelper get(Context context,HttpMethod method,String visitUrl,Map<String,String> params,NativaHttpCallback callback){

        NativeNetHelper helper = new NativeNetHelper(context,method,visitUrl,params,callback).submit();

        return helper;

    }




    /**
     * 1、这个方法会在后台任务开始之前调用，用于进行一些界面上的初始化操作，比如显示一个进度条。。。
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try {
            if (mCallback != null){
                mCallback.onShowProgress();
            }
        } catch (Exception e) {
            e.printStackTrace();
            mCallback.onHideProgress();
            mCallback.Faield(e);
        }

    }


    /**
     * 2、这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理所有耗时的任务。
     * 任务一旦完成就可以通过return 语句来将任务的执行结果返回。
     *
     * 注意： 这个方法中不能进行ui操作,如果需要更新ui元素，可以调用publishProgress()方法来完成
     * @param
     * @return
     */
    @Override
    protected Message doInBackground(Void... params) {
        Message msg = new Message();
                String headerInfo = null;
        if (!isCancelled()){
            if (mCallback !=null){
                 headerInfo = mCallback.setHeaderInfo();
            }
            if (visitUrl!=null&&!visitUrl.isEmpty()){
               if (mMethod == HttpMethod.POST){
                   
               }
               
               if (mMethod == HttpMethod.GET){

                   String response = HttpHelper.doGet(visitUrl, mParams, headerInfo);

                   msg.obj = response;

                   return msg;
               }
            }
        }


        publishProgress();
        return null;
    }


    /**
     * 3、当在后台任务中调用了 publishProgress（）方法后，此方法就会很快被调用，该方法中携带的参数就是在后台任务中传递过来的。
     * 在这个方法中可以对ui进行操作，利用参数中的数值就可以对界面元素进行相应的更新。
     * @param params
     */
    @Override
    protected void onProgressUpdate(Void...params) {
        super.onProgressUpdate(params);
    }


    /**
     * 4、当后台任务执行完毕并通过return 语句进行返回时，这个方法就很快会被调用，返回的数据会作为参数传递到此方法中，可以利用
     * 返回的数据来进行一些ui操作，比如提醒任务执行的结果，以及关闭进度条。。。
     * @param o
     */
    @Override
    protected void onPostExecute(Message o) {
        super.onPostExecute(o);

        if (!isCancelled()){

            if (mCallback != null&& (String)o.obj!=null){

                Object o1 = mCallback.onCreateBean((String) o.obj);

                mCallback.Success(o1);
                mCallback.onHideProgress();
            }

        }

    }
}
