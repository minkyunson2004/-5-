package client;

import main.Main;

import java.io.*;
import java.net.Socket;

public class Client {
    Socket socket;
    BufferedReader in;
    BufferedWriter out;

    public Client(String ip, int port){
        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeClientSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void communicationExit(){
        try {
            out.write("communicationExit\n");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getNormalScoreRank(){
        String[] returnData = new String[100];
        try {
            out.write("sendNormalScoreRank\n");
            out.flush();
            for(int i = 0; i < 100; i++){
                returnData[i] = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return returnData;
    }

    public void receiveNormalScoreModeScore(int score){
        try {
            out.write("receiveNormalScoreModeScore\n"+ Main.userName + "\n" + score + "\n");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getHardScoreRank(){
        String[] returnData = new String[100];
        try {
            out.write("sendHardScoreRank\n");
            out.flush();
            for(int i = 0; i < 100; i++){
                returnData[i] = in.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return returnData;
    }

    public void receiveHardScoreModeScore(int score){
        try {
            out.write("receiveHardScoreModeScore\n"+ Main.userName + "\n" + score + "\n");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
