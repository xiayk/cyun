# 创云 cyun

#### 2020/03/30 XiaYk
- 修改登录接口
过滤部分不需要字段,返回用户基本信息,token
```json
{
  "status": 200,
  "msg": "操作成功",
  "data": {
    "id": "4e4208f076b149058d9aa0046b58efa8",
    "account": "0",
    "userName": "夏",
    "phone": "13856956857",
    "lastDate": null,
    "status": 0,
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiI0ZTQyMDhmMDc2YjE0OTA1OGQ5YWEwMDQ2YjU4ZWZhOCIsImV4cCI6MTU4NTY2NzgwNiwiYWNjb3VudCI6IjAifQ.2eboo5_XFbjDpkcpM162hfJVnAIdBPWXlNbYIxUGVe4"
  }
}
```
- 修改token格式(JWT) CYUN-AUTH-{uid} / token 固定token key值

- 拦截器添加token验证