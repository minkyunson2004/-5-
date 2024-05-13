package panels.gameReady;

import frame.MainFrame;

public class NormalScoreModeReadyPanel extends ReadyPanel{
    public NormalScoreModeReadyPanel(MainFrame superFrame) {
        super(superFrame);
        super.RankingTitle.setText("Normal Score Mode 랭킹");
    }

    @Override
    protected void getServerData(){
        for(int i = 0; i < 100; i++) {
            super.rank[i] = Integer.toString(i);
        }
    }
}