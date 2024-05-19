package frame;

import javax.swing.*;
import java.awt.*;

import panels.*;
import panels.game.EnduringGame.EnduringEndPanel;
import panels.game.EnduringGame.EnduringGamePanel;
import panels.game.HardScoreGame.HardScoreEndPanel;
import panels.game.HardScoreGame.HardScoreGamePanel;
import panels.game.NormalScoreGame.NormalScoreEndPanel;
import panels.game.NormalScoreGame.NormalScoreGamePanel;
import panels.game.SpeedGame.SpeedEndPanel;
import panels.game.SpeedGame.SpeedGamePanel;
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
    NormalScoreGamePanel normalScoreGamePanel = new NormalScoreGamePanel(this);
    NormalScoreEndPanel normalScoreEndPanel = new NormalScoreEndPanel(this);
    HardScoreGamePanel hardScoreGamePanel = new HardScoreGamePanel(this);
    HardScoreEndPanel hardScoreEndPanel = new HardScoreEndPanel(this);
    SpeedGamePanel speedGamePanel = new SpeedGamePanel(this);
    SpeedEndPanel speedEndPanel = new SpeedEndPanel(this);
    EnduringGamePanel enduringGamePanel = new EnduringGamePanel(this);
    EnduringEndPanel enduringEndPanel = new EnduringEndPanel(this);

    //MainFrame initializing method
    public MainFrame() {
        super();                //Frame 생성
        this.setFrame();        //Frame 설정
        this.setComponent();    //Frame Component 설정
    }
    private void setFrame() {
        this.setSize(900, 528);                    //창 사이즈(Frame 기본 요소의 높이는 28픽셀로 프레임의 높이를 이미지 사이즈(888*500)보다 28픽셀 올려야 전체 이미지를 볼 수 있다.)
        this.setLocationRelativeTo(null);                       //창을 화면 중앙에 배치
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //엑스버튼을 누르면 종료
        this.setLayout(MainFrameLayout);                        //프레임의 레이아웃을 카드레이아웃으로 설정
    }
    private void setComponent() {
        this.add(introPanel, "IntroPanel");
        this.add(idInputPanel, "IdInputPanel");
        this.add(gameModeSelectPanel, "GameModeSelectPanel");
        this.add(normalScoreModeReadyPanel, "NormalScoreModeReadyPanel");
        this.add(hardScoreModeReadyPanel, "HardScoreModeReadyPanel");
        this.add(speedModeReadyPanel, "SpeedModeReadyPanel");
        this.add(enduringModeReadyPanel, "EnduringModeReadyPanel");
        this.add(normalScoreGamePanel, "NormalScoreGamePanel");
        this.add(normalScoreEndPanel, "NormalScoreEndPanel");
        this.add(hardScoreGamePanel, "HardScoreGamePanel");
        this.add(hardScoreEndPanel, "HardScoreEndPanel");
        this.add(speedGamePanel, "SpeedGamePanel");
        this.add(speedEndPanel, "SpeedEndPanel");
        this.add(enduringGamePanel, "EnduringGamePanel");
        this.add(enduringEndPanel, "EnduringEndPanel");
    }

    public NormalScoreGamePanel getNormalScoreGamePanel() {
        return normalScoreGamePanel;
    }

    public void setNormalScoreGamePanel(NormalScoreGamePanel normalScoreGamePanel) {
        this.normalScoreGamePanel = normalScoreGamePanel;
    }

    public HardScoreEndPanel getHardScoreEndPanel() {
        return hardScoreEndPanel;
    }

    public void setHardScoreEndPanel(HardScoreEndPanel hardScoreEndPanel) {
        this.hardScoreEndPanel = hardScoreEndPanel;
    }

    public IntroPanel getIntroPanel() {
        return introPanel;
    }

    public void setIntroPanel(IntroPanel introPanel) {
        this.introPanel = introPanel;
    }

    public IdInputPanel getIdInputPanel() {
        return idInputPanel;
    }

    public void setIdInputPanel(IdInputPanel idInputPanel) {
        this.idInputPanel = idInputPanel;
    }

    public GameModeSelectPanel getGameModeSelectPanel() {
        return gameModeSelectPanel;
    }

    public void setGameModeSelectPanel(GameModeSelectPanel gameModeSelectPanel) {
        this.gameModeSelectPanel = gameModeSelectPanel;
    }

    public NormalScoreModeReadyPanel getNormalScoreModeReadyPanel() {
        return normalScoreModeReadyPanel;
    }

    public void setNormalScoreModeReadyPanel(NormalScoreModeReadyPanel normalScoreModeReadyPanel) {
        this.normalScoreModeReadyPanel = normalScoreModeReadyPanel;
    }

    public HardScoreModeReadyPanel getHardScoreModeReadyPanel() {
        return hardScoreModeReadyPanel;
    }

    public void setHardScoreModeReadyPanel(HardScoreModeReadyPanel hardScoreModeReadyPanel) {
        this.hardScoreModeReadyPanel = hardScoreModeReadyPanel;
    }

    public SpeedModeReadyPanel getSpeedModeReadyPanel() {
        return speedModeReadyPanel;
    }

    public void setSpeedModeReadyPanel(SpeedModeReadyPanel speedModeReadyPanel) {
        this.speedModeReadyPanel = speedModeReadyPanel;
    }

    public EnduringModeReadyPanel getEnduringModeReadyPanel() {
        return enduringModeReadyPanel;
    }

    public void setEnduringModeReadyPanel(EnduringModeReadyPanel enduringModeReadyPanel) {
        this.enduringModeReadyPanel = enduringModeReadyPanel;
    }

    public NormalScoreEndPanel getNormalScoreEndPanel() {
        return normalScoreEndPanel;
    }

    public void setNormalScoreEndPanel(NormalScoreEndPanel normalScoreEndPanel) {
        this.normalScoreEndPanel = normalScoreEndPanel;
    }

    public HardScoreGamePanel getHardScoreGamePanel() {
        return hardScoreGamePanel;
    }

    public void setHardScoreGamePanel(HardScoreGamePanel hardScoreGamePanel) {
        this.hardScoreGamePanel = hardScoreGamePanel;
    }

    public SpeedGamePanel getSpeedGamePanel() {
        return speedGamePanel;
    }

    public void setSpeedGamePanel(SpeedGamePanel speedGamePanel) {
        this.speedGamePanel = speedGamePanel;
    }

    public SpeedEndPanel getSpeedEndPanel() {
        return speedEndPanel;
    }

    public void setSpeedEndPanel(SpeedEndPanel speedEndPanel) {
        this.speedEndPanel = speedEndPanel;
    }

    public EnduringGamePanel getEnduringGamePanel() {
        return enduringGamePanel;
    }

    public void setEnduringGamePanel(EnduringGamePanel enduringGamePanel) {
        this.enduringGamePanel = enduringGamePanel;
    }

    public EnduringEndPanel getEnduringEndPanel() {
        return enduringEndPanel;
    }

    public void setEnduringEndPanel(EnduringEndPanel enduringEndPanel) {
        this.enduringEndPanel = enduringEndPanel;
    }
}

