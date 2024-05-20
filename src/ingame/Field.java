package ingame;

import java.awt.Image;

public class Field {
	
	private Image image; // ���� �̹���
	
	// ������ ��ǥ�� ���� ����
	private int x;
	private int y;
	private int width;
	private int height;

	public Field(Image image, int i, int i1, int i2, int i3) {
		this.image = image;
		this.x = i;
		this.y = i1;
		this.width = i2;
		this.height = i3;
	}

	public int getX() {
		return x;
    }

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}

