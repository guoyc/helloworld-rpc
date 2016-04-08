package com.gyc.server;

import com.gyc.impl.HelloWordImpl;
import com.weinong.rpc.BaseRpcConfig;
import com.weinong.rpc.BaseRpcServer;
import com.weinong.rpc.LogProxyHandler;
import com.gyc.rpc.api.HelloWordApi;
import yao.util.init.Initer;
import yao.util.log.Console;

/**
 * @author guoyuchuan
 * @Date 2016/4/6
 */
public class RpcServer extends BaseRpcServer {


    @Override
    public void registerProcessor() {
        LogProxyHandler<HelloWordImpl> weixinUserLogProxyHandler = new LogProxyHandler<>(new HelloWordImpl());
        this.addProcessor("HelloWordApi",
                new HelloWordApi.Processor((HelloWordApi.Iface)weixinUserLogProxyHandler.createProxy()));
    }

    public static void main(String[] args) throws Exception {
//        WeinongUserConf.init();
//        Initer initer;
//        initer = new Initer("user_db");
//        initer.registTool(WeinongUserDB.weinongUser());
//        initer.init();
        // 读取ini文件进行配置host和port
        BaseRpcConfig brc=new BaseRpcConfig("weinong", "helloword.ini");

        RpcServer sss = new RpcServer();
        sss.init(brc);
        Console.info(RpcServer.class, "User rpc server start!!");
        sss.start();
    }
}
