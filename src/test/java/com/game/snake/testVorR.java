package com.game.snake;


import org.junit.Test;

public class testVorR {
    private TestObj testObj = new TestObj(12);
    private int i = 1;

    /***
     * 结论就是如果是String 对象，那么传值后进行 = 操作， concat操作 都会重新创建一个对象，并让这个指针指向这个新的对象
     * 如果是StringBuffer对象，那么传值后的String操作都是原地操作，不会创建新对象。
     *
     * 所以说当java 对象作为参数传递时，可以理解为传递的是一个指向原对象的指针ptr，如果在函数中涉及到ptr = （new 新对象）的操作，那么这个指针就会指向这个新的对象。
     */
    private StringBuffer name = new StringBuffer("hello");

    @Test
    public void test() {
        System.out.println("testObj.x = " + testObj.x);
        System.out.println("i = " + i);
        System.out.println("name = " + name);
        this.change(testObj);
        this.change_i(i);
        this.change_str(name);
        System.out.println("testObj.x = " + testObj.x);
        System.out.println("i = " + i);
        System.out.println("name = " + name);


    }

    void change(TestObj tObj) {
        tObj.x = 111;
    }

    void change_i(int i) {
        i = 2;
    }

    void change_str(StringBuffer st) {
        st.append("jfald");
    }
}


class TestObj {
    public int x;

    public TestObj() {
        this.x = 1;
    }

    public TestObj(int x) {
        this.x = x;
    }
}