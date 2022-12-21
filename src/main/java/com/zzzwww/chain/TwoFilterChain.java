package com.zzzwww.chain;

public class TwoFilterChain extends OrderChain{
    @Override
    public Boolean doFilter() {
        return Boolean.TRUE;
    }

    @Override
    public void success() {
        System.out.println("这个方法一定会成功的");
    }

    @Override
    public void fail() {
        System.out.println("失败了");
    }
}
