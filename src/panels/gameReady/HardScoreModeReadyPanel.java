package panels.gameReady;

import frame.MainFrame;
import ingame.CookieImg;
import main.Main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HardScoreModeReadyPanel extends ReadyPanel{
    public HardScoreModeReadyPanel(MainFrame superFrame) {
        super(superFrame);
        super.RankingTitle.setText("Hard Score Mode 순위");
        super.RankingTitle.setIconTextGap(-180);
    }
    @Override
    public void getServerData(){
        String[] ranking = Main.client.getHardScoreRank();
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
                superFrame.getLayout().show(superFrame.getContentPane(), "HardScoreGamePanel"); //gamePanel 을 카드레이아웃 최상단으로 변경
                superFrame.getHardScoreGamePanel().gameSet(new CookieImg(new ImageIcon("img/cookieimg/cookie3/cookie.gif"),
                        new ImageIcon("img/cookieimg/cookie3/jump.png"),
                        new ImageIcon("img/cookieimg/cookie3/doublejump.gif"),
                        new ImageIcon("img/cookieimg/cookie3/fall.png"),
                        new ImageIcon("img/cookieimg/cookie3/slide.gif"),
                        new ImageIcon("img/cookieimg/cookie3/hit.png")));
                superFrame.getHardScoreGamePanel().gameStart(); // 게임시작
                superFrame.getHardScoreGamePanel().requestFocus();
                superFrame.setVisible(true);
            }
        };
    }
}