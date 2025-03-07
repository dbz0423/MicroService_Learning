package top.zhu.commonservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.zhu.commonservice.config.DeepSeekConfig;
import top.zhu.commonservice.model.entity.ChatRequest;
import top.zhu.commonservice.model.entity.ChatResponse;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeepSeekService {
    private final DeepSeekConfig config;

    @Qualifier("deepSeekRestTemplate")
    private final RestTemplate restTemplate;

    public String chatCompletion(String userMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(config.getKey());

        ChatRequest.Message systemMsg = new ChatRequest.Message("system", "You are a helpful assistant");
        ChatRequest.Message userMsg = new ChatRequest.Message("user", userMessage);

        ChatRequest request = new ChatRequest(
                "deepseek-chat",
                List.of(systemMsg, userMsg),
                false
        );

        ResponseEntity<ChatResponse> response = restTemplate.exchange(
                config.getUrl() + "/chat/completions",
                HttpMethod.POST,
                new HttpEntity<>(request, headers),
                ChatResponse.class
        );

        return response.getBody().getChoices().get(0).getMessage().getContent();
    }
}
