package com.min.util;

import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import com.min.po.StringContent;

public class Main {
    public static void main(String[] args) {
    	
    	//String rootPath=Main.class.getClass().getResource("").getPath();
    	//System.out.println(rootPath);
    	Font font1=LoadFont.loadFont(Main.class.getClass().getResourceAsStream("/shuyan.ttf"),Font.PLAIN, 100f);
    	Font font2=LoadFont.loadFont(Main.class.getClass().getResourceAsStream("/shuyan.ttf"),Font.PLAIN, 50f);
    	StringContent s1=new StringContent("呵呵哒", 1441,1793,font1);
    	 StringContent s2=new StringContent("HEHEDA", 1441,1850, font2);
    	List<StringContent> list=new LinkedList<StringContent>() ;
    	list.add(s1);
    	list.add(s2);
    	//  导出一个图片到pdf  	
    	//doImgTextsToPdf("A:\\test\\2.jpg",list,"A:\\test\\1.pdf","png");
     	String[] a= {"A:\\test\\2.jpg","A:\\test\\ll.jpg","A:\\test\\3.jpg"};
     	//导出多个图片到pdf 
     	PrintImage.doImgsTextsToPdf(a,list,"A:\\test\\2.pdf","jpg");
//       PrintImage tt = new PrintImage();
//        BufferedImage d = tt.loadImageLocal("A:\\test\\2.jpg");
//        tt.modifyImage(d, "刘德华",1441,1793);
//        tt.modifyImage(d, "LIU DE HUA",1441,1800);
//        tt.writeImagePdfLocal("A:\\test\\1.pdf", d,"png");
//        System.out.println("success");
    }
}
