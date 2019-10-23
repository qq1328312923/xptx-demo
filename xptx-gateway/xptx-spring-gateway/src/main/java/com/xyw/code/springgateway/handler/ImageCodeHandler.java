package com.xyw.code.springgateway.handler;

import com.xyw.code.core.constant.XptxConstant;
import com.xyw.code.core.utils.CaptchaUtil;
import com.xyw.code.redis.util.XptxRedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description:
 * @Date: Create in 下午3:48 2019/10/14
 */
@Slf4j
@Component
public class ImageCodeHandler implements HandlerFunction<ServerResponse> {

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        // 图片输出流
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        // 生成图片验证码
        BufferedImage image = CaptchaUtil.createImage();
        // 生成文字验证码
        String randomText = CaptchaUtil.drawRandomText(image);
        // 保存到验证码到 redis 有效期两分钟
        String t = request.queryParam("t").get();
        XptxRedisUtil.set(XptxConstant.XPTX_IMAGE_KEY + t, randomText.toLowerCase(), 2);
        try {
            ImageIO.write(image, "jpeg", os);
        } catch (IOException e) {
            log.error("验证码生成失败", e);
            return Mono.error(e);
        }
        //返回验证码图片信息
        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));
    }
}
