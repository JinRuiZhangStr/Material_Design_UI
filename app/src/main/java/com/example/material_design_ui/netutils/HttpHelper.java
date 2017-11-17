package com.example.material_design_ui.netutils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Created by 张金瑞 on 2017/11/16.
 */

public class HttpHelper {


    public static String doGet(String visitUrl, Map<String,String> params,String headerInfo){


        String path = visitUrl;
        String newPath="";


        if (params!=null&&!params.isEmpty()){

            path+=("?");
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> map : entries) {
                path+=(map.getKey() + "=" + map.getValue()).concat("&");
            }
            //把拼接好的网站去掉最后一个"&"符号
            newPath = path.substring(0, path.length() - 1);

        }

        try {
            URL url = new URL(newPath ==""?visitUrl:newPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conn.getInputStream();

            byte[]by=new byte[1024];
            int len=0;
            StringBuffer sb=new StringBuffer();

            while((len=inputStream.read(by))!=-1) {
                String ss = new String(by, 0, len);
                sb.append(ss);
            }
            String request = sb.toString();



            return request;

        } catch (IOException e) {

        }


        return null;


    }
}
