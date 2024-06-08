package panels.gameReady;

import frame.MainFrame;
import panels.PVPGameReady.SpeedModePVPReadyPanel;
import main.Main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SpeedModeReadyPanel extends ReadyPanel {
    private String userId;
    public SpeedModeReadyPanel(MainFrame superFrame) {
        super(superFrame);
        super.RankingTitle.setText("Speed Mode 순위");
        super.RankingTitle.setIconTextGap(-165);
    }

    @Override
    public void getServerData() {
        // 아이디와 점수를 한번에 받아오는 메서드 호출
        String[] rankingData = Main.client.getNormalScoreRank();

        // 랭킹 데이터에서 첫 번째 유저의 아이디 추출 (예시로 첫 번째 유저의 아이디를 사용)
        if (rankingData.length > 0) {
            String[] parts = rankingData[0].split(" ");
            if (parts.length > 0) {
                userId = parts[0]; // 첫 번째 유저의 아이디를 저장
            } else {
                userId = ""; // 데이터가 잘못된 경우 빈 문자열
            }
        }

        // 추출한 사용자 아이디를 랭킹 목록에 표시
        for (int i = 0; i < 100 && i < rankingData.length; i++) {
            String[] parts = rankingData[i].split(" ");
            String userId = parts.length > 0 ? parts[0] : "";
            RankingList[i].setText((i + 1) + ((i > 8) ? "" : "  ") + ((i == 99) ? "" : "  ") + "   " + userId);
        }
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
                MainFrame frame = (MainFrame) superFrame;
                SpeedModePVPReadyPanel pvpPanel = frame.getSpeedModePVPReadyPanel();
                pvpPanel.setClientIds(userId, "Player2"); // 필요한 데이터 설정

                if (userId == null || userId.isEmpty()) {
                    System.out.println("User ID is not set.");
                } else {
                    System.out.println("User ID: " + userId);
                    pvpPanel.setClientIds(userId, "Player2");
                }


                frame.showSpeedModePVPReadyPanel(); // SpeedModePVPReadyPanel로 전환
                frame.setVisible(true);
            }
        };
    }
}
