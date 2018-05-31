package cn.com.tianyudg.rxretrofitmvpdemo.rx;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.com.tianyudg.rxretrofitmvpdemo.util.MyUtil;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


/**
 * Author : WaterFlower.
 * Created on 2018/4/3.
 * Desc :
 */

public class RxBus {

    private static RxBus instance;

    private final Subject<Object> subject;
    private ConcurrentHashMap<Object, CompositeDisposable> disposableMap;

    private RxBus() {
        subject = PublishSubject.create().toSerialized();
    }


    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }

        return instance;
    }

    /**
     * 发布事件
     *
     * @param o 事件对象
     */
    public void post(Object o) {
        subject.onNext(o);
    }

    /**
     * 是否有人订阅
     *
     * @return true 如果有人订阅，否则false
     */
    public boolean hasObservers() {
        return subject.hasObservers();
    }


    /**
     * 用该方法生成一个能发射事件的Observable对象，
     * 只有该方法返回的Observable对象被订阅的时候，post(Object o)方法中
     * 的subject.onNext(o);语句才真正被执行
     *
     * @param clz  事件类对象
     * @param <T>  事件类型
     * @return    能发射事件的Observable
     */
    public <T> Observable<T> toObservable(Class<T> clz) {

        return subject.ofType(clz);
    }


    /**
     * 管理 订阅关系
     *
     * @param o
     * @param d
     */
    public void addDisposable(Object o, Disposable d) {
        if (disposableMap == null) {
            disposableMap = new ConcurrentHashMap<>();
        }

        String key = o.getClass().getName();
        CompositeDisposable compositeDisposable = disposableMap.get(key);
        if (compositeDisposable == null) {
            CompositeDisposable disposables = new CompositeDisposable();
            disposables.add(d);
            disposableMap.put(key, disposables);
        } else {
            compositeDisposable.add(d);
        }
    }


    /**
     * 这个会清掉对应订阅的所有的订阅者的订阅
     *
     * @param o
     */
    public void unSubscribe(Object o) {
        MyUtil.checkNotNull(disposableMap, "you have to add this disposable first");
        String key = o.getClass().getName();
        if (!disposableMap.containsKey(key)) return;

        MyUtil.checkNotNull(disposableMap.get(key), "you have to add this disposable first");

        disposableMap.get(key).dispose();
        disposableMap.remove(key);

    }


    public void unSubscribeAll() {
        if (disposableMap == null) return;

        for (Map.Entry<Object, CompositeDisposable> set : disposableMap.entrySet()) {
            CompositeDisposable value = set.getValue();
            if (value == null) continue;
            value.clear();
        }

        disposableMap.clear();

    }


}
