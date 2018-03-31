package cn.com.tianyudg.rxretrofitmvpdemo.basic.network;

import android.support.v4.util.ArrayMap;

import cn.com.tianyudg.rxretrofitmvpdemo.basic.model.BaseBean;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.model.Entity;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.service.ConvertService;

import java.io.Serializable;


public class RequestInfo {
    public static final int REQUEST_GET = 0x1; //查询数据请求
    public static final int REQUEST_POST = 0x2; //新增和修改数据请求
    public static final int REQUEST_DELETE = 0x3; //删除请求
    public static final int PAGE_SIZE = 10; //默认数据分页数据条数


    private   int                            requestType; //请求的类型
    private   int                            actionTye; //请求接口要做的操作类型。
    protected long                           dataExpireTime; //数据缓存时间默认为0即不缓存数据
    private   ArrayMap<String, Serializable> requestParams; //请求参数
    private boolean needShowDialog = true; //是否需要显示加载对话框
    private boolean needMockData = false; //是否需要模拟数据，此标识仅用于接口已定义，但未实现的前提下才能设置为true
    private String                          url;
    private String                          api; //请求的api
    private Class<? extends BaseBean>       dataClass; //请求结果Vo的class
    private Class<? extends Entity>         entityClass; //请求结果entity的class
    private Class<? extends ConvertService> serviceClass;

    public RequestInfo(String api, Class<? extends BaseBean> dataClass) {
        this.api = api;
        this.dataClass = dataClass;
        this.url = ApiConfig.HOST + api;
    }


    public RequestInfo(String url, long dataExpireTime, Class<? extends BaseBean> dataClass) {
        this(url, dataClass);
        this.dataExpireTime = dataExpireTime;
    }

    public Class<? extends BaseBean> getDataClass() {
        return dataClass;
    }

    public void setDataClass(Class<? extends BaseBean> dataClass) {
        this.dataClass = dataClass;
    }

    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }

    public int getActionTye() {
        return actionTye;
    }

    public void setActionTye(int actionTye) {
        this.actionTye = actionTye;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public long getDataExpireTime() {
        return dataExpireTime;
    }

    public void setDataExpireTime(long dataExpireTime) {
        this.dataExpireTime = dataExpireTime;
    }

    public ArrayMap<String, Serializable> getRequestParams() {
        return requestParams;
    }

    public ArrayMap<String, Serializable> put(String key, Serializable value) {
        requestParams.put(key, value);
        return requestParams;
    }

    public void setRequestParams(ArrayMap<String, Serializable> requestParams) {
        this.requestParams = requestParams;
    }

    public Class<? extends ConvertService> getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class<? extends ConvertService> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public boolean isNeedShowDialog() {
        return needShowDialog;
    }

    public void setNeedShowDialog(boolean needShowDialog) {
        this.needShowDialog = needShowDialog;
    }

    public boolean isNeedMockData() {
        return needMockData;
    }

    public void setNeedMockData(boolean needMockData) {
        this.needMockData = needMockData;
    }
}
