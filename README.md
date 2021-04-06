# jwt
![](https://upload.cc/i1/2021/04/06/RYdgq5.jpg)

## 什麼是jwt？
Json web token (JWT), 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（(RFC 7519).该token被设计为紧凑且安全的，特别适用于分布式站点的单点登录（SSO）场景。

JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，也可以增加一些额外的其它业务逻辑所必须的声明信息，该token也可直接被用于认证，也可被加密。

## jwt和傳統cookie-session有什麼區別？
![](https://upload.cc/i1/2021/04/06/qi0nmg.png)
* 传统的cookie-session认证
    * 隨著用戶增加，session庫會變的越來越龐大，無法拓展，大型項目很難被採納
    * session需要有與其綁定的cookie並儲存在用戶瀏覽器，且cookie是綁定域名的，這就意味著session無法實現跨域和多平台共享
    * 時效性問題，一但server中相關數據更新，session就會丟失
---
![](https://upload.cc/i1/2021/04/06/Jy4XZR.png)
* jwt的優點
    * server端無需儲存大量的數據，只需要儲存salt即可
    * 跨平台
    * 時效性問題，jwt可以在生成的時候設定過期時間，在這段時間內server更新部署不會影響已產生的token
## Contact Author
石桂華(Skwen) https://blog.iskwen.com
# END
* Thank you for watching.