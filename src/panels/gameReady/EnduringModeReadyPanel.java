package panels.gameReady;

import frame.MainFrame;
import ingame.CookieImg;
import main.Main;
import panels.PVPGameReady.EnduringModePVPReadyPanel;
import panels.PVPGameReady.SpeedModePVPReadyPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnduringModeReadyPanel extends ReadyPanel{
    private String userId;
    public EnduringModeReadyPanel(MainFrame superFrame) {
        super(superFrame);
        super.RankingTitle.setText("Enduring Mode 순위");
        super.RankingTitle.setIconTextGap(-173);
    }

    @Override
    public void getServerData() {

    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    protected MouseListener setMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                superFrame.getLayout().show(superFrame.getContentPane(), "EnduringModePvpReadyPanel"); //gamePanel 을 카드레이아웃 최상단으로 변경
                superFrame.getEnduringModePVPReadyPanel().start();
                superFrame.setVisible(true);
            }
        };
    }
}