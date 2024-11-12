使用方法：
前言：
****注意！此项目使用spring3！如需添加依赖项注意版本对应！（可以查依赖项的版本对应表）****
环境配置包括idea的配置各位看黑马的就可以了，比较多，讲不清楚，各位跟着黑马来就好了
项目还用了knife4j（swagger后端接口变体）地址：
    http://localhost:8080/demo/doc.html#/home
        # swagger用户名
        username: user
        # swagger密码
        password: 123456
端口以及项目根节点：
    server:
      servlet:
        context-path: /demo # ***很重要，接口也好，web地址也好，最前面一定要加上这个！（不管是本地还是客户端）
      port: 8080 # 监听端口
首先配置好数据库database（注意我用的是mysql）
ddl文件在software文件夹中，用ide打开导入，随便加点数据，能登录用户和管理员就行
database配置（Application.yml）:
spring:
  application:
    name: demo
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver # 使用mysql请注意
    url: jdbc:mysql://localhost:3306/software?serverTimezone=UTC
    username: root
    password: 165831

启动服务器（已使用tomcat服务器）：
打开笔记本移动热点，作为本地服务器
客户端（手机，笔记本...）连接到热点
作为本地服务器的笔记本打开命令行：win+r 输入cmd 确认
在命令行中输入ipconfig
找到热点提供的服务器IP地址（不是dhcp分配的那个）:
搜索结果中找到这个：

无线局域网适配器 本地连接* 2:

   连接特定的 DNS 后缀 . . . . . . . :
   //......
   IPv4 地址 . . . . . . . . . . . . : 192.168.137.1
   子网掩码  . . . . . . . . . . . . : 255.255.255.0
   默认网关. . . . . . . . . . . . . :

一般就是192.168.137.1了
现在启动springweb（DemoApplication.java）
在客户端web应用（推荐edge）中输入:
    http://192.168.137.1:8080/demo/
8080为端口号，demo为项目根节点
接下来就可以使用教材管理系统了。

