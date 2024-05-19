package panels;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import frame.MainFrame;
import image.*;

public class GameModeSelectPanel extends JPanel {
    //MainFrame
    MainFrame superFrame;

    //GameModeSelectPanel Component
    JLabel GameModeSelectPanelBackGround = new JLabel();
    JLabel[] ModeCookieImage = new JLabel[4];
    JButton[] ModeButtons = new JButton[4];

    //GameModeSelectPanel initialize method
    public GameModeSelectPanel(MainFrame superFrame){
        this.superFrame = superFrame;   //상위 프레임 접근을 위한 Frame 변수
        this.setPanel();                //Panel 설정
        this.setComponent();            //Panel Component 설정
    }
    private void setPanel(){
        this.setLayout(null);
    }
    private void setComponent(){
        String[] modeArray = {"NormalScore", "HardScore", "Speed", "Enduring"};
        class ModeButtonMouseListener extends MouseAdapter {
            final String mode;
            ModeButtonMouseListener(String mode){
                super();
                this.mode = mode;
            }
            public void mouseClicked(MouseEvent e) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                superFrame.getLayout().show(superFrame.getContentPane(), mode + "ModeReadyPanel"); //gameReadyPanel 을 카드레이아웃 최상단으로 변경
                superFrame.setVisible(true);
            }
        }

        for (int i = 0; i < 4; i++){
            ModeCookieImage[i] = new JLabel();
            ModeCookieImage[i].setIcon(new ImageIcon("img/select/selectCh" + (i + 1) +".png"));
            ModeCookieImage[i].setBounds(60 + 210 * i, 130, 150, 200);
            this.add(ModeCookieImage[i]);

            ModeButtons[i] = new JButton();
            ModeButtons[i].setIcon(new ModeSelectButtonImage("img/select/gameSelectButton.png"));
            ModeButtons[i].setBounds(60 + 210 * i, 330, 150, 40);
            ModeButtons[i].setContentAreaFilled(false);
            ModeButtons[i].setBorderPainted(false);
            ModeButtons[i].addMouseListener(new ModeButtonMouseListener(modeArray[i]));
            this.add(ModeButtons[i]);
        }
        GameModeSelectPanelBackGround.setIcon(new Background("img/select/selectBackground.png"));
        GameModeSelectPanelBackGround.setBounds(0, 0, 900, 500);
        this.add(GameModeSelectPanelBackGround);
    }
}