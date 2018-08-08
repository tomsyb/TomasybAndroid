# GreenDao 3.2.0 的基本使用

greenDAO 是一个将对象映射到 SQLite 数据库中的轻量且快速的 ORM 解决方案

GreenDao特点

- 性能最大化，可能是Android平台上最快的ORM框架
- 易于使用的API
- 最小的内存开销
- 依赖体积小
- 支持数据库加密
- 强大的社区支持

## 配置

在build.gradle(Module:app)中添加下面代码：

```
buildscript {
      repositories {
             mavenCentral()
      }
      dependencies {
              classpath 'org.greenrobot:greendao-gradle-plugin:3.2.1'
       }
 }

 apply plugin: 'org.greenrobot.greendao'

 dependencies {
         compile 'org.greenrobot:greendao:3.2.0'
 }
```








