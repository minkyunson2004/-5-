package ingame;

import javax.swing.ImageIcon;

public class CookieImg {
	private ImageIcon cookieIc; // �⺻���
	private ImageIcon jumpIc; // �������
	private ImageIcon doubleJumpIc; // �����������
	private ImageIcon fallIc; // ���ϸ��(���� ���� ��)
	private ImageIcon slideIc; // �����̵� ���
	private ImageIcon hitIc; // �ε����� ���

	public CookieImg(ImageIcon imageIcon, ImageIcon imageIcon1, ImageIcon imageIcon2, ImageIcon imageIcon3, ImageIcon imageIcon4, ImageIcon imageIcon5) {
		cookieIc = imageIcon;
		jumpIc = imageIcon1;
		doubleJumpIc = imageIcon2;
		fallIc = imageIcon3;
		slideIc = imageIcon4;
		hitIc = imageIcon5;
	}

	public ImageIcon getCookieIc() {
		return cookieIc;
	}

	public void setCookieIc(ImageIcon cookieIc) {
		this.cookieIc = cookieIc;
	}

	public ImageIcon getJumpIc() {
		return jumpIc;
	}

	public void setJumpIc(ImageIcon jumpIc) {
		this.jumpIc = jumpIc;
	}

	public ImageIcon getDoubleJumpIc() {
		return doubleJumpIc;
	}

	public void setDoubleJumpIc(ImageIcon doubleJumpIc) {
		this.doubleJumpIc = doubleJumpIc;
	}

	public ImageIcon getFallIc() {
		return fallIc;
	}

	public void setFallIc(ImageIcon fallIc) {
		this.fallIc = fallIc;
	}

	public ImageIcon getSlideIc() {
		return slideIc;
	}

	public void setSlideIc(ImageIcon slideIc) {
		this.slideIc = slideIc;
	}

	public ImageIcon getHitIc() {
		return hitIc;
	}

	public void setHitIc(ImageIcon hitIc) {
		this.hitIc = hitIc;
	}
}
