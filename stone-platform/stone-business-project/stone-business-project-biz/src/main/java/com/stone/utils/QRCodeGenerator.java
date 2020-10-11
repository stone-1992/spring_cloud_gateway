package com.stone.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {
    /**
     * 生成二维码，写到本地
     *
     * @param text     二维码需要包含的信息
     * @param width    二维码宽度
     * @param height   二维码高度
     * @param filePath 图片保存路径
     * @throws WriterException
     * @throws IOException
     */
    private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    /**
     * 生成二维码，返回字节流
     *
     * @param text   二维码需要包含的信息
     * @param width  二维码宽度
     * @param height 二维码高度
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }
}
