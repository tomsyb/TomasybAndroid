package com.tomasyb.rxjavalibs;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Rxjava学习主Activity
 */
public class RxjavaMainActivity extends AppCompatActivity{
    private Button mBtn_create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_main);
        initView();
    }

    private void initView() {
        mBtn_create = findViewById(R.id.btn_create);
        mBtn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rxjavaOnCreate();
            }
        });
    }

    /**
     * rxjava基础创建
     */
    private void rxjavaOnCreate() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //执行其他操作，完成后通知观察者
                emitter.onNext("我执行完毕");
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);

    }

}
