package panels.PVPGameReady;

import javax.swing.*;

import frame.MainFrame;
import image.Background;
import main.Main;
import util.Util;

import java.awt.*;
import java.awt.event.MouseListener;

abstract public class PVPReadyPanel extends JPanel {
    //MainFrame
    MainFrame superFrame;
    //
    //ReadyPanel initialize method
    public PVPReadyPanel(MainFrame superFrame) {
        this.superFrame = superFrame;
        this.setPanel();
    }
    private void setPanel(){
        this.setLayout(null);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("img/ready/PVPReadyBackground.png").getImage(), 0, 0, 900, 500, this);
        g.drawImage(new ImageIcon("img/ready/SpeedClient.png").getImage(), 163, 170, 150, 200, this);
        g.setColor(Color.BLACK);
        g.fillRect(163, 117, 150, 30);
        g.fillRect(587, 117, 150, 30);
        g.setColor(Color.WHITE);
        g.drawString(Main.userName, 163, 137);
    }
}

