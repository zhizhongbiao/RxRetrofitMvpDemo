package cn.com.tianyudg.rxretrofitmvpdemo.rx;

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
     * @param o
     */
    public void post(Object o) {
        subject.onNext(o);
    }

    /**
     * 是否有人订阅
     *
     * @return
     */
    public boolean hasObservers() {
        return subject.hasObservers();
    }


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
     * 这个会清掉所有的订阅者的订阅
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

}