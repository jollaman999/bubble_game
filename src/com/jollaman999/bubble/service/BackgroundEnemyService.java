package com.jollaman999.bubble.service;

import com.jollaman999.bubble.component.Enemy;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

// 메인쓰레드 바쁨 - 키보드 이벤트 처리하기 바쁨.
// 백그라운드에서 계속 관찰
public class BackgroundEnemyService implements Runnable {
    private BufferedImage image;
    private Enemy enemy;

    // 플레이어, 버블
    public BackgroundEnemyService(Enemy enemy) {
        this.enemy = enemy;

        try {
            image = ImageIO.read(new File("image/BackgroundMapService.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (enemy.getState() == 0) {
            // 색상 확인
            Color leftColor = new Color(image.getRGB(enemy.getX() - 10, enemy.getY() + 25));
            Color rightColor = new Color(image.getRGB(enemy.getX() + 50 + 15, enemy.getY() + 25));
            // -2가 나온다는 뜻은 바닥에 색깔이 없다는 것
            int bottomColor = image.getRGB(enemy.getX() + 10, enemy.getY() + 50 + 5) // -1
                    + image.getRGB(enemy.getX() + 50 - 10, enemy.getY() + 50 + 5); // -1
//            System.out.println("바텀: " + bottomColor);

            if (bottomColor != -2) {
//                System.out.println("바닥에 충돌함");
                enemy.setDown(false);
            } else {
                if (!enemy.isUp() && !enemy.isDown()) {
//                    System.out.println("다운 실행됨");
                    enemy.down();
                }
            }

            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
//                System.out.println("왼쪽 벽에 충돌함");
                enemy.setLeft(false);
                if (!enemy.isRight()) {
                    enemy.right();
                }
            } else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
//                System.out.println("오른쪽 벽에 충돌함");
                enemy.setRight(false);
                if (!enemy.isLeft()) {
                    enemy.left();
                }
            }

            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
