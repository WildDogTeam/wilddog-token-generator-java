
你可以将以下库添加到pom.xml中:

```xml
<dependency>
    <groupId>com.wilddog</groupId>
    <artifactId>wilddog-token-generator</artifactId>
    <version>1.0.0</version>
</dependency>
```


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
