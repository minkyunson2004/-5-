package panels.game.stage;

import java.awt.image.BufferedImage;

public class Stage {
    //stage 별 이미지
    BufferedImage backgroundImage;
    BufferedImage floorImage;
    BufferedImage scaffoldingImage;
    BufferedImage slideObstacleImage;
    BufferedImage lowJumpObstacleImage;
    BufferedImage highJumpObstacleImage;
    //stage 데이터
    int stageLength;
    boolean[][] normalJelly;
    boolean[][] YellowBearJelly;
    boolean[][] PinkBearJelly;
    boolean[][] BigBearJelly;
    boolean[][] scaffolding;
    int[] holeAndObstacle;

    public Stage(int stageLength){
        this.stageLength = stageLength;
        this.normalJelly = new boolean[8][stageLength];
        this.YellowBearJelly = new boolean[8][stageLength];
        this.PinkBearJelly = new boolean[8][stageLength];
        this.BigBearJelly = new boolean[8][stageLength];
        this.scaffolding = new boolean[8][stageLength];
        this.holeAndObstacle = new int[stageLength];
    }
}
