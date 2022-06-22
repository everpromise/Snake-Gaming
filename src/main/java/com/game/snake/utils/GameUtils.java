package com.game.snake.utils;

import java.awt.*;

public class GameUtils {
    // 蛇头
    public static Image upImg = Toolkit.getDefaultToolkit().getImage("classes\\imgs/head-up.png");
    public static Image downImg = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\imgs/head-down.png");
    public static Image leftImg = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\imgs/head-left.png");
    public static Image rightImg = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\imgs/head-right.png");
    // 蛇身
    public static Image bodyImg = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\imgs/body.png");
    // 食物
    public static Image foodImg = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\imgs/food.png");
    // 关卡
    public static int level = 1;
    // 绘制文字
    public static void drawWord(Graphics g, String str, Color color, int size, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("仿宋", Font.BOLD, size));
        g.drawString(str, x, y);
    }

}
