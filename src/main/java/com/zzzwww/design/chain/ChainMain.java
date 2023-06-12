package com.zzzwww.design.chain;

public class ChainMain {

    public static void main(String[] args) {
        OrderChain oneFilterChain = new OneFilterChain();
        OrderChain twoFilterChain = new TwoFilterChain();
        oneFilterChain.setNext(twoFilterChain);
        oneFilterChain.support();
    }
}
