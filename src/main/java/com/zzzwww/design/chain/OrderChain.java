package com.zzzwww.design.chain;

import java.util.Objects;

public abstract class OrderChain {

    private OrderChain next;

    public abstract Boolean doFilter();

    public abstract void success();

    public void fail() {
        System.out.println("默认失败了。。。");
    }

    public OrderChain setNext(OrderChain next) {
        this.next = next;
        return next;
    }

    public void support() {
        if (doFilter()) {
            success();
        } else if (Objects.nonNull(next)) {
            next.support();
        } else {
            fail();
        }
    }

    public static void main(String[] args) {
        Integer order = 1;
        if (Objects.equals(1, order)) {
            System.out.println("1111");
        } else if (Objects.nonNull(order)) {
            System.out.println("2222");
        } else {
            System.out.println("3333");

        }
    }
}
