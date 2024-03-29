package code;

import java.awt.Graphics;

public class Line extends Shape{
	int x1, x2, y1, y2;

	public Line(int x1, int y1) {
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public void setX1(int x1) {
		this.x1 = x1;
	}
	
	public void setX2(int x2) {
		this.x2 = x2;
	}
	
	public void setY1(int y1) {
		this.y1 = y1;
	}
	
	public void setY2(int y2) {
		this.y2 = y2;
	}

	@Override
	public void draw(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}
}
