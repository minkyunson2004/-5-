package image;

import javax.swing.*;
import java.awt.*;

public class Background extends ImageIcon {
    //배경 이미지 크기 단일화용 클래스
    public Background(String inputImage) {
        super(inputImage);
        Image image = getImage();
        setImage(image.getScaledInstance(900,500,Image.SCALE_SMOOTH));
    }
}
