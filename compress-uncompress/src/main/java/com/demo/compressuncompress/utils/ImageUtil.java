package com.demo.compressuncompress.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Iterator;
import java.util.Objects;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import lombok.extern.slf4j.Slf4j;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * 图片压缩工具类
 *
 * @author yueyang
 * @since 2021-12-27 15:47:00
 */
@Slf4j
public class ImageUtil {

  /** 图片长度 */
  public static final int IMAGE_LENGTH = 1000;

  /**
   * 压缩图片
   *
   * @param rowImage 原图片
   * @param destImage 目标图片
   */
  public static void compressPictureContent(File rowImage, File destImage) {
    String fileExtension = FileUtil.getFileExtension(rowImage);
    if (Objects.isNull(fileExtension) || isErrorType(fileExtension)) {
      return;
    }

    // 压缩
    BufferedImage srcImage;
    try {
      // 图片长宽小于1000不压缩
      srcImage = ImageIO.read(rowImage);
      if (srcImage.getWidth() < IMAGE_LENGTH && srcImage.getHeight() < IMAGE_LENGTH) {
        return;
      }
    } catch (IOException e) {
      log.error("获取图片内容出错：{}", e.getMessage());
      return;
    }

    // 计算压缩后的图片目标长和宽
    BufferedImage target;
    int width = (int) (0.3 * srcImage.getWidth());
    int height = (int) (0.3 * srcImage.getHeight());
    if (width < IMAGE_LENGTH) {
      width = IMAGE_LENGTH;
    }
    if (height < IMAGE_LENGTH) {
      height = IMAGE_LENGTH;
    }
    double sx = (double) width / srcImage.getWidth();
    double sy = (double) height / srcImage.getHeight();

    // 等比缩放
    if (sx > sy) {
      sx = sy;
      width = (int) (sx * srcImage.getWidth());
    } else {
      sy = sx;
      height = (int) (sy * srcImage.getHeight());
    }
    ColorModel cm = srcImage.getColorModel();
    WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
    boolean alphaPremultiplied = cm.isAlphaPremultiplied();
    target = new BufferedImage(cm, raster, alphaPremultiplied, null);
    Graphics2D g = target.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.drawRenderedImage(srcImage, AffineTransform.getScaleInstance(sx, sy));
    g.dispose();

    // 将转换后的图片保存
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      ImageIO.write(target, fileExtension, byteArrayOutputStream);
      FileOutputStream fos = new FileOutputStream(destImage);
      fos.write(byteArrayOutputStream.toByteArray());
      fos.flush();
      fos.close();
    } catch (IOException e) {
      log.error("图片压缩出错：{}", e.getMessage());
    }
  }

  /**
   * 压缩图片2
   *
   * @param rowImage 原图片
   * @param destImage 目标图片
   */
  public static void compressPictureContent2(File rowImage, File destImage) {
    String fileExtension = FileUtil.getFileExtension(rowImage);
    if (Objects.isNull(fileExtension) || isErrorType(fileExtension)) {
      return;
    }

    try {
      // 读取图像
      BufferedImage image = ImageIO.read(rowImage);

      // 对图像进行编码
      Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
      ImageWriter writer = writers.next();

      // 处理后的图片输出路径
      OutputStream os = new FileOutputStream(destImage);
      ImageOutputStream ios = ImageIO.createImageOutputStream(os);
      writer.setOutput(ios);

      // 设置压缩模式，压缩质量
      ImageWriteParam param = writer.getDefaultWriteParam();
      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

      // 设置 0-1 之间的数，0.0 表示尽最大程度压缩，1.0 表示保证图像质量很重要
      param.setCompressionQuality(0.01f);
      writer.write(null, new IIOImage(image, null, null), param);

      // 关闭流
      os.close();
      ios.close();
      writer.dispose();
    } catch (IOException e) {
      log.error("图片压缩出错：{}", e.getMessage());
    }
  }

  /**
   * 压缩图片3
   *
   * @param filePath 原图片路径
   * @param destImage 目标图片
   */
  public static void compressPictureContent3(String filePath, String destImage) {
    OpenCV.loadShared();

    // 读取图片
    Mat src = Imgcodecs.imread(filePath);

    // 压缩图片，IMWRITE_JPEG_QUALITY表示图片的质量进行改变，二个是质量因子，1-100，值越大表示质量越高
    MatOfInt dstImage = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, 1);
    Imgcodecs.imwrite(destImage, src, dstImage);
  }

  /**
   * 判断是否是正确的图片类型
   *
   * @param pictureExtension 图片拓展名
   * @return 是否是正确的图片类型
   */
  private static boolean isErrorType(String pictureExtension) {
    switch (pictureExtension) {
      case "jpg":
      case "png":
      case "JPG":
      case "jpeg":
        return false;
      default:
        return true;
    }
  }
}
