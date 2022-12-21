package com.zzzwww.post.service.impl;

import com.zzzwww.post.service.TestThreadLocalServicce;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestThreadLocalServicceImpl implements TestThreadLocalServicce {

    private static final ThreadLocal<Map<String, String>> localVariable = ThreadLocal.withInitial(HashMap::new);

    @Override
    public void test1() {
        Map<String, String> localMap = localVariable.get();
        localMap.put("foo", "bar");
        this.printLocalVariable();
    }

    private void printLocalVariable() {
        Map<String, String> localMap = localVariable.get();
        System.out.println(localMap);
    }


}
