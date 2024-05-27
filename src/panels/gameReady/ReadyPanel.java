package panels.gameReady;

import javax.swing.*;

import frame.MainFrame;
import image.Background;
import image.ModeSelectButtonImage;
import image.RankingLabel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

abstract public class ReadyPanel extends JPanel {
    //MainFrame
    MainFrame superFrame;

    //NormalScoreModeReadyPanel Component
    JLabel NormalScoreModeBackGround = new JLabel();
    JScrollPane RankingScrollPane;
    JLabel RankingTitle = new JLabel();
    JLabel[] RankingList = new JLabel[100];
    JButton rollBackButton = new JButton();
    JButton GameStartButton = new JButton();

    //Server Data
    String[] rank = new String[100];

    //ReadyPanel initialize method
    public ReadyPanel(MainFrame superFrame) {
        this.superFrame = superFrame;
        this.setPanel();
        this.setComponent();
    }
    abstract public void getServerData();
    private void setPanel(){
        this.setLayout(null);
    }
    private void setComponent(){
        JPanel RankingPanel = new JPanel();
        RankingPanel.setLayout(new GridLayout(0, 1));
        RankingPanel.setSize(231, 300); //스크롤의 넓이가 19
        for(int i = 0; i < 100; i++) {
            RankingList[i] = new JLabel();
            RankingList[i].setSize(250,30);
            RankingList[i].setIcon(new RankingLabel("img/ready/rankingBackground.png"));
            RankingList[i].setIconTextGap(-220);
            RankingPanel.add(RankingList[i]);
        }
        RankingScrollPane = new JScrollPane(RankingPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        RankingScrollPane.setBounds(100, 100, 250, 300);
        this.add(RankingScrollPane);

        RankingTitle.setBounds(100, 70, 250, 30);
        RankingTitle.setIcon(new RankingLabel("img/ready/rankingTitle.png"));
        this.add(RankingTitle);

        rollBackButton.setBounds(0, 0, 100, 100);
        rollBackButton.setText("뒤로가기");
        rollBackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                superFrame.getLayout().show(superFrame.getContentPane(), "GameModeSelectPanel");
                superFrame.setVisible(true);
            }
        });
        this.add(rollBackButton);

        GameStartButton.setBounds(650, 380, 150, 40);
        GameStartButton.setIcon(new ModeSelectButtonImage("img/ready/GameStartButton.png"));
        GameStartButton.setContentAreaFilled(false);
        GameStartButton.setBorderPainted(false);
        GameStartButton.addMouseListener(setMouseListener());
        this.add(GameStartButton);

        NormalScoreModeBackGround.setIcon(new Background("img/ready/readyBackground.png"));
        NormalScoreModeBackGround.setBounds(0, 0, 900, 500);
        this.add(NormalScoreModeBackGround);
    }
    abstract protected MouseListener setMouseListener();
}