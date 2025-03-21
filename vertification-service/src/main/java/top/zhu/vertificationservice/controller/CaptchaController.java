package top.zhu.vertificationservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.zhu.vertificationservice.utils.CaptchaGenerator;

import javax.imageio.ImageIO;
import java.io.IOException;

@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getCaptcha(HttpServletRequest request) throws IOException {
        CaptchaGenerator.CaptchaResult result = CaptchaGenerator.generate();

        // 存储到Session（可替换为Redis存储）
        HttpSession session = request.getSession();
        session.setAttribute("captcha", result.code());

        return result.imageData();
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyCaptcha(@RequestParam String code,
                                           HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.badRequest().body("验证码已过期");
        }

        String storedCode = (String) session.getAttribute("captcha");
        if (storedCode == null) {
            return ResponseEntity.badRequest().body("请先获取验证码");
        }

        if (!storedCode.equalsIgnoreCase(code)) {
            return ResponseEntity.badRequest().body("验证码错误");
        }

        // 验证成功后移除验证码
        session.removeAttribute("captcha");
        return ResponseEntity.ok().body("验证成功");
    }
}