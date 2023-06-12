package com.zzzwww;

import com.zzzwww.post.dao.AttachmentAcquisitionMapper;
import com.zzzwww.post.dto.entity.AttachmentAcquisition;
import com.zzzwww.post.dto.entity.PostLog;
import com.zzzwww.post.dto.entity.User;
import com.zzzwww.post.service.AttachmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

@SpringBootTest
class BootTestApplicationTests {


    @Autowired
    AttachmentAcquisitionMapper attachmentAcquisitionMapper;
    @Autowired
    AttachmentService attachmentService;

    @Test
    void contextLoads() {
        List<String> strings = Arrays.asList("a", "b", "c", "d");
        String a = strings.stream().reduce("", String::concat);
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer sum = integers.stream().reduce(1, Integer::sum);
        System.out.println(sum);
    }




    @Test
    @Rollback(false)
    void batchData() {

        for (long i = 0; i < 800; i++) {
            List<AttachmentAcquisition> acquisitions = new ArrayList<>(1000000);
            for (long j = 0; j < 5000; j++) {
                AttachmentAcquisition attachmentAcquisition = new AttachmentAcquisition();
                attachmentAcquisition.setTargetId(j);
                attachmentAcquisition.setTargetType((int) i);
                attachmentAcquisition.setFileName("");
                attachmentAcquisition.setFileKey("");
                attachmentAcquisition.setLevelId(j);
                attachmentAcquisition.setCreateBy(0L);
                attachmentAcquisition.setCreateTime(new Date());
                attachmentAcquisition.setUpdateBy(0L);
                attachmentAcquisition.setUpdateTime(new Date());
                attachmentAcquisition.setDeleted(0);
                acquisitions.add(attachmentAcquisition);
            }
            attachmentService.saveBatch(acquisitions);
        }
    }

    public static Integer num = 1;

    @Test
    void testBuildParam() {
        List<String> param1 = new ArrayList<>();
        param1.add("11");
        param1.add("22");
        param1.add("33");
        Map<String, String> param2 = new HashMap<>();
        param2.put("11", "bar1");
        param2.put("33", "bar2");
        buildParam(this::buildSting, param1, param2);
        createUser();
    }


    @Test
    void test1() {
        Class<PostLog> postLogClass = PostLog.class;
        Annotation[] annotations = postLogClass.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> type = annotation.annotationType();
            System.out.println(type);
        }

    }

    private void buildSting(String message) {
        System.out.println("第" + num + "次执行！message :" + message);
        num++;
    }

    private void createUser() {
        User user = new User();  // 使用new关键字
        Class<User> userClass = User.class; // 类的class属性
        Constructor<?>[] constructors = userClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
        }
        Field[] declaredFields = userClass.getDeclaredFields();
        Method[] methods = userClass.getMethods();
        try {
            userClass.getMethod("xxx", Void.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }


    private <T> void buildParam(Consumer<T> consumer, List<T> list, Map<T, T> param) {
        Assert.notNull(param, "Param is null.");
        Assert.notNull(list, "Param is null.");
        for (Map.Entry<T, T> entry : param.entrySet()) {
            if (list.contains(entry.getKey())) {
                consumer.accept(entry.getValue());
            }
        }
    }


}
