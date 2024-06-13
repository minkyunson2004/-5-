package main;

import javax.swing.*;

import client.Client;
import frame.MainFrame;

public class Main {
    static public Client client = new Client("127.0.0.1", 8000);
    static public String userName = "";

    public static void main(String[] args) {
        //게임의 GUI를 구동하는 MainFrameRunnable 객체를 생성하여 UI 스레드에 처리를 요청
        SwingUtilities.invokeLater(new MainFrameRunnable());
    }
}

class MainFrameRunnable implements Runnable{
    @Override
    public void run() {
        //MainFrame 객체(게임의 기본창)를 생성하고 객체를 gui 형태로 실행
        //SwingUtilities.invokeLater()를 통해 실행되는 메소드
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}