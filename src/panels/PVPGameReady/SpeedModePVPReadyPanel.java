package panels.PVPGameReady;

import frame.MainFrame;
import ingame.CookieImg;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
                        paint(SpeedModePVPReadyPanel.this.getGraphics());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            Main.client.out.write("applySpeedPVP\n");
                            Main.client.out.flush();
                            Main.client.in.readLine();
                            Main.client.out.write(Main.userName + "\n");
                            Main.client.out.flush();
                            System.out.println("applySpeedPVP");
                            opponentId = Main.client.in.readLine();
                            paint(SpeedModePVPReadyPanel.this.getGraphics());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(opponentId);
                            for(int i = 0; i < 5; i++) {
                                CountDown = "   " + Main.client.in.readLine();
                                paint(SpeedModePVPReadyPanel.this.getGraphics());
                            }
                            String start = Main.client.in.readLine();
                            CountDown = "start";
                            paint(SpeedModePVPReadyPanel.this.getGraphics());
                            CountDown = "";
                            opponentId = "";
                            if(start.equals("start")){
                                superFrame.getLayout().show(superFrame.getContentPane(), "SpeedGamePanel"); //gamePanel 을 카드레이아웃 최상단으로 변경
                                superFrame.getSpeedGamePanel().gameSet(new CookieImg(new ImageIcon("img/cookieimg/cookie2/normal.gif"),
                                        new ImageIcon("img/cookieimg/cookie2/jump.gif"),
                                        new ImageIcon("img/cookieimg/cookie2/doublejump.gif"),
                                        new ImageIcon("img/cookieimg/cookie2/fall.png"),
                                        new ImageIcon("img/cookieimg/cookie2/slide.gif"),
                                        new ImageIcon("img/cookieimg/cookie2/hit.gif")));
                                superFrame.getSpeedGamePanel().gameStart(); // 게임시작
                                superFrame.getSpeedGamePanel().requestFocus();
                                superFrame.setVisible(true);
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
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
        if(!opponentId.equals("")) {
            g.drawImage(new ImageIcon("img/ready/SpeedClient.png").getImage(), 587, 170, 150, 200, this);
        }
        g.setColor(Color.BLACK);
        g.fillRect(163, 117, 150, 30);
        g.fillRect(587, 117, 150, 30);
        g.setColor(Color.WHITE);
        g.drawString(Main.userName, 163, 137);
        g.drawString(opponentId, 587, 137);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g.drawString(CountDown, 425, 220);
    }
}
