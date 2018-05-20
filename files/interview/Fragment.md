# Fragment相关

# 1、Fragment加载到Activity中的两种方式

- 静态加载
- 动态加载

动态添加步骤：

1、添加一个FragmentTransaction的实例
FragmentManager manager = getFragmentManager();
FragmentTransaction action = manager.beginTransaction();

2、添加fragment
RightFragment rfg = new RightFragment();
action.add(R.id.right_container,rfg,"rightfragment")
action.addToBackStack("rightfragment");

3、commit

action.commit();

# 2、FragmentPagerAdapter 与FragmentStatePagerAdapter的区别

前试用页面较少，后试用多的，后者切换页面是回收内存的前者不会


源码分析：FragmentStatePagerAdapter的destroyItem方法中最后使用FragmentTransaction实例Remove掉fragment，真正释放内存
FragmentPagerAdapter的destroyItem方法中最后调用的是detach方法不是回收内存，只是把activity和fragment的UI分离开

# 3、fragment生命周期

！[官网周期图](https://images2015.cnblogs.com/blog/945877/201611/945877-20161123093212096-2032834078.png)