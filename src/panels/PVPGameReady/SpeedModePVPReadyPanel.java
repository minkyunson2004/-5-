package panels.PVPGameReady;

import frame.MainFrame;
import ingame.CookieImg;
import main.Main;

import javax.swing.*;
import java.awt.*;

public class SpeedModePVPReadyPanel extends JPanel{
    String opponentId = "";
    String CountDown = "";
    //MainFrame
    MainFrame superFrame;
    //ReadyPanel initialize method
    public SpeedModePVPReadyPanel(MainFrame superFrame) {
        this.superFrame = superFrame;
        this.setPanel();
    }
    private void setPanel(){
        this.setLayout(null);
    }
    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        opponentId = "sfasde";
                        paint(SpeedModePVPReadyPanel.this.getGraphics());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        for(int i = 5; i > 0; i--) {
                            CountDown = Integer.toString(i);
                            paint(SpeedModePVPReadyPanel.this.getGraphics());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        CountDown = "start";
                        paint(SpeedModePVPReadyPanel.this.getGraphics());
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        superFrame.getLayout().show(superFrame.getContentPane(), "SpeedGamePanel"); //gamePanel 을 카드레이아웃 최상단으로 변경
                        superFrame.getSpeedGamePanel().gameSet(new CookieImg(new ImageIcon("img/cookieimg/cookie2/normal.gif"),
                                new ImageIcon("img/cookieimg/cookie2/jump.gif"),
                                new ImageIcon("img/cookieimg/cookie2/doublejump.gif"),
                                new ImageIcon("img/cookieimg/cookie2/fall.png"),
                                new ImageIcon("img/cookieimg/cookie2/slide.gif"),
                                new ImageIcon("img/cookieimg/cookie2/hit.png")));
                        superFrame.getSpeedGamePanel().gameStart(); // 게임시작
                        superFrame.getSpeedGamePanel().requestFocus();
                        superFrame.setVisible(true);
                    }
                });
            }
        }).start();
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
        g.drawString(opponentId, 587, 137);
        g.drawString(CountDown, 420, 220);
    }
}
