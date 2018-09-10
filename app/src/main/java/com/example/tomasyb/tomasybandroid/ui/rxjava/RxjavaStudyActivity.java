package com.example.tomasyb.tomasybandroid.ui.rxjava;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.util.TimeUtils;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.base.ToolbarsBaseActivity;
import com.example.tomasyb.tomasybandroid.bean.LoginUser;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.net.ApiService;
import com.example.tomasyb.tomasybandroid.net.BaseEnty;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.bean.MvpUseBean;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.net.Api;
import com.example.tomasyb.tomasybandroid.ui.study.entity.TextEntity;
import com.example.tomasyb.tomasybandroid.ui.study.entity.UpdateEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Rxjava学习的页面
 */
@Route(path = "/study/RxjavaActivity")
public class RxjavaStudyActivity extends ToolbarsBaseActivity {
    @BindView(R.id.tv_show)
    TextView mTvShow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava_study;
    }

    @Override
    public void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected String getSubTitle() {
        return "Rxjava2及Retrofit学习";
    }

    @OnClick({R.id.txjava_btn_create, R.id.txjava_btn_map, R.id.txjava_btn_thread, R.id
            .txjava_btn_net,R.id.txjava_btn_flatmap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txjava_btn_create:
                create();
                break;
            case R.id.txjava_btn_map:
                map();
                break;
            case R.id.txjava_btn_flatmap:
               flatMap();
                break;
            case R.id.txjava_btn_thread:
                thread();
                break;
            case R.id.txjava_btn_net:
                netQuest();
                break;
        }
    }

    //-----------------------------------------------------------------------------------网络请求演示线程的用法
    private CompositeDisposable mCompositeDisposable;

    //把网络请求添加进CompositeDisposable容器控制，界面销毁统一解除
    private void addDisposable(Disposable disposable) {
        //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//切断所有的网络请求
        }
    }

    /**
     * 1、取消所有网络请求 用 CompositeDisposable容器
     */
    private void netQuest() {
        Api.getInstance().test()
                .subscribeOn(Schedulers.newThread())// 子线程请求数据
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())// 主线程更新UI
                .subscribe(new Consumer<MvpUseBean>() {
                    @Override
                    public void accept(MvpUseBean mvpUseBean) throws Exception {
                        mTvShow.setText(mvpUseBean.getStories().get(0).getTitle());
                    }
                });

    }

    /**
     * -------------------------------------------------------------------------------------线程控制
     * 1.上游下游默认同一线程
     * 2.   Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
     * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
     * Schedulers.newThread() 代表一个常规的新线程
     * AndroidSchedulers.mainThread() 代表Android的主线程
     */
    private void thread() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LogUtils.e("上游线程-->" + Thread.currentThread().getName());
                e.onNext(1);
            }
        })      //指定上游线程,多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
                .subscribeOn(Schedulers.io())
                //指定下游线程,每调用一次observeOn() , 下游的线程就会切换一次.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e("下游线程-->" + Thread.currentThread().getName());
                    }
                });
    }

    /**
     * ------------------------------------------------------------------------------------创建create
     * throws Exception 自动抛出异常无须再try-catch
     * 1、ObservableEmitter 发送事件的
     * 2、上游可以无限onNext下游无限接收onNext
     * 3、当上游发送了一个onComplete(onError)后, 上游onComplete(onError)之后的事件将会继续发送, 而下游收到onComplete(onError)
     * 事件之后将不再继续接收事件.
     * 4、上游可以不发送onComplete或onError.
     * 5、onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError, 也不能先发一个onComplete,
     * 然后再发一个onError, 反之亦然
     */
    private void create() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("发送1");
                e.onNext("发送2");
                e.onComplete();//调用这个就会调用onComplete方法,之后继续onNext还会发送事件,但是下游不会接收事件
                e.onNext("发送3");
            }

            /**
             * subscribe有多个重载方法
             * 1、不带任何参数的subscribe() 表示下游不关心任何事件,你上游尽管发你的数据去吧, 老子可不管你发什么.
             * 2、带有一个Consumer参数的方法表示下游只关心onNext事件, 其他的事件我假装没看见,
             */
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
                if (i == 1) {
                    // 新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件，但是上游该怎么发还是怎么发
                    mDisposable.dispose();
                }
                mTvShow.append(s);
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
     * ----------------------------------------------------------------------------------------Map操作符
     * 数据转换操作
     * 这里我们将实体类打印他的名字
     */
    private void map() {
        Observable.create(new ObservableOnSubscribe<TextEntity>() {
            @Override
            public void subscribe(ObservableEmitter<TextEntity> e) throws Exception {
                TextEntity bean = new TextEntity("严博", 25);
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
                mTvShow.setText(s);
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
     * ----------------------------------------------------------------------------------------zip
     * 我们将两种类型进行合并处理
     * zip 组合事件的过程就是分别从发射器 A 和发射器 B 各取出一个事件来组合，并且一个事件只能被使用一次，
     * 组合的顺序是严格按照事件发送的顺序来进行的，所以，可以看到，我将发送 永远是和 12 结合的，--> 永远是和 434 结合的。
     */
    private void zip() {
        Observable.zip(getStringObservable(), getIntObservable(), new BiFunction<String, Integer,
                String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;//将事件进行合并
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                mTvShow.append(s);
            }
        });
    }

    /**
     * @return 获取String类型的Observable
     */
    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext("我将发送");
                    e.onNext("-->");
                }
            }
        });
    }

    /**
     * @return 获取Int类型的Observable
     */
    private Observable<Integer> getIntObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {//判断是否被截断
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
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        mTvShow.append(integer.toString());
                    }
                });
    }

    /**
     * ------------------------------------------------------------------------------------flatMap
     * 对于单一的把两个发射器连接成一个发射器
     * flatMap是无序的窝
     * 而concatMap用法和flatmap用法一样但是但是他有序
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
                    list.add("我的值:" + i);
                }
                //随机产生个时间做一个延时操作,把每个上游事件变成几个
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mTvShow.append(s);
                    }
                });
    }


    /**
     * --------------------------------------------------------------flatMap
     * 对于单一的把两个发射器连接成一个发射器
     * flatMap是无序的窝
     * 而concatMap用法和flatmap用法一样但是但是他有序
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
                    list.add("我的值:" + integer);
                }
                //随机产生个时间做一个延时操作
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mTvShow.append(s);
                    }
                });
    }

    /**
     * --------------------------------------------------------------去重
     */
    private void distinct() {
        Observable.just(1, 2, 3, 1, 4, 1, 6, 3)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mTvShow.append(integer.toString());
                    }
                });

    }

    /**
     * --------------------------------------------------------------filter过滤
     * filter
     */
    private void filter() {
        Observable.just(1, 45, 63, 12, 33, 90)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 60;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                mTvShow.append(integer.toString());
            }
        });

    }

    /**
     * --------------------------------------------------------------buffer
     */
    private void buffer() {
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        for (Integer num : integers) {
                            mTvShow.append(num.toString());
                        }
                    }
                });

    }

    /**
     * --------------------------------------------------------------timer延时操作
     * 这里延迟两秒操作
     */
    private void timer() {
        mTvShow.append(TimeUtils.getNowString());
        mTvShow.append("\n");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mTvShow.append(aLong + "at" + TimeUtils.getNowString());
                    }
                });
    }

    /**
     * --------------------------------------------------------------interval间隔时间操作
     * 延时操作当页面销毁的时候还是会执行我们用Disposable对象来操作
     */
    private Disposable disposable;

    private void interval() {
        mTvShow.append(TimeUtils.getNowString());
        mTvShow.append("\n");
        disposable = Observable.interval(3, 2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mTvShow.append(aLong + "at" + TimeUtils.getNowString());
                    }
                });
    }


    /**
     * --------------------------------------------------------------doOnNext
     */
    private void doOnNext() {
        Observable.just(1, 2, 3, 4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mTvShow.append("保存" + integer.toString());
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                mTvShow.append("最后" + integer);
            }
        });
    }

    /**
     * --------------------------------------------------------------单一的网络请求
     * 这里使用Retrofit请求网络
     */
    private void singleNet() {
//        Observable.create(new ObservableOnSubscribe<BaseEnty<LoginUser>>() {
//            @Override
//            public void subscribe(final ObservableEmitter<BaseEnty<LoginUser>> e) throws
// Exception {
//                RetrofitUtils.getRetrofit(Constant.BASE_URL).create(ApiService.class)
//                        .getUserMsg("yanb", "123456")
//                        .enqueue(new Callback<BaseEnty<LoginUser>>() {
//                            @Override
//                            public void onResponse(Call<BaseEnty<LoginUser>> call,
//                                                   Response<BaseEnty<LoginUser>> response) {
//                                e.onNext(response.body());
//                            }
//
//                            @Override
//                            public void onFailure(Call<BaseEnty<LoginUser>> call, Throwable t) {
//                                e.onError(t);
//                            }
//                        });
//            }
//            //map数据操作变换把对象通过某种形式转换位String并显示
//        }).map(new Function<BaseEnty<LoginUser>, String>() {
//            @Override
//            public String apply(BaseEnty<LoginUser> loginUserBaseEnty) throws Exception {
//                LogUtils.e("map的线程-->" + Thread.currentThread().getName());
//                return loginUserBaseEnty.getData().getName();
//            }
//        }).observeOn(AndroidSchedulers.mainThread())//数据接收之前操作
//                .doOnNext(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        mTvShow.append("数据保存" + s);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        LogUtils.e("subscribe 线程:" + Thread.currentThread().getName() + "\n");
//                        mTvShow.append("数据请求成功-->" + s);
//                    }
//                }, new Consumer<Throwable>() {//第二个是错误的接收
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        ToastUtils.showLong("请求错误");
//                    }
//                });
    }

    /**
     * --------------------------------------------------------------
     * 多个接口组合处理数据
     */
