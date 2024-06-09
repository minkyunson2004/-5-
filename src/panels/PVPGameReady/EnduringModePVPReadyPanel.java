package panels.PVPGameReady;

import frame.MainFrame;
import ingame.CookieImg;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnduringModePVPReadyPanel extends PVPReadyPanel{
    public EnduringModePVPReadyPanel(MainFrame superFrame) {
        super(superFrame);
    }

    @Override
    public void getServerData() {

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
                superFrame.getLayout().show(superFrame.getContentPane(), "EnduringGamePanel"); //gamePanel 을 카드레이아웃 최상단으로 변경
                superFrame.getEnduringGamePanel().gameSet(new CookieImg(new ImageIcon("img/cookieimg/cookie4/kch.gif"),
                        new ImageIcon("img/cookieimg/cookie4/kjump.gif"),
                        new ImageIcon("img/cookieimg/cookie4/kjump.gif"),
                        new ImageIcon("img/cookieimg/cookie4/kjump.gif"),
                        new ImageIcon("img/cookieimg/cookie4/kslide.gif"),
                        new ImageIcon("img/cookieimg/cookie4/kch.gif")));
                superFrame.getEnduringGamePanel().gameStart(); // 게임시작
                superFrame.getEnduringGamePanel().requestFocus();
                superFrame.setVisible(true);
            }
        };
    }
}
