package top.zhu.vertificationservice.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class CaptchaGenerator {
    // 验证码字符集
    private static final char[] CHARS = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz".toCharArray();
    // 默认参数
    private static final int DEFAULT_WIDTH = 120;
    private static final int DEFAULT_HEIGHT = 40;
    private static final int DEFAULT_LENGTH = 4;
    private static final int DEFAULT_LINE_COUNT = 5;

    public static CaptchaResult generate() {
        return generate(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_LENGTH, DEFAULT_LINE_COUNT);
    }

    public static CaptchaResult generate(int width, int height, int length, int lineCount) {
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 生成随机字符
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = CHARS[random.nextInt(CHARS.length)];
            sb.append(c);
            // 随机颜色
            g.setColor(new Color(random.nextInt(150), random.nextInt(150), random.nextInt(150)));
            // 设置字体
            g.setFont(new Font("Arial", Font.BOLD, 28));
            // 绘制字符
            g.drawString(String.valueOf(c), 25 * i + 10, 28);
        }

        // 添加干扰线
        for (int i = 0; i < lineCount; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(random.nextInt(width), random.nextInt(height),
                    random.nextInt(width), random.nextInt(height));
        }

        // 转换图片为字节数组
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", bos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new CaptchaResult(sb.toString(), bos.toByteArray());
    }

    public record CaptchaResult(String code, byte[] imageData) {}
}
