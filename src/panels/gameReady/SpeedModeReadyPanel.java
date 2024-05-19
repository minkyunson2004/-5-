package panels.gameReady;

import frame.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SpeedModeReadyPanel extends ReadyPanel{
    public SpeedModeReadyPanel(MainFrame superFrame) {
        super(superFrame);
        super.RankingTitle.setText("Speed Mode 순위");
        super.RankingTitle.setIconTextGap(-140);
    }

    @Override
    protected void getServerData(){
        for(int i = 0; i < 100; i++) {
            super.rank[i] = Integer.toString(i);
        }
    }
    @Override
    protected MouseListener setMouseListener(){
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                superFrame.getLayout().show(superFrame.getContentPane(), "SpeedGamePanel"); //gamePanel 을 카드레이아웃 최상단으로 변경
                /*
                superFrame.gamePanel.gameStart(); // 게임시작
                superFrame.gamePanel.requestFocus(); // 리스너를 game 패널에 강제로 줌
                superFrame.setVisible(true);
                 */
            }
        };
    }
}