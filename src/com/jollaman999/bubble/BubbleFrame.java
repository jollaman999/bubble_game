package com.jollaman999.bubble;

import lombok.Getter;
import lombok.Setter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Getter
@Setter
public class BubbleFrame extends JFrame {
    private BubbleFrame mContext = this;
    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame() {
        initObject();
        initSetting();
        initListener();
        setVisible(true);
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }

    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon("image/BackgroundMap.png"));
        setContentPane(backgroundMap); // JPanel 을 JLabel 로 대체함

        player = new Player();
        add(player);
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {

            // 키보드 클릭 이벤트 핸들러
            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println(e.getKeyCode());

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (!player.isLeft() && !player.isLeftWallCrash()) {
                            player.left();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!player.isRight() && !player.isRightWallCrash()) {
                            player.right();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (!player.isUp() && !player.isDown()) {
                            player.up();
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        Bubble bubble = new Bubble(mContext);
                        add(bubble);
                        break;
                    // 떨어지는 건 있어도 벽을 뚫고 내려올 수는 없기
                }
            }

            // 키보드 해제 이벤트 핸들러
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        break;
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        break;
                }
            }
        });
    }

    private void initSetting() {
        setSize(1000, 640);
        getContentPane().setLayout(null); // absolute 레이아웃 (자유롭게 그림을 그릴 수 있다.)_
        setLocationRelativeTo(null); // JFrame 가운데 배치하기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼으로 창을 끌때 JVM 같이 종료하기
        setVisible(true);

    }
}
