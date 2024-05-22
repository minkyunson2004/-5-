package image;

import javax.swing.*;
import java.awt.*;

public class GameBackground extends ImageIcon {
    //배경 이미지 크기 단일화용 클래스
    public GameBackground(String inputImage) {
        super(inputImage);
        Image image = getImage();
        setImage(image.getScaledInstance(1250,500,Image.SCALE_SMOOTH));
    }
}