package com.min.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 利用Itext 将图片导出pdf
 * @author zhangmin
 *
 */
public class ImgToPdf {
	
	/**
	 * 本地路径图片生成pdf
	 * @param imagePath
	 * @param pdfPath
	 */
	public static void exportFdf(String imagePath,String pdfPath) {
		try {
			BufferedImage img = ImageIO.read(new File(imagePath));
			FileOutputStream fos = new FileOutputStream(pdfPath);
			Document doc = new Document(null, 0, 0, 0, 0);
			doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
			Image image = Image.getInstance(imagePath);
			float scalePercentage = (72 / 300f) * 100.0f;
			image.scalePercent(scalePercentage, scalePercentage);
			PdfWriter.getInstance(doc, fos);
			doc.open();
			doc.add(image);
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 图片流导出为pdf
	 * @param bimg
	 * @param format
	 * @param pdfPath
	 */
	public static void exportFdf(BufferedImage bimg,String format,String pdfPath) {
		try {
			FileOutputStream fos = new FileOutputStream(pdfPath);
			Document doc = new Document(null, 0, 0, 0, 0);
			doc.setPageSize(new Rectangle(bimg.getWidth(), bimg.getHeight()));
			doc.newPage();
			// 获取页面宽度
			 float widths = doc.getPageSize().getWidth();
			// 获取页面高度
			 float heights = doc.getPageSize().getHeight();
			System.out.print("页面高度"+heights+"宽度"+ widths);
			ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			ImageIO.write(bimg,format,bOut);
			Image image = Image.getInstance(bOut.toByteArray());
			image.setAlignment(Image.ALIGN_CENTER);
//			float scalePercentage = (72 / 300f) * 100.0f;
//			image.scalePercent(scalePercentage, scalePercentage);
			PdfWriter.getInstance(doc, fos);
			doc.open();
			doc.add(image);
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 多个图片导出一个pdf文件
	 * @param args
	 */
	
	public static void exportImgsPdf(BufferedImage[] bimgs,String format,String pdfPath) {
		try {
			FileOutputStream fos = new FileOutputStream(pdfPath);
			Document doc = new Document();
			ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			PdfWriter.getInstance(doc, fos);
			doc.open();
			for(int i=0;i<bimgs.length;i++) {
			// 获取页面宽度
			Image image=null;
			doc.setPageSize(new Rectangle(bimgs[i].getWidth(), bimgs[i].getHeight()));
			doc.newPage();
			ImageIO.write(bimgs[i],format,bOut);
			image = Image.getInstance(bOut.toByteArray());
			image.setAlignment(Image.ALIGN_CENTER);
			System.out.println("宽度"+image.getWidth()+"、"+doc.getPageSize().getWidth()+"高度"+image.getHeight()+"、"+doc.getPageSize().getHeight());
			doc.add(image);
			bOut.flush();
			bOut.reset();
			}
			bOut.close();
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		exportFdf("A:\\test\\cc.png","A:\\\\test\\test.pdf") ;
//	}
}