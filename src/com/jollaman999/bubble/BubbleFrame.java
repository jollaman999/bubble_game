package com.jollaman999.bubble;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {
    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame() {
        initObject();
        initSetting();
        setVisible(true);
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }

    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroundMap); // JPanel 을 JLabel 로 대체함

        player = new Player();
        add(player);
//        backgroundMap.setSize(100, 100);
//        backgroundMap.setLocation(100, 100);
//        backgroundMap.setSize(1000, 600);
//        add(backgroundMap); // JFrame 에 JLabel 을 그린다.
    }

    private void initSetting() {
        setSize(1000, 640);
        getContentPane().setLayout(null); // absolute 레이아웃 (자유롭게 그림을 그릴 수 있다.)_
        setLocationRelativeTo(null); // JFrame 가운데 배치하기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼으로 창을 끌때 JVM 같이 종료하기
        setVisible(true);

    }
}
