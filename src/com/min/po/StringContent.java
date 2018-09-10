package com.min.po;

import java.awt.Font;

public class StringContent {
	
	/**
	 * 文字内容
	 */
	private String textContent;
	/**
	 * x坐标位置
	 */
	private int x;
	/**
	 * y坐标位置
	 */
	private int y;
	/**
	 * 字体
	 */
	private Font font;
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public StringContent(String textContent, int x, int y, Font font) {
		super();
		this.textContent = textContent;
		this.x = x;
		this.y = y;
		this.font = font;
	}
	
	
}
