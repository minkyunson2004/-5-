package ingame;

import java.awt.Image;

public class Jelly {
	private Image image; // ���� �̹���
	
	// �������� ��ǥ�� ũ��
	private int x;
	private int y;
	private int width;
	private int height;
	
	// ������ ���� 0���� 255������
	private int alpha;
	
	// ������ ����
	private int score;

	public Jelly(Image image, int i, int i1, int i2, int i3, int i4, int i5) {
		this.image = image;
		this.x = i;
		this.y = i1;
		this.width = i2;
		this.height = i3;
		this.alpha = i4;
		this.score = i5;
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

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}

