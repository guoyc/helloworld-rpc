package com.gyc.impl;

import com.gyc.rpc.api.HelloWordApi;
import com.gyc.rpc.bean.Person;
import com.weinong.user.rpc.client.UserClient;
import org.apache.thrift.TException;

/**
 * @author guoyuchuan
 * @Date 2016/4/6
 */
public class HelloWordImpl implements HelloWordApi.Iface{

    @Override
    public Person getPerson(int id) throws TException {
        try {
            System.out.println(UserClient.getUserInfo(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Person person = new Person();
        person.setAge(10);
        person.setName("tomcat");
        person.setIs_man(false);
        return person;
    }

    @Override
    public String sayHello(Person person) throws TException {
        return person.toString() + "hello";
    }
}
