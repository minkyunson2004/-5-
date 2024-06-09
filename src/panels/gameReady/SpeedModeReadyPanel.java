package panels.gameReady;

import frame.MainFrame;
import ingame.CookieImg;
import main.Main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class SpeedModeReadyPanel extends ReadyPanel{
    public SpeedModeReadyPanel(MainFrame superFrame) {
        super(superFrame);
        super.RankingTitle.setText("Speed Mode 순위");
        super.RankingTitle.setIconTextGap(-165);
    }
    @Override
    public void getServerData(){
        String[] ranking = Main.client.getSpeedRank();
        System.arraycopy(ranking, 0, super.rank, 0, ranking.length);
        for(int i = 0; i < 100; i++) {
            RankingList[i].setText((i + 1) + ((i > 8)?"":"  ") + ((i == 99)?"":"  ") + "   " + rank[i]);
        }
    }
    @Override
    protected MouseListener setMouseListener(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                try {
                    Main.client.out.write("applySpeedPVP\n");
                    Main.client.out.flush();
                    Thread.sleep(100);
                    Main.client.out.write(Main.userName + "\n");
                    Main.client.out.flush();
                    System.out.println("applySpeedPVP");
                    String opponentUserName = Main.client.in.readLine();
                    System.out.println(opponentUserName);
                    for(int i = 0; i < 5; i++) {
                        Main.client.in.readLine();
                    }
                    String start = Main.client.in.readLine();
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
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }
}