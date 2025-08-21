# 计划与优化

1. 用户接口需要优化，不需要前端发送用户 id，前端携带 token 就已经是用户的全部信息了。
可以用 Request Attribute 将 id 传给 controller。如果用 Spring Security 就可以集成 JWT，但是我不用 Spring Security。

> 代码层已经改了，明天改一下接口。

~~ 2. 注册也要放行！~~

后端 todo：
2. 添加商品成功需返回 pid。
4. 商品修改后检查商品数量是否为 0
5. 添加恢复出售的接口。
获取收藏列表好像获取错了，按照product_fav的id获取了