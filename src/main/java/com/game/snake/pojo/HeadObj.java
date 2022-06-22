package com.game.snake.pojo;

import com.game.snake.GameWin;
import com.game.snake.utils.GameUtils;

import java.awt.*;
import java.util.List;

public class HeadObj extends GameObj{
    public HeadObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
    }

    // 方向 up down left right
    private String direction = "right";

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    // 蛇头移动
    public void move() {
        //蛇身移动
        List<BodyObj> bodyObjList = this.frame.bodyObjList;
        for (int i = bodyObjList.size() - 1; i > 0; i--) {
            bodyObjList.get(i).x = bodyObjList.get(i-1).x;
            bodyObjList.get(i).y = bodyObjList.get(i-1).y;
            // 蛇头与身体的碰撞判断
            if (this.x == bodyObjList.get(i).x && this.y == bodyObjList.get(i).y) {
                // 失败
                GameWin.state = 3;
            }
        }
        bodyObjList.get(0).x = this.x;
        bodyObjList.get(0).y = this.y;



        // 蛇头移动
        switch (direction){
            case "up":
                y -= height;
                break;
            case "down":
                y += height;
                break;
            case "left":
                x -= width;
                break;
            case "right":
                x += width;
                break;
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        //蛇吃食物
        FoodObj food = this.frame.foodObj;
        //身体最后一节的坐标
        Integer newX = null;
        Integer newY = null;

        if (this.x == food.x && this.y == food.y) {
            this.frame.foodObj = food.getFood();
            // 获取蛇身的最后一个元素
            BodyObj lastBody = this.frame.bodyObjList.get(this.frame.bodyObjList.size() - 1);
            newX = lastBody.x;
            newY = lastBody.y;
            // 分数+1
            this.frame.score ++;

        }
        //通关判断
        if (this.frame.score >= 3) {
            // 通关
            GameWin.state = 4;
        }
        move();
        if (newX != null && newY != null) {
            this.frame.bodyObjList.add(new BodyObj(GameUtils.bodyImg, newX, newY, this.frame));
        }
        // 越界处理
        if (x < 0) {
            x = 570;
        } else if (x > 570) {
            x = 0;
        } else if (y < 30) {
            y = 570;
        }else if (y > 570) {
            y = 30;
        }
    }
}
