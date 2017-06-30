# spring boot 版本貌似与jpa版本有些不兼容,没时间排查,所以用了低版本 ...

 **环境:Fedola-24、jdk-1.8、gradle-2.14.1、maven-3.3**
 
 整合框架:spring boot、jpa(hibernate)、springMvc(数据校验),guava(限流器)
 
 **运行步骤**
 1. gradle build -xtest 打jar包
 2. 项目下/build/libs 下jar包 java -jar community.jar & 即可运行