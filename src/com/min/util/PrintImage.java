package com.min.util;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import com.min.po.StringContent;
 /***
  * 处理图片
  * @author zhangmin
  *
  */
public class PrintImage {
 
    private Font       font= LoadFont.loadFont("A:\\test\\shuyan.ttf",Font.PLAIN, 80f);
    private Graphics2D g = null;
    private int        fontsize = 40;
 
    /**
     * 导入本地图片到缓冲区
     */
    public BufferedImage loadImageLocal(String imgName) {
        try {
        	BufferedImage bufferImage;
        	Image image = ImageIO.read(new File(imgName)); 
        	int width = image.getWidth(null); 
        	int height = image.getHeight(null); 
        	bufferImage= new BufferedImage (width,height,BufferedImage.TYPE_INT_RGB); 
        	bufferImage.getGraphics().drawImage(image,0,0,width,height,image.getGraphics().getColor(),null);//设置底色 
        	return bufferImage;
        } catch (IOException e) {
        	/***
        	 * 将cmyk类型的图片转换为rgb格式
        	 */
        	 try 
        	    { 
        	     ThumbnailConvert tc = new ThumbnailConvert(); 
        	     tc.setCMYK_COMMAND(imgName); 
        	     Image image =null; 
        	     image = Toolkit.getDefaultToolkit().getImage(imgName); 
        	       MediaTracker mediaTracker = new MediaTracker(new Container()); 
        	     mediaTracker.addImage(image, 0); 
        	     mediaTracker.waitForID(0); 
        	      image.getWidth(null); 
        	     image.getHeight(null); 
        	    }catch (Exception e1){ 
        	     e1.printStackTrace(); 
        	    } 
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /**
     * 导入网络图片到缓冲区
     */
    public BufferedImage loadImageUrl(String imgName) {
        try {
            URL url = new URL(imgName);
            return ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /**
     * 生成新图片到本地
     */
    public void writeImageLocal(String newImage, BufferedImage img) {
        if (newImage != null && img != null) {
            try {
                File outputfile = new File(newImage);
                ImageIO.write(img, "jpg", outputfile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
 
    /**生成新图片到pdf
     * 
     * @param pdfPath 生成pdf 的路径 
     * @param img  要处理的图片
     * @param imgType 图片类型
     */
    public void writeImagePdfLocal(String pdfPath, BufferedImage img,String imgType) {
        if (pdfPath != null && img != null) {
            try {             
                ImgToPdf.exportFdf(img, imgType,pdfPath);
                 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**生成多个新图片到pdf
     * 
     * @param pdfPath 生成pdf 的路径 
     * @param img  要处理的图片
     * @param imgType 图片类型
     */
    public void writeImagesPdfLocal(String pdfPath, BufferedImage[] imgs,String imgType) {
        if (pdfPath != null && imgs != null) {
            try {             
                ImgToPdf.exportImgsPdf(imgs, imgType,pdfPath);
                 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * 设定文字的字体等
     */
    public void setFont(String fontStyle, int fontSize) {
        this.fontsize = fontSize;
        this.font = new Font(fontStyle, Font.PLAIN, fontSize);
    }
 
    /**
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
     */
    public BufferedImage modifyImage(BufferedImage img, Object content, int x, int y) {
 
        try {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
             g.setBackground(Color.WHITE);
             g.setColor(new Color(0, 0, 0));//设置字体颜色
            if (this.font != null)
                g.setFont(this.font);
            if (content != null) {
                g.translate(0 , 0);
                g.rotate(0);
                g.drawString(content.toString(), x, y);
            }
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
        return img;
    }
 
    
    /**
     * 修改图片,返回修改后的图片缓冲区（多行文本）
     */
    public BufferedImage modifyTextsImage(BufferedImage img,List<StringContent> content) {
 
        try {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
             g.setBackground(Color.WHITE);
             g.setColor(new Color(0, 0, 0));//设置字体颜色
            if (content != null) {
            	for(int i=0;i<content.size();i++) {
            		 if (content.get(i).getFont() == null)
                     	g.setFont(this.font);
            		 else
            			 g.setFont(content.get(i).getFont());
                     g.translate(0 , 0);
                     g.rotate(0);
                     g.drawString(content.get(i).getTextContent(),content.get(i).getX(), content.get(i).getY());
            	}
            }
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
        return img;
    }
    
    /**
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
     *
     * 时间:2007-10-8
     *
     * @param img
     * @return
     */
    public BufferedImage modifyImageYe(BufferedImage img) {
 
        try {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
            g.setBackground(Color.WHITE);
            g.setColor(Color.blue);//设置字体颜色
            if (this.font != null)
                g.setFont(this.font);
            g.drawString("www.hi.baidu.com?xia_mingjian", w - 85, h - 5);
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
        return img;
    }
 
    public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d) {
 
        try {
            int w = b.getWidth();
            int h = b.getHeight();
            g = d.createGraphics();
            g.drawImage(b, 100, 10, w, h, null);
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
        return d;
    }
    
    /**
     * 一张图添加一行字
     * @param Imgpath 要处理图片的地址
     * @param textContext 添加的文字内容
     * @param x 添加文字的坐标
     * @param y 添加文字的y坐标
     * @param pdfPath 生成pdf 的路径地址
     * @param imgType 图片的类型
     */
    public static  void doImgOneTextToPdf(String Imgpath,String textContext,int x,int y,String  pdfPath,String imgType) {
    	  PrintImage tt = new PrintImage();
          BufferedImage d = tt.loadImageLocal(Imgpath);
          tt.modifyImage(d, textContext,x,y);
          tt.writeImagePdfLocal(pdfPath, d,imgType);
          System.out.println("success");
    }
 
    
    
    /**
     * 一张图添加多行字
     * @param Imgpath 要处理图片的地址
     * @param textContext 添加的文字内容
     * @param x 添加文字的坐标
     * @param y 添加文字的y坐标
     * @param pdfPath 生成pdf 的路径地址
     * @param imgType 图片的类型
     */
    public static  void doImgTextsToPdf(String Imgpath,List<StringContent> stringContents,String  pdfPath,String imgType) {
    	  PrintImage tt = new PrintImage();
          BufferedImage d = tt.loadImageLocal(Imgpath);
          tt.modifyTextsImage(d, stringContents);
          tt.writeImagePdfLocal(pdfPath, d,imgType);
          System.out.println("success");
    }
    
    /***
     * 多张图添加多行字导出到一个Pdf文件
     */
    public static void doImgsTextsToPdf(String[] Imgpath,List<StringContent> stringContents,String  pdfPath,String imgType) {
    	 PrintImage tt = new PrintImage();
    	 BufferedImage[] d =new BufferedImage[Imgpath.length];
    	 for(int i=0;i<Imgpath.length;i++) {
    		 d[i] = tt.loadImageLocal(Imgpath[i]);
    	 }
        for(int i=0;i<Imgpath.length;i++) {
        	  tt.modifyTextsImage(d[i], stringContents);
        }
        tt.writeImagesPdfLocal(pdfPath, d,imgType);
         System.out.println("success");
    	
    }
    

 
}
