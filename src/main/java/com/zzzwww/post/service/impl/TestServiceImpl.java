package com.zzzwww.post.service.impl;

import com.zzzwww.post.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public void test4() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("aaa");
        System.out.println("bbb");
    }
}
