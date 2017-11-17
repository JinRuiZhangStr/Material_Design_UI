package com.example.material_design_ui.bean;

import java.util.List;

/**
 * Created by 张金瑞 on 2017/11/16.
 */

public class Fruit {


    /**
     * status : 200
     * data : {"picList":["https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2249618050,2178382755&fm=26&gp=0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501687025584&di=7926b44d338aa84a3c8e952dfe5be051&imgtype=0&src=http%3A%2F%2Fp13.qhimg.com%2Ft014cc0213c3ca8ead1.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501687333288&di=1cf1c58a1c174d00bebbee56cae95489&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F067%2F5116EPAUD762_1000x500.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501687333286&di=025ada80db878b72f373647ed104735e&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F121027%2F240425-12102H1261832.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501687333285&di=5e90bc88e19550d114b484d26bc18f43&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150113%2F139-150113092405.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501687333283&di=6d4fb5d056bf6ffc7d34ec5e21e7e9d1&imgtype=0&src=http%3A%2F%2Fimg15.3lian.com%2F2015%2Fa1%2F53%2Fd%2F163.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501687333282&di=108661f743b6a50b8dc03a74dffc6896&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F121119%2F240509-12111915051242.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501688684323&di=9f1493296e450621b2dbce1b2fda1490&imgtype=0&src=http%3A%2F%2Fimg5q.duitang.com%2Fuploads%2Fitem%2F201504%2F10%2F20150410H1516_FBwze.jpeg"],"nameList":["范冰冰","李冰冰","王丽坤","尹恩惠","樊蕊","张雨绮","林心如","张钧甯"]}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> picList;
        private List<String> nameList;

        public List<String> getPicList() {
            return picList;
        }

        public void setPicList(List<String> picList) {
            this.picList = picList;
        }

        public List<String> getNameList() {
            return nameList;
        }

        public void setNameList(List<String> nameList) {
            this.nameList = nameList;
        }
    }
}
