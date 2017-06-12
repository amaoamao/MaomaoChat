

# MaomaoChat API规范

### 0. 规范

```js
{
  "error": {
    "code": /* err_code here */,
    "message": /* err_description here */
  },
  "data": {
    // data here
  }
}
```


### 1.注册相关

#### 1.0发送手机验证码

API:

```
GET http://xxx.xxx.xxx.xxx/api/signup/auth?phone=17761302891
```
返回示例：
```js
{
  "error": {
    "code": 0,
    "message": "发送成功"
    //如果发送失败，就返回别的error code和message
  }
}
```



#### 1.1验证手机验证码

*目前网易云信验证码服务停用，默认验证码1234*

API:
```
GET http://xxx.xxx.xxx.xxx/api/signUp/auth?phone=17761302891&code=1234
```
返回示例：
```js
{
  "error": {
    "code": 0,
    "message": "验证成功"
    //如果验证码不对，就返回别的error code和message
  },
  "data": {
    "phone":"17761302891",
    "token":"sdjcfhsXZisdjfc2rcjs"
    //随机生成一个字符串用来在注册的时候验证
  }
}
```
#### 1.2注册

API:

```
POST http://xxx.xxx.xxx.xxx/api/signUp
```

前端传输数据示例：

```json

{
	"user": {
      "name": "amaoamao",
      "phone": "17761302891",
      "password": "123456",
      "isMale": "true",
      "avatar": "asmkdcamskljdkaskjdpkapsofjowe"
	},
    "token": "sdjcfhsXZisdjfc2rcjs"
}
```
返回示例：
```js
{
  "error": {
    "code": 0,
    "message": "注册成功"
  },
  "data":null
}
```



### 2.登录

API:

```
POST http://xxx.xxx.xxx.xxx/api/signIn
```

前端传输数据示例：

```json

{
	"phone":"12345678901",
  	"password":"1234567890"
}
```

返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "登录成功"
  },
  
  "data": {
    "phone":"17761302891",
    "token":"sdjcfhsXZisdjfc2rcjs"
  }
}
```





### 3.聊天消息发送

**聊天消息传输采用JSON字符串而非Object**

API:

```
ws://xxx.xxx.xxx.xxx/api/chat/{token}
```

示例：

```
ws://xxx.xxx.xxx.xxx/api/chat/1234567fljhog35689
```

接收方枚举：

```java
0：个人
1：群组
2：频道
```

文件类型枚举

```java
0：Text
1：文件
```

**聊天消息传输采用JSON字符串而非Object**

前端消息传输：

```javascript
{
  "sender":"1234567890",
  
  "receiver":{
      "type":0,
      "id":"1234567890"
    },
    
  "message" :{
    	"type":0,
        "content":"12345678"
  }
}
```

后端消息传输：

*由后端进行时间戳添加，后端进行消息管理，保存了每一条消息记录，并且对消息记录添加是否发送的标志位，每次用户上线，将自动发送未接收的消息，不需要前端额外请求*

```java
{
  "sender":"1234567890",
  
  "receiver":{
      "type":0,
      "id":"1234567890"
    },
    
  "message" :{
    	"type":0,
        "content":"12345678"
         "time":"2017-5-26 19：30：00"
  }
}
```





**所有API带set字段的都要在Header中添加Token**

**所有API带set字段的都要在Header中添加Token**

**所有API带set字段的都要在Header中添加Token**

### 4.文件

#### 4.1 文件上传

API:

```
POST http://xxx.xxx.xxx.xxx/api/set/file/upload
```

前端传输：

```json
文件类型表单提交，注意：enctype="multipart/form-data"
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "上传成功"
  },
  
  "data":"maoamochat.tech/WEBINF/UserFile/12345678901/XXXXX.jpeg"
  
}
```

#### 4.2 文件下载

API:

```
POST http://xxx.xxx.xxx.xxx/api/set/file/download
```

前端传输：

```json
直接Request添加参数：fileUrl
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "下载成功"
  },
  
  "data":null
  
}
```



### 5.群组管理

#### 5.1创建群组

API:

```
POST http://xxx.xxx.xxx.xxx/api/set/group/create
```

前端传输：

```json
{
  "group":{
    "id":null,
    "name":"群组",
    "intro":"群组介绍",
    "founder":"创建人的手机号"
  }
}
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "创建成功成功"
  },
  
  "data":12345678901 // 创建的群ID，int
  
}
```



#### 5.2加入群组

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/group/mem/create?group=群组ID
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "添加成功"
  },
  
  "data":null
}
```

