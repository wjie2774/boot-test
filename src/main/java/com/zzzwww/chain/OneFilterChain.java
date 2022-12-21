package com.zzzwww.chain;

public class OneFilterChain  extends OrderChain{
    @Override
    public Boolean doFilter() {
        return Boolean.FALSE;
    }

    @Override
    public void success() {
        System.out.println("这个方法不可能成功的");
    }

    @Override
    public void fail() {
        System.out.println("失败了");
    }
}
