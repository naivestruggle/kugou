package com.hc.kugou.bean.custombean;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 验证码
 * @author Administrator
 *
 */
public class Verification implements Serializable {
	private int w = 70;
	private int h = 35;
	private String inCode = null;
	public String getInCode() {
		return inCode;
	}

	public void setInCode(String inCode) {
		this.inCode = inCode;
	}

	private Random r = new Random();
	/**
	 * 可选的字体   {"宋体","华文楷体","黑体","华文新魏","华文隶书","微软雅黑","楷体_GB2312"}
	 */
	private String[] fontNames = {"宋体","华文楷体","黑体","华文新魏","华文隶书","微软雅黑","楷体_GB2312"};
	/**
	 * 可选的字符
	 */
	private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
	/**
	 * 背景色
	 */
	private Color bgColor = new Color(255,255,255);
	/**
	 * 验证码上的字符
	 */
	private String text = null;

	/**
	 * 生成随机的颜色
	 * @return	返回一个Color对象
	 */
	private Color randomColor() {
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red,green,blue);
	}

	/**
	 * 生成随机的字体
	 * @return	返回一个Font对象
	 */
	private Font randomFont() {
		int index = r.nextInt(fontNames.length);
		//生成随机的字体名称
		String fontName = fontNames[index];
		//生成随机的样式  0(无样式)，1(粗体)，2(斜体)，3(粗体加斜体)
		int style = r.nextInt(4);
		//生成随机字号
		int size = r.nextInt(5)+24;
		return new Font(fontName,style,size);
	}

	/**
	 * 画干扰线
	 * @param image	图片
	 */
	private void drawLine(BufferedImage image) {
		//生成5条干扰线
		int num = 5;
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		//生成两个点的坐标
		for(int i=0;i<num;i++) {
			int x1 = r.nextInt(w);
			int y1 = r.nextInt(h);
			int x2 = r.nextInt(w);
			int y2 = r.nextInt(h);
			g2.setStroke(new BasicStroke(1.5F));
			//干扰线颜色
			g2.setColor(Color.BLUE);
			//两个点定一条线
			g2.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * 随机生成一个字符
	 * @return
	 */
	private char randomChar() {
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}

	/**
	 * 创建一张图片
	 * @return
	 */
	private BufferedImage createImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		g2.setColor(this.bgColor);
		g2.fillRect(0, 0, w, h);
		return image;
	}

	/**
	 * 调用这个方法得到验证码
	 * @return
	 */
	public BufferedImage getImage() {
		//创建图片缓冲区
		BufferedImage image = createImage();
		//得到绘制环境
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		//用来装载生成的验证码文本
		StringBuilder sb = new StringBuilder();

		//向图片中画4个字符   循环四次，每次生成一个字符
		for(int i=0;i<4;i++) {
			//随机生成一个字母
			String s = randomChar()+"";
			//把字母添加到sb中
			sb.append(s);
			//设置当前字符的x轴坐标
			float x = i * 1.0F * w/4;
			//设置随机字体
			g2.setFont(randomFont());
			//设置随机颜色
			g2.setColor(randomColor());
			//画图
			g2.drawString(s, x, h-5);
		}
		//把生成的字符串赋值给了this.text
		this.text = sb.toString();
		//添加干扰线
		drawLine(image);
		return image;
	}

	/**
	 * 返回验证码图片上的文本
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * 保存图片到指定的输出流
	 * @param image
	 * @param out
	 * @throws IOException
	 */
	public static void output(BufferedImage image,OutputStream out) throws IOException {
		ImageIO.write(image, "JPEG", out);
	}
}
