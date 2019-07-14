package com.example.thrift.client;

import com.example.thrift.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

@Slf4j
public class ThriftClient {
    public static void main(String[] args) {
        TTransport transport = null;
        try {
            //TSocket 参数分别是：服务器地址，端口，连接超时时间(毫秒)
            transport = new TSocket("localhost", 1998, 30000);
            TProtocol protocol = new TBinaryProtocol(transport);
            TestService.Client client = new TestService.Client(protocol);
            transport.open();

            //这里得到返回值是同步的
            String reply = client.sayHello("Hello, I'm Client");
            log.info(">>>收到回复消息：{}", reply);

            //如果需要异步，不要返回数据可以使用send_***方法
            //client.send_sayHello("Hello, I'm Client ~ ");
            //注意recv_***方法是同步的，会阻塞到收完数据
            //String replyAsync = client.recv_sayHello();

        } catch (Exception e) {
            log.error("Thrift客户端发生错误", e);
        } finally {
            if (transport != null) {
                transport.close();
            }
        }
    }
}
