package com.gyc.client;

import com.gyc.rpc.api.HelloWordApi;
import com.gyc.rpc.bean.Person;
import com.weinong.rpc.BaseRpcClient;
import com.weinong.rpc.BaseRpcConfig;
import org.apache.thrift.TApplicationException;

/**
 * @author guoyuchuan
 * @Date 2016/4/6
 */
public class HelloWordClient extends BaseRpcClient {

    public static HelloWordApi.Client client;

    static {
        try {
            // 读取配置文件
            BaseRpcConfig brc = new BaseRpcConfig("weinong", "helloword.ini");
            HelloWordClient user_client = new HelloWordClient();
            user_client.init(brc);
            // 这个api一定要与服务端的一致
            client = user_client.getApiClient(HelloWordApi.Client.class, "HelloWordApi");
            user_client.init(brc);
        } catch (Exception e) {
            client = null;
            e.printStackTrace();
        }
    }

    public static Person getPerson(int id) throws Exception {
        try {
            return client.getPerson(id);
            // 由于thirft不支持返回null,如果返回null的话会抛出如下异常,捕捉后返回null即可
        } catch (TApplicationException e) {
            return null;
        }
    }

    public static String sayHello(int age, String name, boolean is_man) throws Exception {
        try {
            Person person = new Person();
            person.setAge(age);
            person.setName(name);
            person.setIs_man(is_man);
            return client.sayHello(person);
        } catch (TApplicationException e) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getPerson(1));
        System.out.println(sayHello(1, "111", false));
    }
}
