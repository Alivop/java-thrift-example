package com.example.thrift;

import com.example.thrift.server.ThriftNIOServer;
import com.example.thrift.server.ThriftServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThriftApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThriftApplication.class, args);

		//ThriftServer ts = new ThriftServer();
        //ts.start();

        ThriftNIOServer tns = new ThriftNIOServer();
        tns.start();
	}

}
