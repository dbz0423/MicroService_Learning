package top.zhu.commonservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.zhu.commonservice.common.result.Result;
import top.zhu.commonservice.config.DeepSeekConfig;
import top.zhu.commonservice.service.DeepSeekService;

@RestController
@AllArgsConstructor
public class DeepSeekController {
    private final DeepSeekService deepSeekService;
    private final DeepSeekConfig deepSeekConfig;

    @GetMapping("/deepseek")
    public Result<DeepSeekConfig> deepSeek() {
        return Result.ok(deepSeekConfig);
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody String message) {
        try {
            String response = deepSeekService.chatCompletion(message);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}
