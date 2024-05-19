package panels;

import javax.swing.*;
import java.awt.event.*;

import frame.MainFrame;
import image.Background;
import main.*;

public class IdInputPanel extends JPanel{
    //MainFrame
    MainFrame superFrame;

    //IdInputPanel Component
    JTextField idInputField = new JTextField("id를 입력하세요.", 20);
    JButton idInputButton = new JButton("확인");
    JLabel idInputPanelBackGround = new JLabel();

    //IdInputPanel initialize method
    public IdInputPanel(MainFrame superFrame){
        this.superFrame = superFrame;   //상위 프레임 접근을 위한 Frame 변수
        this.setPanel();                //Panel 설정
        this.setComponent();            //Panel Component 설정
    }
    private void setPanel(){
        this.setLayout(null);
    }
    private void setComponent(){
        idInputField.setBounds(350,220, 200, 20);
        this.add(idInputField);

        idInputButton.setBounds(420,260,60,20);
        idInputButton.addMouseListener(new IdInputButtonMouseListener());
        this.add(idInputButton);

        idInputPanelBackGround.setIcon(new Background("img/idInput/idInputBackground.png"));
        idInputPanelBackGround.setBounds(0, 0, 900, 500);
        this.add(idInputPanelBackGround);
    }

    class IdInputButtonMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Main.userName = idInputField.getText();
            superFrame.getLayout().show(superFrame.getContentPane(), "GameModeSelectPanel"); //gameModeSelect 패널을 카드레이아웃 최상단으로 변경
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
}