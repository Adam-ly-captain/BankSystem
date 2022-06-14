# Bank System
目前总共14个接口

| 接口                                                         | 说明                                                         | 样例                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| [http://120.79.139.163/bank/account/get/info/](http://120.79.139.163/bank/account/get/info/1){uid} | 根据用户ID获取用户信息                                       | [120.79.139.163/bank/account/get/info/1](http://120.79.139.163/bank/account/get/info/1) |
| http://120.79.139.163/bank/account/register                  | 注册账号，注册成功后会返回随机生成的账号                     | ![image-20220519183747773](https://github.com/Adam-ly-captain/SSM/blob/master/img/image-20220519183747773.png) |
| http://120.79.139.163/bank/account/login                     | 登录账号，登录成功后返回该用户所有信息                       | ![image-20220518012826193](https://github.com/Adam-ly-captain/SSM/blob/master/img/image-20220518012826193.png) |
| [http://120.79.139.163/bank/card/add](http://120.79.139.163/bank/card/add) | 添加银行卡                                                   | ![image-20220518014721734](https://github.com/Adam-ly-captain/SSM/blob/master/img/image-20220518014721734.png) |
| http://120.79.139.163/bank/card/get/{uid}                    | 通过传入用户ID获取该用户所有银行卡信息                       | http://120.79.139.163/bank/card/get/1                        |
| http://120.79.139.163/bank/card/del/{uid}                    | 通过传入银行卡ID删除该银行卡                                 | http://120.79.139.163/bank/card/del/8                        |
| http://120.79.139.163/bank/order/add                         | 添加交易记录，type为1代表取钱，0代表存钱                                                 | ![image-20220518025331331](https://github.com/Adam-ly-captain/SSM/blob/master/img/image-20220518025331331.png) |
| [http://120.79.139.163/bank/card/get](http://120.79.139.163/bank/card/get){cid} | 通过传入银行卡ID获取该银行卡对应的所有交易数据信息           | http://120.79.139.163/bank/order/get/1                       |
| http://120.79.139.163/bank/order/transfer                    | 银行卡之间的转账。srcCid是转账发起方的银行卡号，distCid是接收方的银行卡号，uid对应的是转账发起方的UID，amount是转账金额 | ![image-20220518031027272](https://github.com/Adam-ly-captain/SSM/blob/master/img/image-20220518031027272.png)![image-20220518030650655](https://github.com/Adam-ly-captain/SSM/blob/master/img/image-20220518030650655.png) |
| <form *action*="http://120.79.139.163/bank/file/avatar" *method*="post" *enctype*="multipart/form-data"> | 上传头像，并且返回UUID，可以在公网/bank/avatar下访问该头像[5bc49f28-6a27-4883-b27c-48a803b65c74.png (2560×1600)](http://120.79.139.163/bank/avatar/5bc49f28-6a27-4883-b27c-48a803b65c74.png)，头像大小不得超过1M，且不能为空 | ![image-20220518033157541](https://github.com/Adam-ly-captain/SSM/blob/master/img/image-20220518033157541.png) |
| http://120.79.139.163/bank/file/avatar/get/{uid}             | 根据用户ID获取UUID                                           | http://120.79.139.163/bank/file/avatar/get/1                 |
| http://120.79.139.163/bank/page/order                        | 获取分页订单数据                                             | ![image-20220519183147650](https://github.com/Adam-ly-captain/SSM/blob/master/img/image-20220519183147650.png) |
| http://120.79.139.163/bank/page/card                         | 获取分页银行卡数据                                           | ![image-20220519220528161](https://github.com/Adam-ly-captain/SSM/blob/master/img/image-20220519220528161.png) |
| http://120.79.139.163/bank/account/update                    | 更新用户信息，更新时如果输入的身份证号重复则返回错误提示     | ![image-20220519222558141](https://github.com/Adam-ly-captain/SSM/blob/master/img/image-20220519222558141.png) |



 



