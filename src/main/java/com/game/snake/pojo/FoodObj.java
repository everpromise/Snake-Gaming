package com.game.snake.pojo;

import com.game.snake.GameWin;
import com.game.snake.utils.GameUtils;

import java.awt.*;
import java.util.Random;

public class FoodObj extends GameObj{

    // 随机
    Random r = new Random();

    public FoodObj() {
        super();
    }

    public FoodObj(Image img, int x, int y, int width, int height, GameWin frame) {
        super(img, x, y, width, height, frame);
    }

    public FoodObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
    }

    public FoodObj(Image img, int x, int y, int width, int height, GameWin frame, Random r) {
        super(img, x, y, width, height, frame);
        this.r = r;
    }

    public FoodObj(Image img, int x, int y, GameWin frame, Random r) {
        super(img, x, y, frame);
        this.r = r;
    }

    public FoodObj(Random r) {
        this.r = r;
    }

    // 获取食物
    public FoodObj getFood() {
        return new FoodObj(GameUtils.foodImg, r.nextInt(20) * 30, (r.nextInt(19) + 1) * 30, this.frame);
    }
    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }
}
