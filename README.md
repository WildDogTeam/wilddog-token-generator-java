# WildDog Token Generator


当你想将野狗与已有的用户账号系统集成时，可以使用 Token 生成器来生成WildDog Token。
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

如果你没有使用Maven构建你的项目，你也可以从Maven仓库下载  http://search.maven.org/#search%7Cga%7C1%7Cwilddog-token-generator
<br>

## 重要提示

**注意:** 生成 Token 需要以你的超级密钥为参数，所以你应该仅在可信赖的服务器上生成 Token。另外，决不可把超级密钥存入你的应用程序中，也不要与客户端分享你的超级密钥。
## 可用方法

`TokenOptions` 对象有下面几个方法：

* **setExpires(Date)** - 设置过期时间点，此时间过后 Token 失效。

* **setNotBefore(Date)** - 设置生效时间点，到达此时间后 Token 才可用。

* **setAdmin(boolean)** - 若为 `true` ，规则表达式不再有效，客户端将拥有完全的读写权限。

* **setDebug(boolean)** - 若为 `true` ，将启动调试输出你的安全规则信息。通常在生产环境中，你不应该把它设置为true（它会把你的安全规则泄漏给你的用户），但它在调试时是非常有用的。

示例代码：

```java
Map<String, Object> authPayload = new HashMap<String, Object>();
authPayload.put("uid", "1");

TokenOptions tokenOptions = new TokenOptions();
tokenOptions.setAdmin(true);

TokenGenerator tokenGenerator = new TokenGenerator("<YOUR_WILDDOG_SECRET>");
String token = tokenGenerator.createToken(authPayload, tokenOptions);
```
