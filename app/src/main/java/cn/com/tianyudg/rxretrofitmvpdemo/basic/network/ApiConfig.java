package cn.com.tianyudg.rxretrofitmvpdemo.basic.network;


public class ApiConfig {

    public static void setHOST(String HOST) {
        ApiConfig.HOST = HOST;
    }

    public static String HOST = "http://dongguan.huifang.cn/";//测试环境
    //    public static  String HOST = "http://dg.huifang.cn/";//正式环境

    public static final String URL_ADDRESS = "api/hfw";

    //区域选择器列表
    public static final String API_REGION_SELECTOR_LIST = "api/publicRegionList";
    //条件选择器列表
    public static final String API_SUBJECT_SELECTOR_LIST = "api/publicLabelAndStandardData";
    //全部站点
    public static final String API_ALL_SITE_LIST = "api/getAllSite";
}

