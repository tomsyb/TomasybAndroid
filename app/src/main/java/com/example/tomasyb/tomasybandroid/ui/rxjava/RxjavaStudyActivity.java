package com.example.tomasyb.tomasybandroid.ui.rxjava;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.base.mvp.BaseBean;
import com.example.tomasyb.baselib.base.retrofit.ExceptionHelper;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.util.TimeUtils;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.baselib.widget.LoadingDialog;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.base.ToolbarsBaseActivity;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.bean.MvpUseBean;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.net.Api;
import com.example.tomasyb.tomasybandroid.ui.rxjava.entity.User;
import com.example.tomasyb.tomasybandroid.ui.rxjava.entity.Video;
import com.example.tomasyb.tomasybandroid.ui.study.entity.TextEntity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
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
            .txjava_btn_net, R.id.txjava_btn_flatmap, R.id.txjava_btn_net_flamap, R.id
            .txjava_btn_zip, R.id.txjava_btn_net_zip, R.id.txjava_btn_filter, R.id
            .txjava_btn_sample,R.id.txjava_btn_flowable})
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
            case R.id.txjava_btn_net_flamap://演示先注册再登录
                netFlamap();
                break;
            case R.id.txjava_btn_zip://zip操作
                zip();
                break;
            case R.id.txjava_btn_net_zip://zip 网络合并
                zipNet();
                break;
            case R.id.txjava_btn_filter://过滤
                filter();
                break;
            case R.id.txjava_btn_sample://每隔一定时间取值
                simple();
                break;
            case R.id.txjava_btn_flowable://Flowable()
                flowable();
                break;

        }
    }
    //-----------------------------------------------------------------------------------Flowable

    /**
     * BackpressureStrategy.ERROR 用来选择背压，下游流速不均抛出MissingBackpressureException异常
     */
    private void flowable() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                LogUtils.e("发送1");
                e.onNext(1);
                LogUtils.e("发送2");
                e.onNext(2);
                LogUtils.e("发送3");
                e.onNext(3);
                LogUtils.e("完成");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        //Subscription.cancel()可切断水管，不接收上游事件
                        LogUtils.e("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.e("接收-->"+integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.e("onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("onComplete");
                    }
                });
    }

    /**
     * --------------------------------------------------------------sample间隔操作
     * sample 这里有缺陷，就是会有事件丢失
     * 这里实际处理了一个问题，如果去掉sample，内存直接爆炸，可以在循环处做延时操作，Thread.sleep(2000); 每次发送完事件延时2秒，很好解决事件不会丢失
     */
    private void simple() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
                .sample(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e(integer);
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

    //-----------------------------------------------------------------------------------zip多个网络请求合并
    private void zipNet() {
        LoadingDialog.showDialogForLoading(this);
        Observable<BaseBean<User>> loginObservable = Api.getInstance().getLogin("yinh", "123456")
                .subscribeOn(Schedulers.io());
        Observable<BaseBean<Video>> videoObservable = Api.getInstance()
                .getVideoList("d80f699c062c8662fad3df86024e246c").subscribeOn(Schedulers.io());

        Observable.zip(loginObservable, videoObservable, new BiFunction<BaseBean<User>,
                BaseBean<Video>, String>() {

            @Override
            public String apply(BaseBean<User> userBaseBean, BaseBean<Video> videoBaseBean)
                    throws Exception {
                return userBaseBean.getData().getName() + videoBaseBean.getDatas().get(0)
                        .getGroupName();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mTvShow.setText(s);
                        LoadingDialog.cancelDialogForLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showLong(ExceptionHelper.handleException(throwable));
                        LoadingDialog.cancelDialogForLoading();
                    }
                });
    }

    //-----------------------------------------------------------------------------------网络嵌套请求演示先注册再登录
    private void netFlamap() {
        Api.getInstance()
                .getLogin("yinh", "123456")
                .subscribeOn(Schedulers.io())//上游线程只能赋值一次
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<BaseBean<User>>() {
                    @Override
                    public void accept(BaseBean<User> userBaseEnty) throws Exception {
                        ////先根据注册的响应结果去做一些操作
                        LogUtils.e("登录成功--》" + userBaseEnty.getData().getName());
                    }
                }).observeOn(Schedulers.io())
                .flatMap(new Function<BaseBean<User>, ObservableSource<BaseBean<Video>>>() {
                    @Override
                    public ObservableSource<BaseBean<Video>> apply(BaseBean<User> userBaseEnty)
                            throws Exception {
                        if (userBaseEnty.getCode() == 0) {
                            return Api.getInstance().getVideoList(userBaseEnty.getData().getVcode
                                    ());
                        } else {
                            return null;
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<Video>>() {
                    @Override
                    public void accept(BaseBean<Video> videoBaseBean) throws Exception {
                        ToastUtils.showLong("请求成功" + videoBaseBean.getDatas().get(0).getGroupName
                                ());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showLong(ExceptionHelper.handleException(throwable));
                    }
                });
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
        Api.getInstance().getLogin("yinh", "123456")
                .subscribeOn(Schedulers.newThread())// 子线程请求数据
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .subscribe(new Consumer<BaseBean<User>>() {
                    @Override
                    public void accept(BaseBean<User> mvpUseBean) throws Exception {
                        mTvShow.setText(mvpUseBean.getData().getName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showLong(ExceptionHelper.handleException(throwable));//异常处理
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
     * 组合的顺序是严格按照事件发送的顺序来进行的，所以，可以看到，1 永远是和 A 结合的，--> 2和 B 结合的。以此类推，
     * 如果两个事件不指定线程那么执行顺序是发射器A先执行完再执行发射器B，如果添加线程就随机了但是组合顺序是不变的
     * 可以试一试subscribeOn(Schedulers.io())不要的结果
     */
    private void zip() {
        Observable<Integer> firstObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LogUtils.e("发送1");
                e.onNext(1);
                LogUtils.e("发送2");
                e.onNext(2);
                LogUtils.e("发送3");
                e.onNext(3);
                LogUtils.e("123onComplete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable<String> secondObservable = Observable.create(new ObservableOnSubscribe<String>
                () {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                LogUtils.e("发送A");
                e.onNext("A");
                LogUtils.e("发送B");
                e.onNext("B");
                LogUtils.e("ABonComplete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable.zip(firstObservable, secondObservable, new BiFunction<Integer, String, String>
                () {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.e("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                LogUtils.e("onNext结合后-->:" + s);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("onError");
            }

            @Override
            public void onComplete() {
                LogUtils.e("onComplete");
            }
        });
    }

    /**
     * ------------------------------------------------------------------------------------------------concat
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
