package com.game.snake;

import com.game.snake.pojo.BodyObj;
import com.game.snake.pojo.FoodObj;
import com.game.snake.pojo.HeadObj;
import com.game.snake.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {

    // 分数
    public int score = 0;
    // 游戏状态 0.未开始 1.游戏中 2.暂停 3.失败 4.通关 5.失败后重新开始 6.下一关
    public static int state = 0;
    // 定义双缓存图片
    Image offScreenImage = null;
    // 窗口宽高
    int winWidth = 800;
    int winHeight = 600;

    // 蛇头
    HeadObj headObj = new HeadObj(GameUtils.rightImg, 60, 570, this);

    // 蛇身集合
    public List<BodyObj> bodyObjList = new ArrayList<>();

    // 食物
    public FoodObj foodObj = new FoodObj().getFood();

    public void launch() {
        // 设置窗口是否可见
        this.setVisible(true);
        // 设置窗口的大小
        this.setSize(this.winWidth, this.winHeight);
        //设置窗口的位置在屏幕上居中
        this.setLocationRelativeTo(null);
        //设置窗口标题
        this.setTitle("贪吃蛇-金洲学JAVA");

        // 蛇身初始化
        bodyObjList.add(new BodyObj(GameUtils.bodyImg, 30, 570, this));
        bodyObjList.add(new BodyObj(GameUtils.bodyImg,
                0, 570, this));

        //键盘事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    headObj.setDirection("down");
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    headObj.setDirection("up");
                }else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    headObj.setDirection("left");
                }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    headObj.setDirection("right");
                }
            }
        });

        //键盘事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    switch (state){
                        case 0:
                            // 未开始
                            state = 1;
                            break;
                        case 1:
                            // 游戏中
                            state = 2;
                            repaint();
                            break;
                        case 2:
                            // 游戏暂停
                            state = 1;
                            break;
                        case 3:
                            // 游戏失败重启
                            state = 5;
                            break;
                        case 4:
                            // 通关后下一关
                            state = 6;
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        while (true) {
            if (state == 1) {
                // 游戏中才调用
                repaint();
            }
            // 失败重启
            if (state == 5) {
                 state = 0;
                 resetGame();
            }
            // 通关下一关
            if (state == 6 && GameUtils.level != 3) {
                state = 1;
                GameUtils.level++;
                resetGame();
            }
            try {
                // 1秒1000毫秒
                Thread.sleep(200);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        // 初始化双缓存图片
        if (offScreenImage == null) {
            offScreenImage = this.createImage(winWidth, winHeight);
        }
        // 获取图片对应的Graphics对象
        Graphics gImage = offScreenImage.getGraphics();
        // 灰色背景
        gImage.setColor(Color.gray);
        gImage.fillRect(0, 0, this.winWidth, this.winHeight);
        // 网格线
        gImage.setColor(Color.black);
        for (int i = 1; i <= 20; i++) {
            // 绘制横线
            gImage.drawLine(0, i*30, this.winHeight, i*30);
            // 绘制竖线
            gImage.drawLine(i*30, 0, i*30, this
                    .winHeight);
        }
        //绘制蛇身体
        for (int i = bodyObjList.size()-1; i >= 0; i--) {
            bodyObjList.get(i).paintSelf(gImage);

        }

        // 绘制蛇头
        headObj.paintSelf(gImage);

        //绘制食物
        foodObj.paintSelf(gImage);

        //关卡
        GameUtils.drawWord(gImage, "第"+GameUtils.level+"关", Color.orange, 40, 650, 260);

        //绘制分数
        GameUtils.drawWord(gImage, score + " 分", Color.BLUE, 50, 650, 330);

        //绘制提示语
        gImage.setColor(Color.gray);
        prompt(gImage);

        // 将双缓存的图片绘制到主窗口中
        g.drawImage(offScreenImage, 0, 0, null);

    }

    // 绘制提示语
    void prompt(Graphics g) {
        // 未开始
        if (state == 0) {
            g.fillRect(120, 240, 400 ,70);
            GameUtils.drawWord(g, "按下空格键开始游戏", Color.yellow, 35, 150, 290);
        }

        // 暂停
        if (state == 2) {
            g.fillRect(120, 240, 400 ,70);
            GameUtils.drawWord(g, "游戏暂停", Color.yellow, 35, 150, 290);
        }

        // 失败
        if (state == 3) {
            g.fillRect(120, 240, 400 ,70);
            GameUtils.drawWord(g, "游戏失败,按空格重新开始", Color.red, 35, 150, 290);
        }

        // 通关
        if (state == 4) {
            g.fillRect(120, 240, 400 ,70);
            if (GameUtils.level == 3) {
                GameUtils.drawWord(g, "恭喜你，游戏通关!", Color.yellow, 35, 150, 290);
            }else {
                GameUtils.drawWord(g, "达成条件，下一关!", Color.yellow, 35, 150, 290);
            }
        }
    }

    // 游戏重置
    void resetGame() {
        // 关闭当前窗口
        this.dispose();
        // 开启一个新窗口
        String[] args = {};
        main(args);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}