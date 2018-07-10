package com.example.tomasyb.tomasybandroid.ui.study;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.util.TimeUtil;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.base.ToolbarBaseActivity;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.ui.comui.entity.Users;
import com.example.tomasyb.tomasybandroid.ui.study.entity.TextEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Rxjava学习的页面
 */
@Route(path = "/study/RxjavaActivity")
public class RxjavaStudyActivity extends ToolbarBaseActivity {
    @BindView(R.id.rxstudy_tv_content)
    TextView mTvContent;
    private int mPostion;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava_study;
    }

    @Override
    public void initView() {
        mPostion = getIntent().getIntExtra(Constant.STUDY_TYPE, 0);
    }

    @Override
    public void initPresenter() {

    }

    @OnClick(R.id.txjava_btn_show)
    public void onViewClicked() {
        matchData();
    }

    @Override
    protected String getSubTitle() {
        return getIntent().getStringExtra(Constant.STUDY_TITLE);
    }

    /**
     * 匹配数据
     */
    private void matchData() {
        switch (mPostion) {
            case 0:
                create();
                break;
            case 1:
                map();
                break;
            case 2:
                zip();
                break;
            case 3:
                concat();
                break;
            case 4:
                flatMap();
                break;
            case 5:
                concatMap();
            case 6:
                distinct();
                break;
            case 7:
                filter();
                break;
            case 8:
                buffer();
                break;
            case 9:
                timer();
                break;
        }
    }




    /**
     * --------------------------------------------------------------创建create
     * throws Exception 自动抛出异常无须再try-catch
     */
    private void create() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("发送1");
                e.onNext("发送2");
                e.onComplete();//调用这个就会调用onComplete方法,之后继续onNext还会发送事件,但是不会接收事件
                e.onNext("发送3");
            }
        }).subscribe(new Observer<String>() {
            private int i;
            private Disposable mDisposable;
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(String s) {
                i++;
                if (i==1){
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
                mTvContent.append(s);
                LogUtils.e(s);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                LogUtils.e("完成");
            }
        });
    }

    /**
     * --------------------------------------------------------------map
     * 数据转换操作
     * 这里我们将实体类打印他的名字
     */
    private void map() {
        Observable.create(new ObservableOnSubscribe<TextEntity>() {
            @Override
            public void subscribe(ObservableEmitter<TextEntity> e) throws Exception {
                TextEntity bean = new TextEntity("严博",25);
                e.onNext(bean);
            }
        }).map(new Function<TextEntity, String>() {//这里的意思就是把TextEntity实体类变换为String类型
            @Override
            public String apply(TextEntity textEntity) throws Exception {
                return textEntity.getName();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //Disposable 可用于切断操作
            }

            @Override
            public void onNext(String s) {
                mTvContent.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    /**
     * --------------------------------------------------------------zip
     * 我们将两种类型进行合并处理
     * zip 组合事件的过程就是分别从发射器 A 和发射器 B 各取出一个事件来组合，并且一个事件只能被使用一次，
     * 组合的顺序是严格按照事件发送的顺序来进行的，所以，可以看到，我将发送 永远是和 12 结合的，--> 永远是和 434 结合的。
     */
    private void zip() {
        Observable.zip(getStringObservable(), getIntObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s+integer;//将事件进行合并
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                mTvContent.append(s);
            }
        });
    }

    /**
     *
     * @return 获取String类型的Observable
     */
    private Observable<String> getStringObservable(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()){
                    e.onNext("我将发送");
                    e.onNext("-->");
                }
            }
        });
    }

    /**
     *
     * @return 获取Int类型的Observable
     */
    private Observable<Integer> getIntObservable(){
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()){//判断是否被截断
                    e.onNext(12);
                    e.onNext(434);//如果这里不写将只会打印我将发送12组合以最少的为准
                }
            }
        });
    }
    /**
     * --------------------------------------------------------------concat
     * 对于单一的把两个发射器连接成一个发射器
     */
    private void concat() {
        Observable.concat(Observable.just(1,2,3),Observable.just(4,5,6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        mTvContent.append(integer.toString());
                    }
                });
    }
    /**
     * --------------------------------------------------------------flatMap
     * 对于单一的把两个发射器连接成一个发射器
     * flatMap是无序的窝
     * 而concatMap用法和flatmap用法一样但是但是他有序
     *
     */
    private void flatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我的值:"+i);
                }
                //随机产生个时间做一个延时操作
                int delayTime = (int)(1+Math.random() *10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mTvContent.append(s);
                    }
                });
    }


    /**
     * --------------------------------------------------------------flatMap
     * 对于单一的把两个发射器连接成一个发射器
     * flatMap是无序的窝
     * 而concatMap用法和flatmap用法一样但是但是他有序
     *
     */
    private void concatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我的值:"+integer);
                }
                //随机产生个时间做一个延时操作
                int delayTime = (int)(1+Math.random() *10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mTvContent.append(s);
                    }
                });
    }

    /**
     * --------------------------------------------------------------去重
     */
    private void distinct() {
        Observable.just(1,2,3,1,4,1,6,3)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mTvContent.append(integer.toString());
                    }
                });

    }

    /**
     * --------------------------------------------------------------filter过滤
     * filter
     */
    private void filter() {
        Observable.just(1,45,63,12,33,90)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer>60;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                mTvContent.append(integer.toString());
            }
        });

    }

    /**
     * --------------------------------------------------------------buffer
     */
    private void buffer() {
        Observable.just(1,2,3,4,5)
                .buffer(3,2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        for (Integer num:integers){
                            mTvContent.append(num.toString());
                        }
                    }
                });

    }
    /**
     * --------------------------------------------------------------timer延时操作
     * 这里延迟两秒操作
     */
    private void timer() {
        mTvContent.append(TimeUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
        mTvContent.append("\n");
        Observable.timer(2,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mTvContent.append(aLong+"at"+TimeUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    }
                });
    }

}