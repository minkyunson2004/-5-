package panels.PVPGameReady;

import javax.swing.*;

import frame.MainFrame;
import image.Background;
import image.ModeSelectButtonImage;
import image.RankingLabel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

abstract public class PVPReadyPanel extends JPanel {
    //MainFrame
    MainFrame superFrame;

    JLabel PVPModeBackGround = new JLabel();
    JLabel countdownLabel = new JLabel();
    JLabel leftClientLabel = new JLabel();
    JLabel rightClientLabel = new JLabel();
    JLabel CookieImage = new JLabel();

    //Server Data
    String leftClientId = "";
    String rightClientId = "";

    //ReadyPanel initialize method
    public PVPReadyPanel(MainFrame superFrame) {
        this.superFrame = superFrame;
        this.setPanel();
        this.setComponent();
    }
    abstract public void getServerData();
    private void setPanel(){
        this.setLayout(null);
    }
    private void setComponent(){
        leftClientLabel.setBounds(163, 117, 150, 30);
        leftClientLabel.setOpaque(true);
        leftClientLabel.setBackground(Color.BLACK);
        leftClientLabel.setForeground(Color.GREEN);
        leftClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(leftClientLabel);

        rightClientLabel.setBounds(587, 117, 150, 30);
        rightClientLabel.setOpaque(true);
        rightClientLabel.setBackground(Color.BLACK);
        rightClientLabel.setForeground(Color.GREEN);
        rightClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(rightClientLabel);

        CookieImage.setIcon(new ImageIcon("img/ready/SpeedClient.png"));
        CookieImage.setBounds(163, 170, 150, 200);
        this.add(CookieImage);

        PVPModeBackGround.setIcon(new Background("img/ready/PVPReadyBackground.png"));
        PVPModeBackGround.setBounds(0, 0, 900, 500);
        this.add(PVPModeBackGround);
    }
    public void setClientIds(String leftClientId, String rightClientId) {
        // Null check for client IDs
        if (leftClientId == null) leftClientId = "";
        if (rightClientId == null) rightClientId = "";

        this.leftClientId = leftClientId;
        this.rightClientId = rightClientId;
        updateClientLabels();

        // Check for non-empty client IDs
        if (!leftClientId.isEmpty() && !rightClientId.isEmpty()) {
            startCountdown();
        }
    }

    private void updateClientLabels() {
        leftClientLabel.setText(leftClientId);
        rightClientLabel.setText(rightClientId);
    }

    private void startCountdown() {
        new Thread(() -> {
            try {
                countdownLabel.setVisible(true);
                for (int i = 5; i > 0; i--) {
                    countdownLabel.setText(String.valueOf(i));
                    Thread.sleep(1000);
                }
                countdownLabel.setText("Start!");
                Thread.sleep(1000);
                countdownLabel.setVisible(false);
                // Logic to start the game
                System.out.println("Game Start!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    abstract protected MouseListener setMouseListener();
}

