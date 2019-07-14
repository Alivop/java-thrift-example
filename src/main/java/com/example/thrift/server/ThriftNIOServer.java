package com.example.thrift.server;

import com.example.thrift.service.TestService;
import com.example.thrift.service.TestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TNonblockingServerSocket;

@Slf4j
public class ThriftNIOServer {
    public void start() {
        try {
            log.info(">>>Thrift NIO 服务端开启");
            TProcessor tprocessor = new TestService.Processor<TestService.Iface>(new TestServiceImpl());
            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(1998);
            //NIO单线程版
            //TNonblockingServer.Args args = new TNonblockingServer.Args(serverSocket);
            //args.processor(tprocessor);
            //TNonblockingServer server = new TNonblockingServer(args);

            //NIO多线程版
            THsHaServer.Args args = new THsHaServer.Args(serverSocket);
            args.processor(tprocessor);
            args.minWorkerThreads(2);
            args.maxWorkerThreads(10);
            THsHaServer server = new THsHaServer(args);
            server.serve();

        } catch (Exception e) {
            log.error("Thrift服务端发生错误：", e);
        }
    }
}
