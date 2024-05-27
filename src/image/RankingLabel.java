package image;

import javax.swing.*;
import java.awt.*;

public class RankingLabel extends ImageIcon{
    public RankingLabel(String inputImage) {
        super(inputImage);
        Image image = getImage();
        setImage(image.getScaledInstance(231,30,Image.SCALE_SMOOTH));
    }
}
