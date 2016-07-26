
--R360接口地址

http://localhost:8080/rBorrowList.action?product_id=469&t=20150408171942&token=ba6c9de676391d11d6b489bfa734ed70

http://localhost:8080/rBorrowMsg.action?product_id=469&t=20150408171942&token=ba6c9de676391d11d6b489bfa734ed70

http://dev.duomeidai.com/rBorrowList.action?page=1&count=1&t=20150408171942&token=ba6c9de676391d11d6b489bfa734ed70

http://dev.duomeidai.com/rBorrowMsg.action?product_id=469&t=20150408171942&token=ba6c9de676391d11d6b489bfa734ed70

http://test.duomeidai.com/rBorrowList.action
http://test.duomeidai.com/rBorrowMsg.action

http://www.duomeidai.com/rBorrowList.action?page=1&count=1&t=20150408171942&token=ba6c9de676391d11d6b489bfa734ed70

http://www.duomeidai.com/rBorrowMsg.action?product_id=206&t=20150408171942&token=ba6c9de676391d11d6b489bfa734ed70

--融360地址：
http://www.rong360.com/licai-p2p/list-a1239/

13888888888/111111
30000197/hechaojie

--发布dev环境--begin-------
cd /data/svntmp/dmd/
cd branches/
svn up
sh /data/deploy-script/home_deploy.sh dmd/branches/duomeidai-web-2.26.2 duomeidai-web
----end--------------------
--发布主干
sh /data/deploy-script/home_deploy.sh dmd/trunk/duomeidai/duomeidai-web duomeidai-web



dmd/trunk/dmd-app/app-economic-admin

sh /data/deploy-script/home_deploy.sh dmd/trunk/dmd-app/app-economic-admin app-economic-admin


--打包测试环境--begin----
cd /data/svntmp/dmd/branches/duomeidai-web-2.26.2
mvn clean install -Ptest
--war路径：/data/svntmp/dmd/branches/duomeidai-web-2.26.2/target/duomeidai-web.war
----------------end------

--打包生产环境--begin---
cd /data/svntmp/dmd/branches/duomeidai-web-2.26.2
mvn clean install -Pwww
--war路径：/data/svntmp/dmd/branches/duomeidai-web-2.26.2/target/duomeidai-web.war
---------------end------

sh /data/deploy-script/home_deploy.sh /dmd/tags/app-economic-admin/app-economic-admin-0.0.38 app-economic-admin

svn://svn.duomeidai.com/dmd/tags/app-economic-admin/app-economic-admin-0.0.38

--生产环境 
mvn clean install -Ptest

-------打tag-------
1、登录dev机器
2、cd  /data/svntmp/duomeidai/trunk/
3、svn update
4、cd   longdai-service
svn update
mvn release:prepare
      如失败：mvn release:rollback
mvn release:perform
     如失败，重新执行该命令。
     
     
经纪人平台：
http://dev.broker.duomeidai.com/login/index?wxOpenId=oKMiujmHEdQzWVrFeeHGpOHdYs1s
http://127.0.0.1:8083/login/index?wxOpenId=oKMiujmHEdQzWVrFeeHGpOHdYs1s#/brokerfront/login/0/0
http://127.0.0.1:8083/login/index?wxOpenId=oKMiujmHEdQzWVrFeeHGpOHdYs1s
--商家测试帐号
13439504367/123456
18888888219/123456
--经纪人测试帐号
15811372713/111111
http://dev.broker.duomeidai.com/p/u/a/broker/reserve/list?page=1&size=10&status=1

telent 192.168.1.235
1.列出所有的keys
stats items 
2.通过itemid获取key
stats cachedump 7 0 
3.通过get获取key值
get ssssssss

------刷新缓存--------
flush com.dmtz.dmd.red.model.GameScoreSetting

你如果按照ssh  就去ssh的目录下 应该有个ssh2.exe 
然后你执行
 ssh2 -R 80:localhost:8080 -l root peon.cn 
然后 你访问 peon.cn 就会看到 访问的 其实是你本机8080端口的项目
  
