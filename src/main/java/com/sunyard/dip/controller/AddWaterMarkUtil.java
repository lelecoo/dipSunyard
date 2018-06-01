package com.sunyard.dip.controller;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/5/31/0031.
 */
public class AddWaterMarkUtil {
    /**
    * 获取资源文件路径
    * */
    private String urlPath = AddWaterMarkUtil.class.getResource("/").getPath();
    private String absoluteUrl = urlPath.substring(0,urlPath.lastIndexOf("classes"));
    /**
     * 水印图片加载路径
    * */
    private String waterMark = absoluteUrl + "downloads/template/watermark/littlezjrcu.png";
    File waterMarkFile = new File(waterMark);


    // 添加水印方法,filePath为源图片路径
    public String addWaterMark(String filePath) {
        File file = new File(filePath);
        String fileFormat = filePath.substring(filePath.indexOf(".") + 1);
        FileOutputStream fileOutputStream = null;
        try {
            Image src = ImageIO.read(file);
            Image waterMark = ImageIO.read(waterMarkFile);
            // 获取图片宽度
            int width = src.getWidth(null);
            // 获取图片高度
            int height = src.getHeight(null);
            // 创建一个画布，图片缓存对象
            BufferedImage tarFrame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 创建输出流
            fileOutputStream = new FileOutputStream(filePath);
            // 绘制合成图像
            Graphics2D g = tarFrame.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            g.drawImage(waterMark, 0, 0, width, height, null);
            // 释放资源
            g.dispose();

            // 将绘制的图像生成至输出流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fileOutputStream);
            encoder.encode(tarFrame);
            fileOutputStream.close();

            // 将绘制的图像生成至输出流（使用ImageIO）
            ImageIO.write(tarFrame, fileFormat, fileOutputStream);

            // 将绘制的图像生成至输出流（使用ImageWriter）
            ImageWriter imageWriter  =   ImageIO.getImageWritersBySuffix(fileFormat).next();
            ImageOutputStream ios  =  ImageIO.createImageOutputStream(fileOutputStream);
            imageWriter.setOutput(ios);
            IIOMetadata imageMetaData  =  imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(tarFrame), null);
            imageWriter.write(imageMetaData, new IIOImage(tarFrame, null, null), null);
            ios.close();
            imageWriter.dispose();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePath;
    }
}
