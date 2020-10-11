package com.stone.controller;


import cn.hutool.core.util.RandomUtil;
import com.stone.utils.QRCodeGenerator;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author stone
 */
@Api(tags = "二维码接口")
@RequestMapping("/qr")
@RestController
@Slf4j
public class QRController {
    /**
     * 二维码的信息，可以用微信、钉钉等任何能扫码的软件扫描
     * 1.如果是普通文本，则扫码显示文本信息
     * 2.如果是链接，则扫码后跳转链接的URL
     */
    private String INFO;

    {
        try {
            INFO = new String("https://zhuanlan.zhihu.com/p/111099137".getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用ResponseEntity响应文件流（页面显示），Spring会从里面取出数据塞入响应流返回
     *
     * @return
     */
    @GetMapping("qrImageByResponseEntity")
    public ResponseEntity<byte[]> getQRImageByResponseEntity() {
        byte[] qrCode = null;
        try {
            int num = RandomUtil.randomNumber();
            qrCode = QRCodeGenerator.getQRCodeImage("我是随机生成的数字 ：" + num, 360, 360);
        } catch (Exception e) {
            log.info("Could not generate QR Code, Exception:{}", e.getMessage());
        }

        // Header设置文件类型（对于ResponseEntity响应的方式，必须设置文件类型）
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(qrCode, headers, HttpStatus.CREATED);
    }

    /**
     * response原生响应文件流（页面显示）
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("qrImageByOutputStream")
    public void getQRImageByOutputStream(HttpServletResponse response) throws IOException {
        byte[] qrCode = null;
        try {
            qrCode = QRCodeGenerator.getQRCodeImage(INFO, 360, 360);
        } catch (Exception e) {
            log.info("Could not generate QR Code, Exception:{}", e.getMessage());
        }

        // Header设置文件类型（ContentType不设置也没事）
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.getOutputStream().write(qrCode);
    }

    /**
     * response原生响应文件流（下载）
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("downloadQRCode")
    public void downloadQRCode(HttpServletResponse response) throws IOException {

        byte[] qrCode = null;
        try {
            qrCode = QRCodeGenerator.getQRCodeImage(INFO, 360, 360);
        } catch (Exception e) {
            log.info("Could not generate QR Code, Exception:{}", e.getMessage());
        }
        String fileName = new String("激情二维码.png".getBytes("UTF-8"), "ISO-8859-1");
        // ContentType不设置也没事
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setHeader("content-disposition", "attachment;filename=" + fileName);
        response.setHeader("filename", fileName);
        response.getOutputStream().write(qrCode);
    }
}