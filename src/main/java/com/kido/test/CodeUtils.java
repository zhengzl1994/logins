package com.kido.test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

public class CodeUtils {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    private CodeUtils() {
    }
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
//    public static void writeToFile(BitMatrix matrix, String format, File file)
//            throws IOException {
//        BufferedImage image = toBufferedImage(matrix);
//        if (!ImageIO.write(image, format, file)) {
//            throw new IOException("Could not write an image of format "
//                    + format + " to " + file);
//        }
//    }
    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }
    public static InputStream creatImage(String text) throws Exception
    {
        // text = "http://www.baidu.com"; // 二维码内容
        int width = 300; // 二维码图片宽度
        int height = 300; // 二维码图片高度
        String format = "jpg";// 二维码的图片格式
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
                BarcodeFormat.QR_CODE, width, height, hints);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CodeUtils.writeToStream(bitMatrix, format, out);
        return new ByteArrayInputStream(out.toByteArray());
    }

    public static void main(String[] args) throws Exception {
        InputStream inputStream = null;
        try {
            //这里放的是需要放入二维码的信息  可以直接放入链接地址
//            inputStream = CodeUtils.creatImage("https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842");
            inputStream = CodeUtils.creatImage("告白520");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            File file = new File("E:\\login\\二维码2.jpg");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
