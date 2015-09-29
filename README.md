WildDog Token Generator


当你想将野狗与已有的用户账号系统集成时，可以使用Token生成器来生成WildDog Token。
每位终端用户使用野狗的服务均要拥有WildDog Token。

WildDog Token用来对终端用户的身份进行校验，控制存储在WildDog端的数据读写权限，进而保证数据安全。


你可以将以下库添加到pom.xml中:

```xml
<dependency>
    <groupId>com.wilddog</groupId>
    <artifactId>wilddog-token-generator</artifactId>
    <version>1.0.0</version>
</dependency>
```

<br>

如果你没有使用Mave构建你的项目，你也可以从Maven仓库下载  http://search.maven.org/#search%7Cga%7C1%7Cwilddog-token-generator

<br>
<br>

示例代码：

```java
Map<String, Object> authPayload = new HashMap<String, Object>();
authPayload.put("uid", "1");

TokenOptions tokenOptions = new TokenOptions();
tokenOptions.setAdmin(true);

TokenGenerator tokenGenerator = new TokenGenerator("<YOUR_WILDDOG_SECRET>");
String token = tokenGenerator.createToken(authPayload, tokenOptions);
```