#### 5.3 退出群组

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/group/mem/delete?group=群组ID
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "添加成功"
  },
  
  "data":null
}
```



#### 5.4 更新群组

API:

```
POST http://xxx.xxx.xxx.xxx/api/set/group/update
```

前端传输：

```json
{
  "group":{
    "id":null,
    "name":"群组",
    "intro":"群组介绍",
    "founder":"发起更新动作人的手机号"//只有是创建人操作才能成功
  }
}
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "更新成功"
  },
  
  "data":null
  
}
```



#### 5.5 解散群组

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/group/delete?group=群组ID
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "解散成功"//只有是创建人操作才能成功
  },
  
  "data":null
}
```



#### 5.6获取列表

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/group/get
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "获取成功"
  },
  
  "data":[
    {
    "id":null,
    "name":"群组",
    "intro":"群组介绍",
    "founder":"创建人手机号"//只有是创建人操作才能成功
  },
    {
    "id":null,
    "name":"群组",
    "intro":"群组介绍",
    "founder":"创建人手机号"//只有是创建人操作才能成功
  }
  ]
}
```





### 6.频道管理

**功能同上，API改变**

#### 6.1创建频道

API:

```
POST http://xxx.xxx.xxx.xxx/api/set/channel/create
```

前端传输：

```json
{
  "channel":{
    "id":null,
    "name":"频道",
    "intro":"频道介绍",
    "founder":null
  }
}
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "创建成功"
  },
  
  "data":12345678901 // 创建的频道ID，int
  
}
```



#### 6.2加入频道

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/channel/mem/create?channel=频道ID
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "添加成功"
  },
  
  "data":null
}
```



#### 6.3 退出频道

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/channel/mem/delete?channel=频道ID
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "添加成功"
  },
  
  "data":null
}
```



#### 6.4 更新频道

API:

```
POST http://xxx.xxx.xxx.xxx/api/set/channel/update
```

前端传输：

```json
{
  "channel":{
    "id":null,
    "name":"频道",
    "intro":"频道介绍",
    "founder":"发起更新动作人的手机号"//只有是创建人操作才能成功
  }
}
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "更新成功"
  },
  
  "data":null
  
}
```



#### 6.5 解散频道

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/channel/delete?channel=频道ID
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "解散成功"//只有是创建人操作才能成功
  },
  
  "data":null
}
```



#### 6.6获取列表

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/channel/get
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "获取成功"
  },
  
  "data":[
    {
    "id":null,
    "name":"频道",
    "intro":"频道介绍",
    "founder":"创建人手机号"//只有是创建人操作才能成功
  },
    {
    "id":null,
    "name":"频道",
    "intro":"频道介绍",
    "founder":"创建人手机号"//只有是创建人操作才能成功
  }
  ]
}
```



### 7.好友关系

#### 7.1 添加

API:

**更新备注也用此API，相当于重新创建一次好友关系**

```
GET http://xxx.xxx.xxx.xxx/api/set/relation/create?friend=好友手机号&remark=备注名称
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "添加成功"
  },
  
  "data":null
}
```



#### 7.2 删除 

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/relation/delete?friend=好友手机号
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "删除成功"
  },
  
  "data":null
}
```



#### 7.3 一次获取好友列表及信息

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/relation/get
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "删除成功"
  },
  
  "data":[{
    		"id":0,
    		"phone":"12345678901",
    		"name":"用户的备注名",
    		"password":null,
    		"isMale":true,
    		"avatar":"url",
    		"token":null,
    		"expiredate":null	
          },
          {
            "id":0,
    		"phone":"12345678901",
    		"name":"用户的备注名",
    		"password":null,
    		"isMale":true,
    		"avatar":"url",
    		"token":null,
    		"expiredate":null	
          }
         ]//数组形式
}
```



### 8.用户

#### 8.1更新用户数据

API:

```
POST http://xxx.xxx.xxx.xxx/api/set/user/update
```

前端传输：

```json
{
  "user":{
    "id":0,
    "phone":"12345678901",
    "name":"昵称",
    "password":null,
    "isMale":true,
    "avatar":"url",
    "token":null,
    "expiredate":null	
  }
}
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "更新成功"
  },
  
  "data":null
  
}
```

#### 8.2根据手机号获取用户公开基本数据

API:

```
GET http://xxx.xxx.xxx.xxx/api/set/user/get?user=将查询此人的基本信息
```

后端返回示例：

```json
{
  "error": {
    "code": 0,
    "message": "更新成功"
  },
  
  "data":{
    
    "id":0,
    "phone":"12345678901",
    "name":"昵称",
    "password":null,
    "isMale":true,
    "avatar":"url",
    "token":null,
    "expiredate":null	
         
 } 
}
```

#### 