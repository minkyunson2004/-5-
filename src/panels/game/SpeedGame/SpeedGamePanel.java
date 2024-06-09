package panels.game.SpeedGame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import client.Client;
import frame.MainFrame;
import image.GameBackground;
import ingame.Back;
import ingame.Cookie;
import ingame.CookieImg;
import ingame.Field;
import ingame.Jelly;
import ingame.MapObjectImg;
import ingame.Tacle;
import main.Main;
import util.Util;

public class SpeedGamePanel extends JPanel {

    // 쿠키 이미지 아이콘들
    private ImageIcon cookieIc; // 기본모션
    private ImageIcon jumpIc; // 점프모션
    private ImageIcon doubleJumpIc; // 더블점프모션
    private ImageIcon fallIc; // 낙하모션(더블 점프 후)
    private ImageIcon slideIc; // 슬라이드 모션
    private ImageIcon hitIc; // 부딛히는 모션

    // 배경 이미지
    private ImageIcon backIc; // 제일 뒷 배경
    private ImageIcon secondBackIc; // 2번째 배경

    private ImageIcon backIc2;
    private ImageIcon secondBackIc2;

    private ImageIcon backIc3;
    private ImageIcon secondBackIc3;

    private ImageIcon backIc4;
    private ImageIcon secondBackIc4;

    // 젤리 이미지 아이콘들


    private ImageIcon jellyEffectIc;

    // 발판 이미지 아이콘들
    private ImageIcon field1Ic; // 발판
    private ImageIcon field2Ic; // 공중발판

    // 장애물 이미지 아이콘들
    private ImageIcon tacle10Ic; // 1칸 장애물
    private ImageIcon tacle20Ic; // 2칸 장애물
    private ImageIcon tacle30Ic; // 3칸 장애물
    private ImageIcon tacle40Ic; // 4칸 장애물

    // 체력 게이지
    private ImageIcon speedModeBar;
    private ImageIcon speedModeMe;
    private ImageIcon speedModeOpponent;

    private ImageIcon redBg; // 피격시 붉은 화면

    private ImageIcon jumpButtonIconUp;
    private ImageIcon jumpButtonIconDown;

    private ImageIcon leftButtonIconUp;
    private ImageIcon leftButtonIconDown;

    private ImageIcon rightButtonIconUp;
    private ImageIcon rightButtonIconDown;

    private ImageIcon slideIconUp;
    private ImageIcon slideIconDown;

    Image jumpBtn;
    Image slideBtn;
    Image leftBtn;
    Image rightBtn;


    // 리스트 생성
    private List<Jelly> jellyList; // 젤리 리스트

    private List<Field> fieldList; // 발판 리스트

    private List<Tacle> tacleList; // 장애물 리스트

    private List<Integer> mapLengthList;

    private int winlose = -1;

    private int mapLength = 0;

    private int runPage = 0; // 한 화면 이동할때마다 체력을 깎기 위한 변수

    private int runStage = 1; // 스테이지를 확인하는 변수이다. (미구현)

    private int myScore = 0; // 결과점수를 수집하는 변수

    private int opponentScore = 0; // 결과점수를 수집하는 변수

    private int gameSpeed = 5; // 게임 속도

    private boolean hit = false;

    private int hitPenalty = 0;

    private int penaltyTime=0;



    private int nowField = 2000; // 발판의 높이를 저장.


    private boolean fadeOn = false;


    private boolean downKeyOn = false; // 다운키 눌렀는지 여부

    private boolean redScreen = false; // 피격시 반짝 붉은 화면 여부

    int face; // 쿠키의 정면
    int foot; // 쿠키의 발

    // 이미지 파일로 된 맵을 가져온다.
    private int[] sizeArr; // 이미지의 넓이와 높이를 가져오는 1차원 배열
    private int[][] colorArr; // 이미지의 x y 좌표의 픽셀 색값을 저장하는 2차원배열

    private Image buffImage; // 더블버퍼 이미지
    private Graphics buffg; // 더블버퍼 g

    private AlphaComposite alphaComposite; // 투명도 관련 오브젝트

    Cookie c1; // 쿠키 오브젝트

    Back b11; // 배경1-1 오브젝트
    Back b12; // 배경1-2 오브젝트

    Back b21; // 배경2-1 오브젝트
    Back b22; // 배경2-2 오브젝트

    Color backFade; // 배경이 바뀔 때 페이드 아웃 페이드 인 하기 위한 컬러변수

    // 맵 오브젝트의 이미지들
    MapObjectImg mo1;
    MapObjectImg mo2;
    MapObjectImg mo3;
    MapObjectImg mo4;

    // 외부
    MainFrame superFrame;
    CardLayout cl;

    // 게임패널 생성자 (상위 프레임과 카드레이아웃, 그리고 Main인스턴스를 받는다)
    public SpeedGamePanel(MainFrame superFrame) {

        this.superFrame = superFrame;
        this.cl = superFrame.getLayout();

    }

    // 게임을 세팅한다
    public void gameSet(CookieImg ci) {

        setFocusable(true);

        initCookieImg(ci); // 쿠키이미지를 세팅

        initObject(); // 게임 내 지형지물 인스턴스 생성

        initListener(); // 키리스너 추가

        runRepaint(); // 리페인트 무한반복 실행
    }

    // 게임을 시작한다
    public void gameStart() {

        mapMove(); // 배경 젤리 발판 장애물 작동

        fall(); // 낙하 스레드 발동

    }

