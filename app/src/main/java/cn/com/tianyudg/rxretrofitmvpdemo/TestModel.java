package cn.com.tianyudg.rxretrofitmvpdemo;


import cn.com.tianyudg.rxretrofitmvpdemo.basic.presenter.Callback;
import cn.com.tianyudg.rxretrofitmvpdemo.bean.IBean;
import cn.com.tianyudg.rxretrofitmvpdemo.contracts.TestContract;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by zhizhongbiao on 2018/5/31.
 */

public class TestModel implements TestContract.ITestModel {


    @Override
    public void login(String account, String psw, final Callback callback) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                               @Override
                               public void accept(String s) throws Exception {
                                   callback.onSuccess(null);
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                callback.onError(null);
                            }
                        });
    }
}
