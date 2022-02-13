package com.jollaman999.bubble;

import lombok.Getter;
import lombok.Setter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@Getter
@Setter
public class Enemy extends JLabel implements Moveable {
    private BubbleFrame mContext;

    // 위치 상태
    private int x;
    private int y;

    // 플레이어의 방향
    private  EnemyWay enemyWay;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // 적군을 맞춘 상태
    private int state; // 0(살아있는 상태), 1(물방울에 갇힌 상태)

    // 적군 이동 속도
    private final int SPEED = 3;
    private final int JUMPSPEED = 1; // up, down

    private ImageIcon enemyR, enemyL;

    public Enemy(BubbleFrame mContext) {
        this.mContext = mContext;
        initObject();
        initSetting();
        initBackgroundEnemyService();
    }

    private void initObject() {
        enemyR = new ImageIcon("image/enemyR.png");
        enemyL = new ImageIcon("image/enemyL.png");
    }

    private void initSetting() {
        x = 480;
        y = 178;

        left = false;
        right = false;
        up = false;
        down = false;

        state = 0;

        enemyWay = EnemyWay.RIGHT;

        setIcon(enemyR);
        setSize(50, 50);
        setLocation(x, y);
    }

    private void initBackgroundEnemyService() {
        // new Thread(new BackgroundEnemyService(this)).start();
    }

    // 이벤트 핸들러
    @Override
    public void left() {
        enemyWay = EnemyWay.LEFT;
        left = true;
        new Thread(() -> {
            while (left) {
                setIcon(enemyL);
                x -= SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void right() {
        enemyWay = EnemyWay.RIGHT;
        right = true;
        new Thread(() -> {
            while (right) {
                setIcon(enemyR);
                x += SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    @Override
    public void up() {
        up = true;
        new Thread(() -> {
            for (int i = 0; i < 130/JUMPSPEED; i++) {
                y -= JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            up = false;
            down();

//            y -= 120;
        }).start();
    }

    @Override
    public void down() {
        if (down == false) {

        }

        down = true;
        new Thread(()-> {
            while (down) {
                y += JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            down = false;
        }).start();
    }
}