    // 화면을 그린다
    @Override
    protected void paintComponent(Graphics g) {

        // 더블버퍼는 그림을 미리그려놓고 화면에 출력한다.

        // 더블버퍼 관련
        if (buffg == null) {
            buffImage = createImage(this.getWidth(), this.getHeight());
            if (buffImage == null) {
                System.out.println("더블 버퍼링용 오프 스크린 생성 실패");
            } else {
                buffg = buffImage.getGraphics();
            }
        }

        // 투명도 관련
        Graphics2D g2 = (Graphics2D) buffg;

        super.paintComponent(buffg); // 이전 화면을 지운다.

        // 배경이미지를 그린다
        buffg.drawImage(b11.getImage(), b11.getX(), 0, b11.getWidth(), b11.getHeight() * 5 / 4, null);
        buffg.drawImage(b12.getImage(), b12.getX(), 0, b12.getWidth(), b12.getHeight() * 5 / 4, null);
        buffg.drawImage(b21.getImage(), b21.getX(), 0, b21.getWidth(), b21.getHeight() * 5 / 4, null);
        buffg.drawImage(b22.getImage(), b22.getX(), 0, b22.getWidth(), b22.getHeight() * 5 / 4, null);

        // 스테이지 넘어갈시 페이드아웃 인 효과
        if (fadeOn) {
            buffg.setColor(backFade); // 투명하게 하는방법 1
            buffg.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        // 발판을 그린다
        for (int i = 0; i < fieldList.size(); i++) {

            Field tempFoot = fieldList.get(i);

            // 사양을 덜 잡아먹게 하기위한 조치
            if (tempFoot.getX() > -90 && tempFoot.getX() < 900) { // x값이 -90~810인 객체들만 그린다.

                buffg.drawImage(tempFoot.getImage(), tempFoot.getX(), tempFoot.getY(), tempFoot.getWidth(),
                        tempFoot.getHeight(), null);
            }

        }

        // 젤리를 그린다
        for (int i = 0; i < jellyList.size(); i++) {

            Jelly tempJelly = jellyList.get(i);

            if (tempJelly.getX() > -90 && tempJelly.getX() < 900) {

                alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                        (float) tempJelly.getAlpha() / 255);
                g2.setComposite(alphaComposite); // 투명하게 하는방법 2

                buffg.drawImage(tempJelly.getImage(), tempJelly.getX(), tempJelly.getY(), tempJelly.getWidth(),
                        tempJelly.getHeight(), null);

                // alpha값을 되돌린다
                alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
                g2.setComposite(alphaComposite);
            }
        }

        // 장애물을 그린다
        for (int i = 0; i < tacleList.size(); i++) {

            Tacle tempTacle = tacleList.get(i);

            if (tempTacle.getX() > -90 && tempTacle.getX() < 900) {

                buffg.drawImage(tempTacle.getImage(), tempTacle.getX(), tempTacle.getY(), tempTacle.getWidth(),
                        tempTacle.getHeight(), null);
            }
        }

        if (c1.isInvincible()) { // 무적상태일 경우
            // 쿠키의 alpha값을 받아온다
            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) c1.getAlpha() / 255);
            g2.setComposite(alphaComposite);

            // 쿠키를 그린다
            buffg.drawImage(c1.getImage(), c1.getX() - 110, c1.getY() - 170,
                    cookieIc.getImage().getWidth(null) * 8 / 10, cookieIc.getImage().getHeight(null) * 8 / 10, null);

            // alpha값을 되돌린다
            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
            g2.setComposite(alphaComposite);

        } else { // 무적상태가 아닐 경우

            // 쿠키를 그린다
            buffg.drawImage(c1.getImage(), c1.getX() - 110, c1.getY() - 170,
                    cookieIc.getImage().getWidth(null) * 8 / 10, cookieIc.getImage().getHeight(null) * 8 / 10, null);
        }

        // 피격시 붉은 화면
        if (redScreen) {

            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 125 / 255);
            g2.setComposite(alphaComposite);

            buffg.drawImage(redBg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
            g2.setComposite(alphaComposite);
        }

