package top.zhu.requestservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ConsumerController3 {
    private final String SERVICE_URL = "http://localhost:8080";
    private final String SERVICE_URL1 = "https://www.wanandroid.com/project/list/1";

    private final WebClient webClient = WebClient.builder()
            .baseUrl(SERVICE_URL1).build();

    @GetMapping("/webClientTest")
    public String webClientTest() {
        Mono<String> mono = webClient
                .get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(String.class);
        mono.subscribe(System.out::println);
        return "请求成功";
    }

    @GetMapping("/webClientTest1")
    public String webClientTest(@RequestParam Integer id) {
        Mono<String> mono = webClient
                .get()
                .uri("/json?cid=" + id)
                .retrieve()
                .bodyToMono(String.class);
        String result = mono.block();
        return result;
    }
}
