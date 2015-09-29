
You can get the library by including the following in your project's pom.xml:

<dependency>
    <groupId>com.mixpanel</groupId>
    <artifactId>mixpanel-java</artifactId>
    <version>1.4.2</version>
</dependency>
If you're not using Maven to build your project, you can browse and download the library jar directly from Maven central at http://search.maven.org/#search|ga|1|mixpanel-java

可以搜索mave  http://search.maven.org/#search%7Cga%7C1%7Cwilddog-token-generator


示例代码：

```java
Map<String, Object> authPayload = new HashMap<String, Object>();
authPayload.put("uid", "1");

TokenOptions tokenOptions = new TokenOptions();
tokenOptions.setAdmin(true);

TokenGenerator tokenGenerator = new TokenGenerator("<YOUR_WILDDOG_SECRET>");
String token = tokenGenerator.createToken(authPayload, tokenOptions);
```
