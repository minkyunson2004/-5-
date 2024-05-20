package panels.game.SpeedGame;

import frame.MainFrame;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SpeedEndPanel extends JPanel {
    MainFrame superFrame;
    ImageIcon btn = new ImageIcon("img/end/button.png");
    JButton btnNewButton;
    JLabel lblNewLabel_1;
    JLabel lblNewLabel_2;
    JLabel lblNewLabel;


    private int resultScore;

    public void setResultScore(int resultScore) {
        lblNewLabel_2.setText(resultScore+"");
    }

    public SpeedEndPanel(MainFrame superFrame) {
        this.setLayout(null);
        this.superFrame = superFrame;
        //¹öÆ°
        btnNewButton = new JButton(btn);
        btnNewButton.setName("endAccept");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                superFrame.remove(superFrame.getSpeedGamePanel());
                superFrame.setSpeedGamePanel(new SpeedGamePanel(superFrame));
                superFrame.add(superFrame.getSpeedGamePanel(), "SpeedGamePanel"); // 게임패널을 새 패널로 교체
                superFrame.getSpeedGamePanel().setLayout(null);

                superFrame.getLayout().show(superFrame.getContentPane(), "GameModeSelectPanel"); // 새 select패널을 카드레이아웃 최상단으로 이동 (화면에 보임)
                superFrame.getGameModeSelectPanel().requestFocus(); // 리스너를 select패널에 강제로 줌
            }
        });
        btnNewButton.setBounds(550, 370, 199, 81);
        btnNewButton.setBorderPainted(false);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setContentAreaFilled(false);
        add(btnNewButton);

        //Á¡¼ö ±ÛÀÚ
        lblNewLabel_1 = new JLabel("SCORE");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 37));
        lblNewLabel_1.setBounds(451, 0, 205, 55);
        add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("0");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 49));
        lblNewLabel_2.setBounds(313, 52, 459, 87);
        add(lblNewLabel_2);

        lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBackground(SystemColor.activeCaptionText);
        lblNewLabel.setIcon(new ImageIcon("img/end/cookierunbg.jpg"));
        lblNewLabel.setBounds(0, 0, 784, 461);
        add(lblNewLabel);
    }
}