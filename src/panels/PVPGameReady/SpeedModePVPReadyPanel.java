package panels.PVPGameReady;

import frame.MainFrame;
import ingame.CookieImg;
import main.Main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SpeedModePVPReadyPanel extends PVPReadyPanel{
    public SpeedModePVPReadyPanel(MainFrame superFrame) {
        super(superFrame);
    }

    @Override
    public void getServerData() {

    }

    @Override
    protected MouseListener setMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // 300밀리초 일시 정지
                    Thread.sleep(300);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                superFrame.getLayout().show(superFrame.getContentPane(), "SpeedGamePanel"); // gamePanel을 카드레이아웃 최상단으로 변경
                superFrame.getSpeedGamePanel().gameSet(new CookieImg(new ImageIcon("img/cookieimg/cookie2/normal.gif"),
                        new ImageIcon("img/cookieimg/cookie2/jump.gif"),
                        new ImageIcon("img/cookieimg/cookie2/doublejump.gif"),
                        new ImageIcon("img/cookieimg/cookie2/fall.png"),
                        new ImageIcon("img/cookieimg/cookie2/slide.gif"),
                        new ImageIcon("img/cookieimg/cookie2/hit.gif")));
                superFrame.getSpeedGamePanel().gameStart(); // 게임 시작
                superFrame.getSpeedGamePanel().requestFocus();
                superFrame.setVisible(true);
            }
        };
    }
}
