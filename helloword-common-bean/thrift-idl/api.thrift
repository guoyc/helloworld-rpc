namespace java com.gyc.rpc.api

// 引入bean.thrift文件,尽量都引入
include "bean.thrift"

// 提供服务的集合
service HelloWordApi {
    // 提供的方法
    bean.Person getPerson(1:i32 id);
    string sayHello(1:bean.Person person)
}

