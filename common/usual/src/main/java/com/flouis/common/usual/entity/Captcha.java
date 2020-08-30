package com.flouis.common.usual.entity;

import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

public class Captcha {

	// 验证码
	@Getter
	private String code;

	// 图片
	@Getter
	private BufferedImage bufferedImage;

	// 随机数发生器
	private Random random = new Random();

	public Captcha(int width, int height, int codeCount, int lineCount){
		// 生成图像
		this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 背景色
		Graphics g = this.bufferedImage.getGraphics();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		Font font = new Font("Fixedsys", Font.BOLD, height - 5);
		g.setFont(font);

		// 生成干扰色和噪点
		for (int i = 0; i < lineCount; i++){
			int xs = this.random.nextInt(width);
			int ys = this.random.nextInt(height);
			int xe = xs + this.random.nextInt(width);
			int ye = ys + this.random.nextInt(height);
			g.setColor(getRandColor(1, 255));
			g.drawLine(xs, ys, xe, ye);
		}
		float yawpRate = 0.01f;
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			this.bufferedImage.setRGB(x, y, random.nextInt(255));
		}

		// 添加字符
		this.code = getRandString(codeCount);
		int fontWidth = width / codeCount;
		int fontHeight = height - 8;
		for (int i = 0; i < codeCount; i++){
			String str = this.code.substring(i, i+1);
			g.setColor(getRandColor(1, 255));
			g.drawString(str, i*fontWidth+3, fontHeight);
		}
	}

	private Color getRandColor(int fc, int bc){
		fc = fc > 255 ? 255 : fc;
		bc = bc > 255 ? 255 : bc;
		int r = fc + this.random.nextInt(bc - fc);
		int g = fc + this.random.nextInt(bc - fc);
		int b = fc + this.random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private String getRandString(int codeCount){
		String strDict = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder stringBuilder = new StringBuilder();
		int len = strDict.length() - 1;
		double index;
		for (int i = 0; i < codeCount; i++){
			index = (Math.random()) * len;
			stringBuilder.append(strDict.charAt((int)index));
		}
		return stringBuilder.toString();
	}

	public String getBase64ByteStr() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(this.bufferedImage, "png", baos);
		String s = Base64.getEncoder().encodeToString(baos.toByteArray());
		s = s.replaceAll("\n", "").replaceAll("\r", "");
		return "data:image/jpg;base64," + s;
	}

}
