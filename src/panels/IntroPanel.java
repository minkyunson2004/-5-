package panels;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import frame.MainFrame;
import image.*;

public class IntroPanel extends JPanel {
    //MainFrame
    MainFrame superFrame;

    //IntroPanel Component
    JLabel introPanelBackGround = new JLabel();
    IntroPanelMouseListener introPanelMouseListener = new IntroPanelMouseListener();
    class IntroPanelMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            superFrame.getLayout().show(superFrame.getContentPane(), "IdInputPanel");
            superFrame.setVisible(true);
        }
        @Override
        public void mousePressed(MouseEvent e) { }
        @Override
        public void mouseReleased(MouseEvent e) { }
        @Override
        public void mouseEntered(MouseEvent e) { }
        @Override
        public void mouseExited(MouseEvent e) { }
    }

    //IntroPanel initialize method
    public IntroPanel(MainFrame superFrame) {
        this.superFrame = superFrame;   //상위 프레임 접근을 위한 Frame 변수
        this.setPanel();                //Panel 설정
        this.setComponent();            //Panel Component 설정
    }
    private void setPanel(){
        this.setLayout(null);
    }
    private void setComponent(){
        introPanelBackGround.setIcon(new Background("img/intro/intro.png"));
        introPanelBackGround.setBounds(0, 0, 900, 500);
        this.add(introPanelBackGround);
        this.addMouseListener(introPanelMouseListener);
    }
}

