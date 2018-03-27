# eumji-blog
  
 [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

这个项目是使用spring boot + thymeleaf 开发个人博客项目.

演示站下线了.太烧钱了.....<br>
大家看看就好了,如果有毅力自己部署上线也是可以的.

# 用户部分说明

作者抽空已经将用户修改，用户图像修改，密码修改等已经做了。
不过对裁剪图片的插件研究不深。做的比较粗糙。

此外需要注意的是，因为讲大部分公共信息已经封装在userinfo表中，所以此表不能为空。默认值作者已经给出了。

# 运行环境
- JDK 8
- Maven
- MySQL (or other SQL database)

# 主要技术

- Spring && Spring security && Spring boot
- Mybatis
- bootstrap
- flavr
- thymeleaf
- editor.md
- [展示页面模板icarus](https://github.com/ppoffice/hexo-theme-icarus)



# 安装步骤

0 - clone或者下载本项目

1 - 创建数据库zblog并导入跟目录eumji-blog.sql文件到数据库

2 - 更新 resources/application.yml中数据库连接配置和百度推送,七牛云的上次图片信息

3 - 编译项目: **mvn package -DskipTests** 或者导入ide,然后运行blogApplication.java

4 - 如果是编译项目,则使用 **java -jar target/eumji-blog.jar**

5 - http://localhost:8080/ 访问你的主页


## 后台模块

登陆路径 http://localhost:8080/login

默认账号 admin<br>
默认密码 admin

剩下的自己玩吧!

##注意事项

1.文章添加后默认是关闭的需要开启(关闭表示不展示)

2.如果运行有问题，请先检查查看一下错误的原因，一般来说是不会有大问题。

3.请自己配置百度推送的相关信息和腾讯云上传图片的相关配置(因为推送给我太多无用的链接了!!!)
百度站长工具: http://ziyuan.baidu.com/dashboard/index <br>
腾讯云: https://cloud.tencent.com/product/cos

```yml

## 腾讯云对象存储key
qcloud:
  APPID: 1253685092
  SecretId: AKIDBVerwpAPBm196tg9Y3RgszyngaYdALYg
  SecretKey: AAyUJ2Zu54ugIiy2oTUNAECyc8Ti2CAx
  Region: ap-chengdu
  BaseUrl: http://zblog-2-1253685092.cos.ap-chengdu.myqcloud.com/

## 百度推送
baidu:
  task:
    postUrl:
    baseUrl:


```



## License

The eumji-blog is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).

