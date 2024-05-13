package panels.gameReady;

import frame.MainFrame;

public class EnduringModeReadyPanel extends ReadyPanel{
    public EnduringModeReadyPanel(MainFrame superFrame) {
        super(superFrame);
        super.GameStartButton.setText("Hard Score Mode 순위");
    }

    @Override
    protected void getServerData(){
        for(int i = 0; i < 100; i++) {
            super.rank[i] = Integer.toString(i);
        }
    }
}