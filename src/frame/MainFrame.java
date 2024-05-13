package frame;

import javax.swing.*;
import java.awt.*;

import panels.*;
import panels.gameReady.*;

public class MainFrame extends JFrame {
    //getLayout() 오버라이딩(JFrame component)
    private final CardLayout MainFrameLayout = new CardLayout();
    @Override
    public CardLayout getLayout() {
        return MainFrameLayout;
    }

    //MainFrame component
    IntroPanel introPanel = new IntroPanel(this);
    IdInputPanel idInputPanel = new IdInputPanel(this);
    GameModeSelectPanel gameModeSelectPanel = new GameModeSelectPanel(this);
    NormalScoreModeReadyPanel normalScoreModeReadyPanel = new NormalScoreModeReadyPanel(this);
    HardScoreModeReadyPanel hardScoreModeReadyPanel = new HardScoreModeReadyPanel(this);
    SpeedModeReadyPanel speedModeReadyPanel = new SpeedModeReadyPanel(this);
    EnduringModeReadyPanel enduringModeReadyPanel = new EnduringModeReadyPanel(this);

    //MainFrame initializing method
    public MainFrame() {
        super();                //Frame 생성
        this.setFrame();        //Frame 설정
        this.setComponent();    //Frame Component 설정
    }
    private void setFrame() {
        this.setSize(900, 528);                    // 창 사이즈(Frame 기본 요소의 높이는 28픽셀로 프레임의 높이를 이미지 사이즈(888*500)보다 28픽셀 올려야 전체 이미지를 볼 수 있다.)
        this.setLocationRelativeTo(null);                       // 창을 화면 중앙에 배치
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 엑스버튼을 누르면 종료
        this.setLayout(MainFrameLayout);                        // 프레임의 레이아웃을 카드레이아웃으로 설정
    }
    private void setComponent() {
        this.add(introPanel, "IntroPanel");
        this.add(idInputPanel, "IdInputPanel");
        this.add(gameModeSelectPanel, "GameModeSelectPanel");
        this.add(normalScoreModeReadyPanel, "NormalScoreModeReadyPanel");
        this.add(hardScoreModeReadyPanel, "HardScoreModeReadyPanel");
        this.add(speedModeReadyPanel, "SpeedModeReadyPanel");
        this.add(enduringModeReadyPanel, "EnduringModeReadyPanel");
    }
}

