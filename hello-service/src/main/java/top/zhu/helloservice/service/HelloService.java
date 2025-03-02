package top.zhu.helloservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class HelloService {
    public String getName() {
        return "Hello Service";
    }


    public String sayHello(String name) {
        return "hello from " + name;
    }
}
