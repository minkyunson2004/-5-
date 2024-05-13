package image;

import javax.swing.*;
import java.awt.*;

public class ModeSelectButtonImage extends ImageIcon{
    public ModeSelectButtonImage(String inputImage) {
        super(inputImage);
        Image image = getImage();
        setImage(image.getScaledInstance(150,40,Image.SCALE_SMOOTH));
    }
}
