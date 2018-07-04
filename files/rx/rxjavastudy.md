# Rxjava专栏

## **目录**

| 说明 | 序号 | 备注 |
| ------------- |:-------------| :-----|
| github地址及配置| 1| ... |
| Rxjava学习资源| 2| ... |
| 概述| 3| ... |
| dfs| 1| ... |
| dfs| 1| ... |
| dfs| 1| ... |
| dfs| 1| ... |


### 1、github地址：

[Rxjava](https://github.com/ReactiveX/RxJava)
[RxAndroid](https://github.com/ReactiveX/RxAndroid)

- 配置

gradle:(版本请查看官网最新)

```
compile 'io.reactivex:rxjava:1.0.14'
compile 'io.reactivex:rxandroid:1.0.1'
```

### 2、更多学习地址：

- [手把手教你使用Rxjava2.0](https://www.jianshu.com/p/d149043d103a)
- [给初学者的RxJava2.0教程](https://www.jianshu.com/p/464fa025229e)
- [给Android开发者的RxJava详解](http://gank.io/post/560e15be2dca930e00da1083)


### 3、概述

异步，逻辑复杂依然保持极强的阅读性,使用观察者模式

Rxjava观察者模式

- Observable: 被观察者
- Observer: 观察者 接受Observable发送的数据
- subscribe:订阅，观察者与被观察者通过subscribe()进行订阅
- Subscriber:也是一种观察者，在2.0中它与Observer没有实质区别不同的是 Subscriber要与Flowable(也是一种被观察者)联合使用，
该部分内容是2.0新增的，后续文章再介绍。Obsesrver用于订阅Observable，而Subscriber用于订阅Flowable


### **Observable操作符**

| 操作符 | 说明 | 备注 |
| ------------- |:-------------| :-----|
| 创建| create()| ... |
| just(T...)| 将传入的参数依次发送出来| ... |
| from(T[]) / from(Iterable<? extends T>)| 将传入的数组或 Iterable 拆分成具体对象后，依次发送出来| ... |
| map()| 对象转换一对一| 使用列子见附1 |
| dfs| 1| ... |
| dfs| 1| ... |
| dfs| 1| ... |

#### 附1、map()

场景将对象装换为String并打印

```
IndexTable[] table = {new IndexTable("张三"),new IndexTable("李四")};
        /**
         * Func1 和Action1类似用于包装含有一个参数的方法，
         * 区别：
         * 不同：Func1包装有返回值，
         * 同：ActionX,FuncX有多个，用于不同参数的方法
         */
        //使用just可连续传值from是数组
        Observable.from(table)//输入类型为数组
                .map(new Func1<IndexTable, String>() {//前面是需要转换的类型，后面是转换后的类型
                    @Override
                    public String call(IndexTable table) {//参数类型IndexTable
                        return table.getIndexTopName();//返回类型String
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.e(s);
            }
        });
```


#### 附2、flatMap()

场景: 打印一个学生数组每个学生锁修课程的名字

```
Student[] students = new Student[3];
        for (int i = 0; i < 3; i++) {
            Student student = new Student();
            List<Student.Course> courseList = new ArrayList<>();
            for (int j = 0;j < 4; j++) {
                Student.Course course = new Student.Course();
                course.setCourse("语文"+j);
                courseList.add(course);
            }
            student.setName("张三"+i);
            student.setmCourse(courseList);
            students[i] = student;
        }
        //打印多个学生的所有所学课程
        Observable.from(students).subscribe(new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {
//                for (int i = 0; i < student.getmCourse().size(); i++) {
//                    LogUtils.e("课程-->"+student.getmCourse().get(i).getCourse());
//                }
            }
        });

        //换用flatMap方法
        /**
         * flatMap返回Observable对象并用Observable对象发送
         */
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Student.Course>>() {
                    @Override
                    public Observable<Student.Course> call(Student student) {
                        return Observable.from(student.getmCourse());
                    }
                }).subscribe(new Subscriber<Student.Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student.Course course) {
                LogUtils.e(course.getCourse());
            }
        });
```
