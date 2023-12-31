# IvanPredator

一款对考拉助手V2的强化工具，强化对京东的无货捡漏场景下的自动化效率以及使用体验。

前端：3个油猴脚本：考拉助手v2+2个强化辅助脚本 负责完成挂机捡漏功能

后端：一个SpringBoot做的SMTP邮件后端 ，负责接收油猴脚本的调用，以及完成邮件分发和放货统计功能，默认端口开在了8080

## Feature

1.实现商品页面无货加购物车功能

2.实现完全自动化的捡漏，如果下单失败则会自动重试，完全自动挂机

3.实现捡漏成功自动发邮件提醒，防止未支付订单超时失效



## Run&Start

1.油猴先安装3个脚本，只启动无货加购脚本

![1687437832130](./image/1687437832130.png)



2.进入京东，选好商品，加购物车，因为已经启动了无货加购物车脚本，此时的商品页面即便无货，也能加到购物车里面

3.启动邮件后端SpringBoot程序，默认监听在8080端口，然后开启剩余两个脚本

IvanPredator.js中定义了各种抢购参数，在这个里面修改具体参数（频率默认1000ms一次，不建议修改，快了很容易被限流， 得不偿失）

**PS:考拉助手原版的HTML中的参数设置不要改，到IvanPredator.js中去改。**

4.进到购物车页面（开启了IvanPredator.js后会自动跳转），挂机就行了。

## Problem：

1.京东官方对用户访问购物车接口进行了限流操作，如果访问频率过快会导致接口报403,以及几分钟内无法继续访问购物车，实际测试发现一个账号的访问频次上限大概是800ms/次，说实话，很低，如果想提高效率，还得多个号一块抢，单个账号是有局限性的。

2.有些情况下订单页面如果支付失败，会跳转到首页，此时需要自动跳转回去，为了不影响正常使用，请在实际需要捡漏的时候再开启IvanPredator脚本。

