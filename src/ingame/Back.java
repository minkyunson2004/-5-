package ingame;

import java.awt.Image;

public class Back {
	private Image image;
	private int x;
	private int y;
	private int width;
	private int height;

	public Back(Image image, int width, int i, int width1, int height) {
		this.image = image;
		this.x = width;
		this.y = i;
		this.width = width1;
		this.height = height;
	}

	public Image getImage() {
		return image;
    }

	public void setImage(Image image) {
		this.image = image;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
