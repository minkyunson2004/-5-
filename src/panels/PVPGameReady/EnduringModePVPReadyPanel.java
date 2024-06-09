package panels.PVPGameReady;

import frame.MainFrame;
import ingame.CookieImg;
import main.Main;

import javax.swing.*;
import java.awt.*;

public class EnduringModePVPReadyPanel extends JPanel{
    String opponentId = "";
    String CountDown = "";
    //MainFrame
    MainFrame superFrame;
    //ReadyPanel initialize method
    public EnduringModePVPReadyPanel(MainFrame superFrame) {
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
                        paint(EnduringModePVPReadyPanel.this.getGraphics());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        for(int i = 5; i > 0; i--) {
                            CountDown = Integer.toString(i);
                            paint(EnduringModePVPReadyPanel.this.getGraphics());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        CountDown = "start";
                        paint(EnduringModePVPReadyPanel.this.getGraphics());
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        superFrame.getLayout().show(superFrame.getContentPane(), "EnduringGamePanel"); //gamePanel 을 카드레이아웃 최상단으로 변경
                        superFrame.getEnduringGamePanel().gameSet(new CookieImg(new ImageIcon("img/cookieimg/cookie4/kch.gif"),
                                new ImageIcon("img/cookieimg/cookie4/kjump.gif"),
                                new ImageIcon("img/cookieimg/cookie4/kjump.gif"),
                                new ImageIcon("img/cookieimg/cookie4/kch.gif"),
                                new ImageIcon("img/cookieimg/cookie4/kslide.gif"),
                                new ImageIcon("img/cookieimg/cookie4/kch.gif")));
                        superFrame.getEnduringGamePanel().gameStart(); // 게임시작
                        superFrame.getEnduringGamePanel().requestFocus();
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
        g.drawImage(new ImageIcon("img/ready/EnduringClient.png").getImage(), 163, 170, 150, 200, this);
        g.setColor(Color.BLACK);
        g.fillRect(163, 117, 150, 30);
        g.fillRect(587, 117, 150, 30);
        g.setColor(Color.WHITE);
        g.drawString(Main.userName, 163, 137);
        g.drawString(opponentId, 587, 137);
        g.drawString(CountDown, 420, 220);
    }
}
