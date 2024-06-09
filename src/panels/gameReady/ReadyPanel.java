package panels.gameReady;

import javax.swing.*;

import frame.MainFrame;
import image.Background;
import image.ModeSelectButtonImage;
import image.RankingLabel;

import java.awt.*;
import java.awt.event.*;

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
            RankingList[i] = new CustomLabel((i + 1) + ((i > 8) ? "" : " ") + ((i == 99) ? "" : " ") + " " + rank[i], i);
            RankingList[i].setSize(250,30);
            RankingList[i].setIconTextGap(-220);
            RankingPanel.add(RankingList[i]);
        }
        RankingScrollPane = new JScrollPane(RankingPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        RankingScrollPane.setBounds(100, 100, 250, 300);
        this.add(RankingScrollPane);

        RankingTitle.setBounds(100, 70, 250, 30);
        RankingTitle.setText("Ranking");
        RankingTitle.setOpaque(true);
        RankingTitle.setBackground(Color.ORANGE); // 주황색
        RankingTitle.setForeground(Color.WHITE); // 흰색
        RankingTitle.setHorizontalAlignment(SwingConstants.CENTER);
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

    protected class CustomLabel extends JLabel {
        private int index;

        public CustomLabel(String text, int index) {
            super(text);
            this.index = index;
            setOpaque(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // 배경 주황색으로 채우기
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());

            // 테두리 그리기
            int borderWidth = 4; // 테두리 두께

            g.setColor(Color.ORANGE);

            g.fillRect(0, 0, getWidth(), borderWidth / 2); // 상단 테두리
            g.fillRect(0, getHeight() - borderWidth / 2, getWidth(), borderWidth / 2); // 하단 테두리
            g.fillRect(0, 0, borderWidth, getHeight()); // 좌측 테두리
            g.fillRect(getWidth() - borderWidth, 0, borderWidth, getHeight()); // 우측 테두리

            // Draw text
            FontMetrics fm = g.getFontMetrics();
            String text = getText();
            String[] parts = text.split(" ", 2);
            String number = parts[0];
            String mainText = parts.length > 1 ? parts[1] : "";

            int textHeight = fm.getAscent();
            int numberX = 10;
            int textY = (getHeight() + textHeight) / 2 - fm.getDescent() + borderWidth / 2;

            g.setColor(Color.BLACK); // 텍스트 색상 검정색으로 변경
            g.drawString(number, numberX, textY);

            int mainTextWidth = fm.stringWidth(mainText);
            int mainTextX = (getWidth() - mainTextWidth) / 2 + borderWidth / 2;
            g.drawString(mainText, mainTextX, textY);
        }
    }
}