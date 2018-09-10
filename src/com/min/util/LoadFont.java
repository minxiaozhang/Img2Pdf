package com.min.util;

import java.awt.Font;

import java.io.File;

import java.io.FileInputStream;
import java.io.InputStream;

public class LoadFont

{

	public static Font loadFont(String fontFileName,int fontFormat, float fontSize) // 第一个参数是外部字体名，第二个是字体大小
	{
		try
		{
			File file = new File(fontFileName);
			FileInputStream in = new FileInputStream(file);
			Font dynamicFont = Font.createFont(fontFormat,in);
			Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
			in.close();
			return dynamicFontPt;
		}
		catch (Exception e)// 异常处理
		{
			e.printStackTrace();
			return new java.awt.Font("宋体", Font.PLAIN,80);
		}

	}
	
	public static Font loadFont(InputStream in,int fontFormat, float fontSize) // 第一个参数是外部字体名，第二个是字体大小
	{
		try
		{
 
			Font dynamicFont = Font.createFont(fontFormat,in);
			Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
			in.close();
			return dynamicFontPt;
		}
		catch (Exception e)// 异常处理
		{
			e.printStackTrace();
			return new java.awt.Font("宋体", Font.PLAIN,80);
		}

	}
}
