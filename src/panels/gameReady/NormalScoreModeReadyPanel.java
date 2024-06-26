package panels.gameReady;

import frame.MainFrame;
import ingame.CookieImg;
import main.Main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NormalScoreModeReadyPanel extends ReadyPanel{
    public NormalScoreModeReadyPanel(MainFrame superFrame) {
        super(superFrame);
        super.RankingTitle.setText("Normal Score Mode 순위");
        super.RankingTitle.setIconTextGap(-189);
    }
    @Override
    public void getServerData(){
        String[] ranking = Main.client.getNormalScoreRank();
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
                superFrame.getLayout().show(superFrame.getContentPane(), "NormalScoreGamePanel"); //gamePanel 을 카드레이아웃 최상단으로 변경
                superFrame.getNormalScoreGamePanel().gameSet(new CookieImg(new ImageIcon("img/cookieimg/cookie1/player_origin.gif"),
                        new ImageIcon("img/cookieimg/cookie1/player_up.gif"),
                        new ImageIcon("img/cookieimg/cookie1/player_doubleup.gif"),
                        new ImageIcon("img/cookieimg/cookie1/player_jumpend.png"),
                        new ImageIcon("img/cookieimg/cookie1/player_down.gif"),
                        new ImageIcon("img/cookieimg/cookie1/player_attack.png")));
                superFrame.getNormalScoreGamePanel().gameStart(); // 게임시작
                superFrame.getNormalScoreGamePanel().requestFocus();
                superFrame.setVisible(true);
            }
        };
    }
}