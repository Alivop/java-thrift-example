package com.example.thrift.server;

import com.example.thrift.service.TestService;
import com.example.thrift.service.TestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

@Slf4j
public class ThriftServer {

    public void start() {
        try {
            log.info(">>>Thrift服务端开启");
            TServerSocket serverTransport = new TServerSocket(1998);
            TProcessor tProcessor = new TestService.Processor<TestService.Iface>(new TestServiceImpl());

            //单线程版
            TSimpleServer.Args sArgs = new  TSimpleServer.Args(serverTransport);
            sArgs.processor(tProcessor);
            TServer server = new TSimpleServer(sArgs);

            //多线程版
            /*
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
            tArgs.processor(tProcessor);
            tArgs.minWorkerThreads(2);
            tArgs.maxWorkerThreads(10);
            TServer server = new TThreadPoolServer(tArgs);
            */
            server.serve();

        } catch (Exception e) {
            log.error("Thrift服务端发生错误：", e);
        }
    }
}