//    private void moreNet() {
//        Observable.zip(getUserObservable(), getUpdateObservable(), new
//                BiFunction<BaseEnty<LoginUser>, UpdateEntity, String>() {
//
//            @Override
//            public String apply(BaseEnty<LoginUser> loginUserBaseEnty, UpdateEntity updateEntity)
//                    throws Exception {
//                return "合并后的数据为:" + loginUserBaseEnty.getData().getName() + updateEntity
//                        .getUpdateTime();
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        mTvShow.setText(s);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        ToastUtils.showLong("请求错误");
//                    }
//                });
//
//    }
//
//    private Observable<BaseEnty<LoginUser>> getUserObservable() {
//        return Observable.create(new ObservableOnSubscribe<BaseEnty<LoginUser>>() {
//            @Override
//            public void subscribe(final ObservableEmitter<BaseEnty<LoginUser>> e) throws
// Exception {
//                RetrofitUtils.getRetrofit(Constant.BASE_URL).create(ApiService.class).getUserMsg
//                        ("yanb", "123456")
//                        .enqueue(new Callback<BaseEnty<LoginUser>>() {
//                            @Override
//                            public void onResponse(Call<BaseEnty<LoginUser>> call,
//                                                   Response<BaseEnty<LoginUser>> response) {
//                                e.onNext(response.body());
//                            }
//
//                            @Override
//                            public void onFailure(Call<BaseEnty<LoginUser>> call, Throwable t) {
//                                e.onError(t);
//                            }
//                        });
//            }
//        });
//    }

//    private Observable<UpdateEntity> getUpdateObservable() {
//        return Observable.create(new ObservableOnSubscribe<UpdateEntity>() {
//            @Override
//            public void subscribe(final ObservableEmitter<UpdateEntity> e) throws Exception {
//                RetrofitUtils.getRetrofit(Constant.BASE_UPDATAURL).create(ApiService.class)
//                        .getUpdateMsg("81383", "AppVersion", "daqsoft", "1", "2.0.0")
//                        .enqueue(new Callback<UpdateEntity>() {
//                            @Override
//                            public void onResponse(Call<UpdateEntity> call,
//                                                   Response<UpdateEntity> response) {
//                                e.onNext(response.body());
//                            }
//
//                            @Override
//                            public void onFailure(Call<UpdateEntity> call, Throwable t) {
//                                e.onError(t);
//                            }
//                        });
//            }
//        });
//
//    }


}