//		buffg.setFont(new Font("Arial", Font.BOLD, 30));
//		buffg.setColor(Color.WHITE);
//		buffg.drawString(Integer.toString(myScore), 700, 85);

        // 점수를 그린다
        String scoreper = myScore + "%";
        g2.setFont(new Font("Arial", Font.BOLD, 30)); // 폰트 설정
        g2.setColor(Color.BLACK); // 글자 색 설정
        g2.drawString(scoreper, 830, 45);

        String penaltyTimeNum = penaltyTime + "s to accelerate";
        g2.setFont(new Font("Arial", Font.BOLD, 15)); // 폰트 설정
        g2.setColor(Color.RED); // 글자 색 설정
        g2.drawString(penaltyTimeNum, 770, 15);

        String speednum = gameSpeed  + "m/s";
        g2.setFont(new Font("Arial Bold", Font.BOLD, 30)); // 폰트 설정
        g2.setColor(Color.BLUE); // 글자 색 설정
        g2.drawString(speednum, 800, 75);

        // 체력게이지를 그린다
        buffg.drawImage(speedModeBar.getImage(), 20, 15, null);
        buffg.drawImage(speedModeMe.getImage(), 15+(7*myScore), 20, null);
        buffg.drawImage(speedModeOpponent.getImage(), 15+(7*opponentScore), 20, null);


        // 버튼을 그린다
        buffg.drawImage(jumpBtn, 0, 360, 132, 100, null);
        buffg.drawImage(slideBtn, 720, 360, 132, 100, null);
        buffg.drawImage(leftBtn, 150, 360, 90, 90, null);
        buffg.drawImage(rightBtn, 600, 360, 90, 90, null);

        // 버퍼이미지를 화면에 출력한다
        g.drawImage(buffImage, 0, 0, this);

    }

    // 맵 오브젝트 이미지들을 저장
    private void makeMo() {

        mo1 = new MapObjectImg(new GameBackground("img/game/Speed/map1img/bg1.png"), new ImageIcon("img/game/Speed/map1img/bg2.png"),
                new ImageIcon("img/game/Common/jelly/jelly1.png"), new ImageIcon("img/game/Common/jelly/jelly2.png"),
                new ImageIcon("img/game/Common/jelly/jelly3.png"), new ImageIcon("img/game/Common/jelly/life.png"),
                new ImageIcon("img/game/Common/effectTest.png"),
                new ImageIcon("img/game/Speed/map1img/fieldIc1.png"), new ImageIcon("img/game/Speed/map1img/fieldIc2.png"),
                new ImageIcon("img/game/Speed/map1img/tacle1.gif"), new ImageIcon("img/game/Speed/map1img/tacle2.png"),
                new ImageIcon("img/game/Speed/map1img/tacle3.png"), new ImageIcon("img/game/Speed/map1img/tacle3.png"));

        mo2 = new MapObjectImg(new GameBackground("img/game/Speed/map2img/back1.png"), new ImageIcon("img/game/Speed/map2img/back2.png"),
                new ImageIcon("img/game/Common/jelly/jelly1.png"), new ImageIcon("img/game/Common/jelly/jelly2.png"),
                new ImageIcon("img/game/Common/jelly/jelly3.png"), new ImageIcon("img/game/Common/jelly/life.png"),
                new ImageIcon("img/game/Common/effectTest.png"),
                new ImageIcon("img/game/Speed/map2img/field1.png"), new ImageIcon("img/game/Speed/map2img/field2.png"),
                new ImageIcon("img/game/Speed/map2img/tacle1.png"), new ImageIcon("img/game/Speed/map2img/tacle2.png"),
                new ImageIcon("img/game/Speed/map2img/tacle3.png"), new ImageIcon("img/game/Speed/map2img/tacle3.png"));

        mo3 = new MapObjectImg(new GameBackground("img/game/Speed/map3img/bg.png"), new ImageIcon("img/game/Speed/map3img/bg2.png"),
                new ImageIcon("img/game/Common/jelly/jelly1.png"), new ImageIcon("img/game/Common/jelly/jelly2.png"),
                new ImageIcon("img/game/Common/jelly/jelly3.png"), new ImageIcon("img/game/Common/jelly/life.png"),
                new ImageIcon("img/game/Common/effectTest.png"),
                new ImageIcon("img/game/Speed/map3img/field.png"), new ImageIcon("img/game/Speed/map3img/field2.png"),
                new ImageIcon("img/game/Speed/map3img/tacle1.png"), new ImageIcon("img/game/Speed/map3img/tacle2.png"),
                new ImageIcon("img/game/Speed/map3img/tacle3.png"), new ImageIcon("img/game/Speed/map3img/tacle3.png"));

        mo4 = new MapObjectImg(new GameBackground("img/game/Speed/map4img/bback.png"), new ImageIcon("img/game/Speed/bback2.png"),
                new ImageIcon("img/game/Common/jelly/jelly1.png"), new ImageIcon("img/game/Common/jelly/jelly2.png"),
                new ImageIcon("img/game/Common/jelly/jelly3.png"), new ImageIcon("img/game/Common/jelly/life.png"),
                new ImageIcon("img/game/Common/effectTest.png"),
                new ImageIcon("img/game/Speed/map4img/ffootTest.png"), new ImageIcon("img/game/Speed/map4img/ffootTest2.png"),
                new ImageIcon("img/game/Speed/map4img/tacle1.png"), new ImageIcon("img/game/Speed/map4img/tacle2.png"),
                new ImageIcon("img/game/Speed/map4img/tacle2.png"), new ImageIcon("img/game/Speed/map4img/tacle2.png"));


    }

    // 메인에서 받은 쿠키 이미지 아이콘들을 인스턴스화
    private void initCookieImg(CookieImg ci) {
        // 쿠키 이미지 아이콘들
        cookieIc = ci.getCookieIc(); // 기본모션
        jumpIc = ci.getJumpIc(); // 점프모션
        doubleJumpIc = ci.getDoubleJumpIc(); // 더블점프모션
        fallIc = ci.getFallIc(); // 낙하모션(더블 점프 후)
        slideIc = ci.getSlideIc(); // 슬라이드 모션
        hitIc = ci.getHitIc(); // 부딛히는 모션
    }

    // 젤리 발판 장애물 등을 인스턴스화
    private void initImageIcon(MapObjectImg mo) {

        // 젤리 이미지 아이콘들

        jellyEffectIc = mo.getJellyEffectIc();

        // 발판 이미지 아이콘들
        field1Ic = mo.getField1Ic(); // 발판
        field2Ic = mo.getField2Ic(); // 공중발판

        // 장애물 이미지 아이콘들
        tacle10Ic = mo.getTacle10Ic(); // 1칸 장애물
        tacle20Ic = mo.getTacle20Ic(); // 2칸 장애물
        tacle30Ic = mo.getTacle30Ic(); // 3칸 장애물
        tacle40Ic = mo.getTacle40Ic(); // 4칸 장애물
    }

    // 맵의 구조를 그림판 이미지를 받아서 세팅
    private void initMap(int num, int mapLength) {

        String tempMap = null;
        int tempMapLength = 0;

        if (num == 1) {
            tempMap = "img/game/Speed/map1.png";
        } else if (num == 2) {
            tempMap = "img/game/Speed/map1.png";
        } else if (num == 3) {
            tempMap = "img/game/Speed/map1.png";
        } else if (num == 4) {
            tempMap = "img/game/Speed/map1.png";
        }

        // 맵 정보 불러오기
        try {
            sizeArr = Util.getSize(tempMap); // 맵 사이즈를 배열에 저장
            colorArr = Util.getPic(tempMap); // 맵 픽셀값을 배열에 저장
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        tempMapLength = sizeArr[0];
        int maxX = sizeArr[0]; // 맵의 넓이
        int maxY = sizeArr[1]; // 맵의 높이


        for (int i = 0; i < maxX; i += 2) { // 발판은 4칸을 차지하는 공간이기 때문에 2,2사이즈로 반복문을 돌린다.
            for (int j = 0; j < maxY; j += 2) {
                if (colorArr[i][j] == 0) { // 색값이 0 일경우 (검은색)
                    // 좌표에 40을 곱하고, 넓이와 높이는 80으로 한다.
                    fieldList.add(new Field(field1Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80));

                } else if (colorArr[i][j] == 6579300) { // 색값이 6579300 일경우 (회색)
                    // 좌표에 40을 곱하고, 넓이와 높이는 80으로 한다.
                    fieldList.add(new Field(field2Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80));
                }
            }
        }

        for (int i = 0; i < maxX; i += 2) { // 장애물은 4칸 이상을 차지한다. 추후 수정
            for (int j = 0; j < maxY; j += 2) {
                if (colorArr[i][j] == 16711680) { // 색값이 16711680일 경우 (빨간색) 1칸
                    // 좌표에 40을 곱하고, 넓이와 높이는 80으로 한다.
                    tacleList.add(new Tacle(tacle10Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80, 0));

                } else if (colorArr[i][j] == 16711830) { // 색값이 16711830일 경우 (분홍) 2칸
                    // 좌표에 40을 곱하고, 넓이와 높이는 160으로 한다.
                    tacleList.add(new Tacle(tacle20Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 160, 0));

                } else if (colorArr[i][j] == 16711935) { // 색값이 16711830일 경우 (핫핑크) 3칸
                    // 좌표에 40을 곱하고, 넓이와 높이는 240으로 한다.
                    tacleList.add(new Tacle(tacle30Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 240, 0));
                }
            }
        }

        this.mapLength = this.mapLength + tempMapLength;
    }

    // makeMo, initImageIcon, imitMap 메서드를 이용해서 객체 생성
    private void initObject() {

        // 생명게이지 이미지아이콘
        speedModeBar = new ImageIcon("img/game/Common/lifebar/speedModeBar.png");
        speedModeMe = new ImageIcon("img/game/Common/lifebar/speedModeMe.png");
        speedModeOpponent = new ImageIcon("img/game/Common/lifebar/speedModeOpponent.png");

        // 피격 붉은 이미지
        redBg = new ImageIcon("img/game/Common/lifebar/redBg.png");

        // 점프버튼
        jumpButtonIconUp = new ImageIcon("img/game/Common/lifebar/jumpno.png");
        jumpButtonIconDown = new ImageIcon("img/game/Common/lifebar/jumpdim.png");

        // 슬라이드 버튼
        slideIconUp = new ImageIcon("img/game/Common/lifebar/slideno.png");
        slideIconDown = new ImageIcon("img/game/Common/lifebar/slidedim.png");
        //감속 버튼
        leftButtonIconUp = new ImageIcon("img/game/Common/lifebar/leftno.png");
        leftButtonIconDown = new ImageIcon("img/game/Common/lifebar/leftdim.png");
        //가속 버튼
        rightButtonIconUp = new ImageIcon("img/game/Common/lifebar/rightno.png");
        rightButtonIconDown = new ImageIcon("img/game/Common/lifebar/rightdim.png");

        jumpBtn = jumpButtonIconUp.getImage();
        slideBtn = slideIconUp.getImage();
        leftBtn = leftButtonIconUp.getImage();
        rightBtn = rightButtonIconUp.getImage();

        jellyList = new ArrayList<>(); // 젤리 리스트

        fieldList = new ArrayList<>(); // 발판 리스트

        tacleList = new ArrayList<>(); // 장애물 리스트

        mapLengthList = new ArrayList<>(); // 다음 맵의 시작지점을 확인하기위한 배열

        // 맵 인스턴스들을 생성

        makeMo();

        initImageIcon(mo1);
        initMap(1, mapLength);
        mapLengthList.add(mapLength);

        initImageIcon(mo2);
        initMap(2, mapLength);
        mapLengthList.add(mapLength);

        initImageIcon(mo3);
        initMap(3, mapLength);
        mapLengthList.add(mapLength);

        initImageIcon(mo4);
        initMap(4, mapLength);

        // 배경이미지 아이콘
        backIc = mo1.getBackIc();
        secondBackIc = mo1.getSecondBackIc();

        backIc2 = mo2.getBackIc();
        secondBackIc2 = mo2.getSecondBackIc();

        backIc3 = mo3.getBackIc();
        secondBackIc3 = mo3.getSecondBackIc();

        backIc4 = mo4.getBackIc();
        secondBackIc4 = mo4.getSecondBackIc();

        // 쿠키 인스턴스 생성 / 기본 자료는 클래스안에 내장 되어 있기 때문에 이미지만 넣었다.
        c1 = new Cookie(cookieIc.getImage());

        // 쿠키의 정면 위치 / 쿠키의 x값과 높이를 더한 값
        face = c1.getX() + c1.getWidth();

        // 쿠키의 발밑 위치 / 쿠키의 y값과 높이를 더한 값
        foot = c1.getY() + c1.getHeight();

        // 배경1-1 인스턴스 생성
        b11 = new Back(backIc.getImage(), 0, 0, backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));

        // 배경1-2 인스턴스 생성
        b12 = new Back(backIc.getImage(), backIc.getImage().getWidth(null), 0, // y 값 (조정 필요)
                backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));

        // 배경2-1 인스턴스 생성
        b21 = new Back(secondBackIc.getImage(), 0, 0, secondBackIc.getImage().getWidth(null),
                secondBackIc.getImage().getHeight(null));

        // 배경2-2 인스턴스 생성
        b22 = new Back(secondBackIc.getImage(), secondBackIc.getImage().getWidth(null), 0, // y 값 (조정 필요)
                secondBackIc.getImage().getWidth(null), secondBackIc.getImage().getHeight(null));

        backFade = new Color(0, 0, 0, 0);

    }

    // 리스너 추가 메서드
    private void initListener() {
        addKeyListener(new KeyAdapter() { // 키 리스너 추가
            @Override
            public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {// 스페이스 키를 누르고 더블점프가 2가 아닐때
                        jumpBtn = jumpButtonIconDown.getImage();
                        if (c1.getCountJump() < 2) {
                            jump(); // 점프 메서드 가동
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {// 속도감소버튼
                        leftBtn = leftButtonIconDown.getImage();
                        if (gameSpeed > 2 && !hit) {
                            gameSpeed-=1; // 점프 메서드 가동
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// 속도증가버튼
                        rightBtn = rightButtonIconDown.getImage();
                        if (gameSpeed < 20 && !hit) {
                            gameSpeed+=1; // 점프 메서드 가동
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) { // 다운키를 눌렀을 때
                        slideBtn = slideIconDown.getImage();
                        downKeyOn = true; // downKeyOn 변수를 true로

                        if (c1.getImage() != slideIc.getImage() // 쿠키이미지가 슬라이드 이미지가 아니고
                                && !c1.isJump() // 점프 중이 아니며
                                && !c1.isFall()) { // 낙하 중도 아닐 때

                            c1.setImage(slideIc.getImage()); // 이미지를 슬라이드이미지로 변경

                        }
                    }

            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_DOWN) { // 다운키를 뗐을 때
                    slideBtn = slideIconUp.getImage();
                    downKeyOn = false; // downKeyOn 변수를 false로

                    if (c1.getImage() != cookieIc.getImage() // 쿠키이미지가 기본이미지가 아니고
                            && !c1.isJump() // 점프 중이 아니며
                            && !c1.isFall()) { // 낙하 중도 아닐 때

                        c1.setImage(cookieIc.getImage()); // 이미지를 기본이미지로 변경
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    jumpBtn = jumpButtonIconUp.getImage();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftBtn = leftButtonIconUp.getImage();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightBtn = rightButtonIconUp.getImage();
                }
            }
        });
    }

    // 리페인트 전용 쓰레드 추가 메서드
    private void runRepaint() {
        // 리페인트 전용 쓰레드
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    repaint();

                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    // 화면을 움직이고 젤리를 먹거나, 장애물에 부딛히는 등의 이벤트를 발생시키는 메서드
    private void mapMove() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {

                    if (runPage > 630) { // 100픽셀 이동 마다 진행률 1% 증가
                        myScore+=1;
                        runPage = 0;
                    }
                    runPage += gameSpeed; // 화면이 이동하면 runPage에 이동한 만큼 저장된다.
                    foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

                    try {
                        if(foot > 1999) Main.client.out.write("gameOver\n");
                        else if (myScore >= 100) Main.client.out.write("gameEnd\n");
                        else Main.client.out.write(myScore + "\n");
                        Main.client.out.flush();

                        String gameRelay = Main.client.in.readLine();
                        if(gameRelay.equals("end")){
                            String result = Main.client.in.readLine();
                            if(result.equals("winner")){
                                winlose=1;
                                superFrame.getSpeedEndPanel().setResultScore(winlose);
                                cl.show(superFrame.getContentPane(), "SpeedEndPanel");
                                superFrame.setSpeedGamePanel(new SpeedGamePanel(superFrame));
                                superFrame.requestFocus();
                                break;
                            }
                            else if(result.equals("loser")){
                                winlose=0;
                                superFrame.getSpeedEndPanel().setResultScore(winlose);
                                cl.show(superFrame.getContentPane(), "SpeedEndPanel");
                                superFrame.setSpeedGamePanel(new SpeedGamePanel(superFrame));
                                superFrame.requestFocus();
                                break;
                            }
                        }
                        else{
                            opponentScore = Integer.parseInt(gameRelay);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    // 배경 이미지 변경
                    if (fadeOn == false) { // 페이드아웃인 상태가 아닐때
                        if (mapLength > mapLengthList.get(2) * 40 + 800 && b11.getImage() != backIc4.getImage()) {
                            fadeOn = true;

                            new Thread(new Runnable() {

                                @Override
                                public void run() {

                                    backFadeOut();

                                    b11 = new Back(backIc4.getImage(), 0, 0, backIc4.getImage().getWidth(null),
                                            backIc4.getImage().getHeight(null));

                                    b12 = new Back(backIc4.getImage(), backIc4.getImage().getWidth(null), 0,
                                            backIc4.getImage().getWidth(null), backIc4.getImage().getHeight(null));

                                    b21 = new Back(secondBackIc4.getImage(), 0, 0,
                                            secondBackIc4.getImage().getWidth(null),
                                            secondBackIc4.getImage().getHeight(null));

                                    b22 = new Back(secondBackIc4.getImage(), secondBackIc4.getImage().getWidth(null), 0,
                                            secondBackIc4.getImage().getWidth(null),
                                            secondBackIc4.getImage().getHeight(null));

                                    backFadeIn();
                                    fadeOn = false;
                                }
                            }).start();

                        } else if (mapLength > mapLengthList.get(1) * 40 + 800
                                && mapLength < mapLengthList.get(2) * 40 + 800
                                && b11.getImage() != backIc3.getImage()) {
                            fadeOn = true;

                            new Thread(new Runnable() {

                                @Override
                                public void run() {

                                    backFadeOut();

                                    b11 = new Back(backIc3.getImage(), 0, 0, backIc3.getImage().getWidth(null),
                                            backIc3.getImage().getHeight(null));

                                    b12 = new Back(backIc3.getImage(), backIc3.getImage().getWidth(null), 0,
                                            backIc3.getImage().getWidth(null), backIc3.getImage().getHeight(null));

                                    b21 = new Back(secondBackIc3.getImage(), 0, 0,
                                            secondBackIc3.getImage().getWidth(null),
                                            secondBackIc3.getImage().getHeight(null));

                                    b22 = new Back(secondBackIc3.getImage(), secondBackIc3.getImage().getWidth(null), 0,
                                            secondBackIc3.getImage().getWidth(null),
                                            secondBackIc3.getImage().getHeight(null));

                                    backFadeIn();
                                    fadeOn = false;
                                }
                            }).start();

                        } else if (mapLength > mapLengthList.get(0) * 40 + 800
                                && mapLength < mapLengthList.get(1) * 40 + 800
                                && b11.getImage() != backIc2.getImage()) {
                            fadeOn = true;

                            new Thread(new Runnable() {

                                @Override
                                public void run() {

                                    backFadeOut();

                                    b11 = new Back(backIc2.getImage(), 0, 0, backIc2.getImage().getWidth(null),
                                            backIc2.getImage().getHeight(null));

                                    b12 = new Back(backIc2.getImage(), backIc2.getImage().getWidth(null), 0,
                                            backIc2.getImage().getWidth(null), backIc2.getImage().getHeight(null));

                                    b21 = new Back(secondBackIc2.getImage(), 0, 0,
                                            secondBackIc2.getImage().getWidth(null),
                                            secondBackIc2.getImage().getHeight(null));

                                    b22 = new Back(secondBackIc2.getImage(), secondBackIc2.getImage().getWidth(null), 0,
                                            secondBackIc2.getImage().getWidth(null),
                                            secondBackIc2.getImage().getHeight(null));

                                    backFadeIn();
                                    fadeOn = false;
                                }
                            }).start();
                        }
                    }

                    // 배경이미지 변경을 위한 맵이동 길이 측정
                    mapLength += gameSpeed;

                    if (b11.getX() < -(b11.getWidth() - 1)) { // 배경1-1 이 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-2뒤에 붙음
                        b11.setX(b11.getWidth());
                    }
                    if (b12.getX() < -(b12.getWidth() - 1)) { // 배경1-2 가 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-1뒤에 붙음
                        b12.setX(b12.getWidth());
                    }

                    if (b21.getX() < -(b21.getWidth() - 1)) { // 배경1-1 이 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-2뒤에 붙음
                        b21.setX(b21.getWidth());
                    }
                    if (b22.getX() < -(b22.getWidth() - 1)) { // 배경1-2 가 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-1뒤에 붙음
                        b22.setX(b22.getWidth());
                    }

                    // 배경의 x좌표를 -1 해준다 (왼쪽으로 흐르는 효과)
                    b11.setX( (b11.getX() - gameSpeed / 3));
                    b12.setX( (b12.getX() - gameSpeed / 3));

                    b21.setX( (b21.getX() - gameSpeed / 3));
                    b22.setX( (b22.getX() - gameSpeed / 3));

                    // 발판위치를 -3 씩 해준다. (왼쪽으로 흐르는 효과)
                    for (int i = 0; i < fieldList.size(); i++) {

                        Field tempField = fieldList.get(i); // 임시 변수에 리스트 안에 있는 개별 발판을 불러오자

                        if (tempField.getX() < -90) { // 발판의 x좌표가 -90 미만이면 해당 발판을 제거한다.(최적화)

                            fieldList.remove(tempField);

                        } else {

                            tempField.setX(tempField.getX() - gameSpeed); // 위 조건에 해당이 안되면 x좌표를 줄이자

                        }
                    }

                    // 젤리위치를 -4 씩 해준다.
                    for (int i = 0; i < jellyList.size(); i++) {

                        Jelly tempJelly = jellyList.get(i); // 임시 변수에 리스트 안에 있는 개별 젤리를 불러오자

                        if (tempJelly.getX() < -90) { // 젤리의 x 좌표가 -90 미만이면 해당 젤리를 제거한다.(최적화)

                            fieldList.remove(tempJelly);

                        } else {

                            tempJelly.setX(tempJelly.getX() - gameSpeed); // 위 조건에 해당이 안되면 x좌표를 줄이자
                            if (tempJelly.getImage() == jellyEffectIc.getImage() && tempJelly.getAlpha() > 4) {
                                tempJelly.setAlpha(tempJelly.getAlpha() - 5);
                            }

                            foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

                            if ( // 캐릭터의 범위 안에 젤리가 있으면 아이템을 먹는다.
                                    c1.getImage() != slideIc.getImage()
                                            && tempJelly.getX() + tempJelly.getWidth() * 20 / 100 >= c1.getX()
                                            && tempJelly.getX() + tempJelly.getWidth() * 80 / 100 <= face
                                            && tempJelly.getY() + tempJelly.getWidth() * 20 / 100 >= c1.getY()
                                            && tempJelly.getY() + tempJelly.getWidth() * 80 / 100 <= foot
                                            && tempJelly.getImage() != jellyEffectIc.getImage()) {


                                tempJelly.setImage(jellyEffectIc.getImage()); // 젤리의 이미지를 이펙트로 바꾼다

                            } else if ( // 슬라이딩 하는 캐릭터의 범위 안에 젤리가 있으면 아이템을 먹는다.
                                    c1.getImage() == slideIc.getImage()
                                            && tempJelly.getX() + tempJelly.getWidth() * 20 / 100 >= c1.getX()
                                            && tempJelly.getX() + tempJelly.getWidth() * 80 / 100 <= face
                                            && tempJelly.getY() + tempJelly.getWidth() * 20 / 100 >= c1.getY()
                                            + c1.getHeight() * 1 / 3
                                            && tempJelly.getY() + tempJelly.getWidth() * 80 / 100 <= foot
                                            && tempJelly.getImage() != jellyEffectIc.getImage()) {

                                tempJelly.setImage(jellyEffectIc.getImage()); // 젤리의 이미지를 이펙트로 바꾼다

                            }
                        }
                    }

                    // 장애물위치를 - 4 씩 해준다.
                    for (int i = 0; i < tacleList.size(); i++) {

                        Tacle tempTacle = tacleList.get(i); // 임시 변수에 리스트 안에 있는 개별 장애물을 불러오자

                        if (tempTacle.getX() < -90) {

                            fieldList.remove(tempTacle); // 장애물의 x 좌표가 -90 미만이면 해당 젤리를 제거한다.(최적화)

                        } else {

                            tempTacle.setX(tempTacle.getX() - gameSpeed); // 위 조건에 해당이 안되면 x좌표를 줄이자

                            face = c1.getX() + c1.getWidth(); // 캐릭터 정면 위치 재스캔
                            foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

                            if ( // 무적상태가 아니고 슬라이드 중이 아니며 캐릭터의 범위 안에 장애물이 있으면 부딛힌다
                                    !c1.isInvincible() && c1.getImage() != slideIc.getImage()
                                            && tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
                                            && tempTacle.getX() + tempTacle.getWidth() / 2 <= face
                                            && tempTacle.getY() + tempTacle.getHeight() / 2 >= c1.getY()
                                            && tempTacle.getY() + tempTacle.getHeight() / 2 <= foot) {

                                hit(); // 피격 + 무적 쓰레드 메서드

                            } else if ( // 슬라이딩 아닐시 공중장애물
                                    !c1.isInvincible() && c1.getImage() != slideIc.getImage()
                                            && tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
                                            && tempTacle.getX() + tempTacle.getWidth() / 2 <= face
                                            && tempTacle.getY() <= c1.getY()
                                            && tempTacle.getY() + tempTacle.getHeight() * 95 / 100 > c1.getY()) {

                                hit(); // 피격 + 무적 쓰레드 메서드

                            } else if ( // 무적상태가 아니고 슬라이드 중이며 캐릭터의 범위 안에 장애물이 있으면 부딛힌다
                                    !c1.isInvincible() && c1.getImage() == slideIc.getImage()
                                            && tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
                                            && tempTacle.getX() + tempTacle.getWidth() / 2 <= face
                                            && tempTacle.getY() + tempTacle.getHeight() / 2 >= c1.getY()
                                            + c1.getHeight() * 2 / 3
                                            && tempTacle.getY() + tempTacle.getHeight() / 2 <= foot) {

                                hit(); // 피격 + 무적 쓰레드 메서드

                            } else if ( // 슬라이딩시 공중장애물
                                    !c1.isInvincible() && c1.getImage() == slideIc.getImage()
                                            && tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
                                            && tempTacle.getX() + tempTacle.getWidth() / 2 <= face
                                            && tempTacle.getY() < c1.getY() && tempTacle.getY()
                                            + tempTacle.getHeight() * 95 / 100 > c1.getY() + c1.getHeight() * 2 / 3) {

                                hit(); // 피격 + 무적 쓰레드 메서드
                            }
                        }
                    }

                    // 쿠키가 밟을 발판을 계산하는 코드
                    int tempField; // 발판위치를 계속 스캔하는 지역변수
                    int tempNowField; // 캐릭터와 발판의 높이에 따라 저장되는 지역변수, 결과를 nowField에 저장한다

                    // 쿠키가 무적상태라면 낙사 하지 않기 때문에 400으로 세팅 / 무적이 아니라면 2000(낙사지점);
                    if (c1.isInvincible()) {
                        tempNowField = 400;
                    } else {
                        tempNowField = 2000;
                    }

                    for (int i = 0; i < fieldList.size(); i++) { // 발판의 개수만큼 반복

                        int tempX = fieldList.get(i).getX(); // 발판의 x값

                        if (tempX > c1.getX() - 60 && tempX <= face) { // 발판이 캐릭 범위 안이라면

                            tempField = fieldList.get(i).getY(); // 발판의 y값을 tempField에 저장한다

                            foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

                            // 발판위치가 tempNowField보다 높고, 발바닥 보다 아래 있다면
                            // 즉, 캐릭터 발 아래에 제일 높이 있는 발판이라면 tempNowField에 저장한다.
                            if (tempField < tempNowField && tempField >= foot) {

                                tempNowField = tempField;

                            }
                        }
                    }

                    nowField = tempNowField; // 결과를 nowField에 업데이트 한다.


                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    // 부딛혔을 때 일어나는 상태를 담당하는 메서드
    private void hit() {
        new Thread(new Runnable() {

            @Override
            public void run() {

                c1.setInvincible(true);
                System.out.println("피격");

                redScreen = true; // 피격 붉은 이펙트 시작
                hit = true;
                gameSpeed=2;
                hitPenalty++;
                penaltyTime= 2+hitPenalty;


                c1.setAlpha(80); // 쿠키의 투명도를 80으로 변경

                try { // 0.5초 대기
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



                try { // 0.5초 대기
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (c1.getImage() == hitIc.getImage()) { // 0.5초 동안 이미지가 바뀌지 않았다면 기본이미지로 변경

                    c1.setImage(cookieIc.getImage());

                }

                for (int j = 0; j < (2+hitPenalty); j++) { // 2.5초간 캐릭터가 깜빡인다. (피격후 무적 상태를 인식)

                    if (c1.getAlpha() == 80) { // 이미지의 알파값이 80이면 160으로

                        c1.setAlpha(160);

                    } else { // 아니면 80으로

                        c1.setAlpha(80);

                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    penaltyTime--;
                }

                c1.setAlpha(255); // 쿠키의 투명도를 정상으로 변경
                hit = false;
                redScreen = false; // 피격 붉은 이펙트 종료
                c1.setInvincible(false);
                System.out.println("피격무적종료");
            }
        }).start();
    }

    // 낙하 메서드
    private void fall() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {

                    foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

                    // 발바닥이 발판보다 위에 있으면 작동
                    if (foot < nowField // 공중에 있으며
                            && !c1.isJump() // 점프 중이 아니며
                            && !c1.isFall()) { // 떨어지는 중이 아닐 때

                        c1.setFall(true); // 떨어지는 중으로 전환
                        System.out.println("낙하");

                        if (c1.getCountJump() == 2) { // 더블점프가 끝났을 경우 낙하 이미지로 변경
                            c1.setImage(fallIc.getImage());
                        }

                        long t1 = Util.getTime(); // 현재시간을 가져온다
                        long t2;
                        int set = 1; // 처음 낙하량 (0~10) 까지 테스트해보자

                        while (foot < nowField) { // 발이 발판에 닿기 전까지 반복

                            t2 = Util.getTime() - t1; // 지금 시간에서 t1을 뺀다

                            int fallY = set + (int) ((t2) / (200/gameSpeed)); // 낙하량을 늘린다.

                            foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

                            if (foot + fallY >= nowField) { // 발바닥+낙하량 위치가 발판보다 낮다면 낙하량을 조정한다.
                                fallY = nowField - foot;
                            }

                            c1.setY(c1.getY() + fallY); // Y좌표에 낙하량을 더한다

                            if (c1.isJump()) { // 떨어지다가 점프를 하면 낙하중지
                                break;
                            }

                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        c1.setFall(false);

                        if (downKeyOn // 다운키를 누른상태고
                                && !c1.isJump() // 점프 상태가 아니고
                                && !c1.isFall() // 낙하 상태가 아니고
                                && c1.getImage() != slideIc.getImage()) { // 쿠키 이미지가 슬라이드 이미지가 아닐 경우

                            c1.setImage(slideIc.getImage()); // 쿠키 이미지를 슬라이드로 변경

                        } else if (!downKeyOn // 다운키를 누른상태가 아니고
                                && !c1.isJump() // 점프 상태가 아니고
                                && !c1.isFall() // 낙하 상태가 아니고
                                && c1.getImage() != cookieIc.getImage()) { // 쿠키 이미지가 기본 이미지가 아닐 경우

                            c1.setImage(cookieIc.getImage());
                        }

                        if (!c1.isJump()) { // 발이 땅에 닿고 점프 중이 아닐 때 더블점프 카운트를 0으로 변경
                            c1.setCountJump(0);
                        }
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    // 점프 메서드
    private void jump() {
        new Thread(new Runnable() {

            @Override
            public void run() {

                c1.setCountJump(c1.getCountJump() + 1); // 점프 횟수 증가

                int nowJump = c1.getCountJump(); // 이번점프가 점프인지 더블점프인지 저장

                c1.setJump(true); // 점프중으로 변경

                if (c1.getCountJump() == 1) { // 점프 횟수가 1이라면

                    System.out.println("점프");
                    c1.setImage(jumpIc.getImage());

                } else if (c1.getCountJump() == 2) { // 점프 횟수가 2라면

                    System.out.println("더블점프");
                    c1.setImage(doubleJumpIc.getImage());

                }
// 점프난제
                long t1 = Util.getTime(); // 현재시간을 가져온다
                long t2;
                int set = 5+(int)(0.8*gameSpeed); // 점프 계수 설정(0~20) 등으로 바꿔보자
                int jumpY = 1; // 1이상으로만 설정하면 된다.(while문 조건 때문)

                while (jumpY >= 0) { // 상승 높이가 0일때까지 반복

                    t2 = Util.getTime() - t1; // 지금 시간에서 t1을 뺀다

                    jumpY = set - (int) ((t2) / (200/gameSpeed));// jumpY 를 세팅한다.

                    c1.setY(c1.getY() - jumpY); // Y값을 변경한다.

                    if (nowJump != c1.getCountJump()) { // 점프가 한번 더되면 첫번째 점프는 멈춘다.
                        break;
                    }

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (nowJump == c1.getCountJump()) { // 점프가 진짜 끝났을 때를 확인
                    c1.setJump(false); // 점프상태를 false로 변경
                }

            }
        }).start();
    }

    private void backFadeOut() {
        for (int i = 0; i < 256; i += 2) {
            backFade = new Color(0, 0, 0, i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void backFadeIn() {
        for (int i = 255; i >= 0; i -= 2) {
            backFade = new Color(0, 0, 0, i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}