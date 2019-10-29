package code;

import java.awt.Graphics;

public class Box extends Shape{
	int x, y, width, height;

	public Box(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}
