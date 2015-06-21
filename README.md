示例代码：

```java
Map<String, Object> authPayload = new HashMap<String, Object>();
authPayload.put("uid", "1");
authPayload.put("some", "arbitrary");
authPayload.put("data", "here");

TokenOptions tokenOptions = new TokenOptions();
tokenOptions.setAdmin(true);

TokenGenerator tokenGenerator = new TokenGenerator("<YOUR_WILDDOG_SECRET>");
String token = tokenGenerator.createToken(authPayload, tokenOptions);
```