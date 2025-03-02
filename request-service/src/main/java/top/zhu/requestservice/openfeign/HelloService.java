package top.zhu.requestservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "hello-service")
public interface HelloService {
    @GetMapping("/sayHello")
    String hi(@RequestParam String name);
}
