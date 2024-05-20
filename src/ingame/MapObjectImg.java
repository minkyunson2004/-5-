package ingame;

import javax.swing.ImageIcon;

public class MapObjectImg {
	
	// ��� �̹���
	private ImageIcon backIc; // ���� �� ���
	private ImageIcon secondBackIc; // 2��° ���

	// ���� �̹��� �����ܵ�
	private ImageIcon jelly1Ic;
	private ImageIcon jelly2Ic;
	private ImageIcon jelly3Ic;
	private ImageIcon jellyHPIc;

	private ImageIcon jellyEffectIc;

	// ���� �̹��� �����ܵ�
	private ImageIcon field1Ic; // ����
	private ImageIcon field2Ic; // ���߹���

	// ��ֹ� �̹��� �����ܵ�
	private ImageIcon tacle10Ic; // 1ĭ ��ֹ�
	private ImageIcon tacle20Ic; // 2ĭ ��ֹ�
	private ImageIcon tacle30Ic; // 3ĭ ��ֹ�
	private ImageIcon tacle40Ic; // 4ĭ ��ֹ�

	public MapObjectImg(ImageIcon imageIcon, ImageIcon imageIcon1, ImageIcon imageIcon2, ImageIcon imageIcon3, ImageIcon imageIcon4, ImageIcon imageIcon5, ImageIcon imageIcon6, ImageIcon imageIcon7, ImageIcon imageIcon8, ImageIcon imageIcon9, ImageIcon imageIcon10, ImageIcon imageIcon11, ImageIcon imageIcon12) {
		backIc = imageIcon;
		secondBackIc = imageIcon1;
		jelly1Ic = imageIcon2;
		jelly2Ic = imageIcon3;
		jelly3Ic = imageIcon4;
		jellyHPIc = imageIcon5;
		jellyEffectIc = imageIcon6;
		field1Ic = imageIcon7;
		field2Ic = imageIcon8;
		tacle10Ic = imageIcon9;
		tacle20Ic = imageIcon10;
		tacle30Ic = imageIcon11;
		tacle40Ic = imageIcon12;
	}

	public ImageIcon getBackIc() {
		return backIc;
	}

	public void setBackIc(ImageIcon backIc) {
		this.backIc = backIc;
	}

	public ImageIcon getSecondBackIc() {
		return secondBackIc;
	}

	public void setSecondBackIc(ImageIcon secondBackIc) {
		this.secondBackIc = secondBackIc;
	}

	public ImageIcon getJelly1Ic() {
		return jelly1Ic;
	}

	public void setJelly1Ic(ImageIcon jelly1Ic) {
		this.jelly1Ic = jelly1Ic;
	}

	public ImageIcon getJelly2Ic() {
		return jelly2Ic;
	}

	public void setJelly2Ic(ImageIcon jelly2Ic) {
		this.jelly2Ic = jelly2Ic;
	}

	public ImageIcon getJellyHPIc() {
		return jellyHPIc;
	}

	public void setJellyHPIc(ImageIcon jellyHPIc) {
		this.jellyHPIc = jellyHPIc;
	}

	public ImageIcon getJelly3Ic() {
		return jelly3Ic;
	}

	public void setJelly3Ic(ImageIcon jelly3Ic) {
		this.jelly3Ic = jelly3Ic;
	}

	public ImageIcon getJellyEffectIc() {
		return jellyEffectIc;
	}

	public void setJellyEffectIc(ImageIcon jellyEffectIc) {
		this.jellyEffectIc = jellyEffectIc;
	}

	public ImageIcon getField1Ic() {
		return field1Ic;
	}

	public void setField1Ic(ImageIcon field1Ic) {
		this.field1Ic = field1Ic;
	}

	public ImageIcon getField2Ic() {
		return field2Ic;
	}

	public void setField2Ic(ImageIcon field2Ic) {
		this.field2Ic = field2Ic;
	}

	public ImageIcon getTacle10Ic() {
		return tacle10Ic;
	}

	public void setTacle10Ic(ImageIcon tacle10Ic) {
		this.tacle10Ic = tacle10Ic;
	}

	public ImageIcon getTacle20Ic() {
		return tacle20Ic;
	}

	public void setTacle20Ic(ImageIcon tacle20Ic) {
		this.tacle20Ic = tacle20Ic;
	}

	public ImageIcon getTacle30Ic() {
		return tacle30Ic;
	}

	public void setTacle30Ic(ImageIcon tacle30Ic) {
		this.tacle30Ic = tacle30Ic;
	}

	public ImageIcon getTacle40Ic() {
		return tacle40Ic;
	}

	public void setTacle40Ic(ImageIcon tacle40Ic) {
		this.tacle40Ic = tacle40Ic;
	}
}
