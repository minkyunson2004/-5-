package panels.gameReady;

import javax.swing.*;

import frame.MainFrame;
import image.Background;
import image.ModeSelectButtonImage;
import image.RankingLabel;

import java.awt.*;

abstract public class ReadyPanel extends JPanel {
    //MainFrame
    MainFrame superFrame;

    //NormalScoreModeReadyPanel Component
    JLabel NormalScoreModeBackGround = new JLabel();
    JScrollPane RankingScrollPane;
    JLabel RankingTitle = new JLabel("순위");
    JButton GameStartButton = new JButton();

    //Server Data
    String[] rank = new String[100];

    //ReadyPanel initialize method
    public ReadyPanel(MainFrame superFrame) {
        this.superFrame = superFrame;
        this.getServerData();
        this.setPanel();
        this.setComponent();
    }
    protected void getServerData(){

    }
    private void setPanel(){
        this.setLayout(null);
    }
    private void setComponent(){
        JPanel RankingPanel = new JPanel();
        RankingPanel.setLayout(new GridLayout(0, 1));
        RankingPanel.setSize(181, 300); //스크롤의 넓이가 19
        for(int i = 0; i < 100; i++) {
            JLabel rankingLabel = new JLabel(rank[i]);
            rankingLabel.setSize(200,30);
            rankingLabel.setIcon(new RankingLabel("img/select/GameStartBtn.png"));
            rankingLabel.setIconTextGap(-100);
            RankingPanel.add(rankingLabel);
        }
        RankingScrollPane = new JScrollPane(RankingPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        RankingScrollPane.setBounds(100, 100, 200, 300);
        this.add(RankingScrollPane);

        RankingTitle.setBounds(100, 70, 200, 30);
        RankingTitle.setIcon(new RankingLabel("img/select/GameStartBtn.png"));
        RankingTitle.setIconTextGap(-100);
        this.add(RankingTitle);

        GameStartButton.setBounds(650, 380, 150, 40);
        GameStartButton.setIcon(new ModeSelectButtonImage("img/select/GameStartBtn.png"));
        GameStartButton.setContentAreaFilled(false);
        GameStartButton.setBorderPainted(false);
        this.add(GameStartButton);

        NormalScoreModeBackGround.setIcon(new Background("img/select/selectBg.png"));
        NormalScoreModeBackGround.setBounds(0, 0, 900, 500);
        this.add(NormalScoreModeBackGround);
    }
}