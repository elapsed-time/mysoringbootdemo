##controller类：逻辑控制器
AuthorizeController:Github登录控制  
IndexController:主界面控制  
PublishController:发布问题页面控制
##mapper类：数据库操作
UserMapper:对user表的操作
QuestionMapper:对question表的操作
##model类:数据库中传输数据（模型）
User:将要传输到user表的数据封装
Question:将要传输到question表的数据封装
##pojo类:网络中传输数据
AccessTokenPOJO:将发送第三方请求时需要的数据进行封装
GitHubUserPOJO:将第三方请求完成时得到的数据进行封装
QuestionPOJO:将用户的头像与发布的问题数据进行封装
PagePOJO:将分页的数据进行计算与封装
##provider类：提供第三方数据
GithubProvider:登录时提供第三方请求，并将请求得到的数据进行封装
##service：作为中间层来组装多个Mapper获得数据
QuestionService:作为中间层组装UserMapper和QuestionMapper，IndexController需要QuestionPOJO的数据，这些数据需要两个Mapper的操作
##templates：页面文件
index.html:主界面  
publish.html:问题发布页面  
navigation.html:将导航栏封装，省去每次修改导航栏都要修改所有导航栏页面的麻烦
##static：资源文件
##application.properties：配置文件
##DemoApplication：主函数main()